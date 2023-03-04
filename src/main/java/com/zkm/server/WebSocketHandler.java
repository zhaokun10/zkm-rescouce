package com.zkm.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zkm.model.ChatRecord;
import com.zkm.model.UserZkm;
import com.zkm.server.config.ChannelGroupConfig;
import com.zkm.service.FriendService;
import com.zkm.service.GroupService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    public static ConcurrentHashMap<Integer, Channel> channelUserMap = new ConcurrentHashMap<>();

    private WebSocketServerHandshaker webSocketServerHandshaker;

    @Autowired
    FriendService friendService;
    @Autowired
    GroupService groupService;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        log.info("handlerAdded,连接channel{},连接id{}", ctx.channel(), ctx.channel().id());
        ChannelGroupConfig.group.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //处理客户端向服务端发起的http握手请求
        if (o instanceof FullHttpRequest){
            log.info("http连接请求");
            handHttpRequest(channelHandlerContext,(FullHttpRequest) o);
        }else if (o instanceof WebSocketFrame){//处理websocket链接业务
            log.info("websocket信息请求");
            handWebSocketFrame(channelHandlerContext,(WebSocketFrame) o);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //从服务端的channelGroup中移除当前离开的客户端
        ChannelGroupConfig.group.remove(channel);

        //获得删除channle对应的userCode
        Integer removeUserCode = null;
        for (Integer userCode : channelUserMap.keySet()) {
            Channel userChannel = channelUserMap.get(userCode);
            if (userChannel.equals(channel)) {
                removeUserCode = userCode;
                break;
            }
        }

        //从服务端的channelMap中移除当前离开的客户端
        Collection<Channel> col = channelUserMap.values();
        while (true == col.contains(channel)) {
            col.remove(ctx.channel());
            log.info("handlerRemoved,netty客户端连接删除成功!,删除channel:{},channelId:{}", ctx.channel(), ctx.channel().id());
        }
        //通知好友上线下线通知
        sendFriendMsgLoginOrOut(removeUserCode, "notice", "下线了");
    }

    private void sendFriendMsgLoginOrOut(Integer userId,String type,String message) {

        //查询该用户好友
        List<UserZkm> friendList =  friendService.getAllFriendByUserId(userId);
        for (UserZkm friend : friendList) {
            Integer friendCode = friend.getId();
            String userName = friend.getNickName();
            if(channelUserMap.containsKey(friendCode)){
                channelUserMap.get(friendCode).writeAndFlush(new TextWebSocketFrame(userName + message));
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive,netty与客户端建立连接，通道开启！channel{}连接,连接id{}",ctx.channel(),ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive,netty与客户端断开连接，通道关闭！channel:{},channelId:{}",ctx.channel(),ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable)throws Exception {
        log.info("exceptionCaught,抛出异常，异常信息{}，异常信息channel:{},channelId:{}", throwable.getLocalizedMessage(), context.channel(), context.channel().id());
        handlerRemoved(context);
        context.close();
    }
    private void handWebSocketFrame(ChannelHandlerContext context, WebSocketFrame webSocketFrame) throws JsonProcessingException {
        if (webSocketFrame instanceof CloseWebSocketFrame){//判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(),(CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame){//判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        if (!(webSocketFrame instanceof TextWebSocketFrame)){//判断是否是二进制消息
            System.out.println("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }

    }

    private ChatRecord exchangeChatMessage(Channel channel, String text) {

        JSONObject chatRecordJson = JSONObject.parseObject(text);
        ChatRecord chatRecord = JSON.toJavaObject(chatRecordJson,ChatRecord.class);
        return chatRecord;
    }
    private void handHttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest){
        log.info("请求连接的channel{}，id为{}",context.channel(),context.channel().id());
        //判断是否http握手请求
        if (!fullHttpRequest.getDecoderResult().isSuccess()
                ||!("websocket".equals(fullHttpRequest.headers().get("Upgrade")))){
            sendHttpResponse(context,fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:9999/im",null,false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(fullHttpRequest);
        if (webSocketServerHandshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        }else{
            webSocketServerHandshaker.handshake(context.channel(),fullHttpRequest);
        }
        //把token解析成用户Code
        Channel channel = context.channel();
        String uri = fullHttpRequest.getUri();
        String userCode = uri.substring(uri.lastIndexOf("?")+1,uri.length());
        channelUserMap.put(Integer.getInteger(userCode),channel);

        sendFriendMsgLoginOrOut(Integer.getInteger(userCode),"notice","上线了");

    }

    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest, DefaultFullHttpResponse defaultFullHttpResponse){
        if (defaultFullHttpResponse.getStatus().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.getStatus().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
        if (defaultFullHttpResponse.getStatus().code() !=200){
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }
}
