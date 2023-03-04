package com.zkm.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkm.model.ChatRecord;
import com.zkm.model.UserZkm;
import com.zkm.server.config.ChannelGroupConfig;
import com.zkm.service.AddFriendNotificationService;
import com.zkm.service.ChatRecordService;
import com.zkm.service.FriendService;
import com.zkm.service.GroupService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ChannelHandler.Sharable
public class MessageServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static ConcurrentHashMap<Integer, Channel> channelUserMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, Channel> channelAddFriendMap = new ConcurrentHashMap<>();

    @Autowired
    FriendService friendService;
    @Autowired
    GroupService groupService;
    @Autowired
    ChatRecordService chatRecordService;

    @Autowired
    AddFriendNotificationService addFriendNotificationService;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        log.info("handlerAdded,连接channel{},连接id{}", ctx.channel(), ctx.channel().id());
        ChannelGroupConfig.group.add(ctx.channel());
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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("我收到消息啦,收到了->" + textWebSocketFrame.text());
        //获取客户端向服务端发送的消息
        String text = textWebSocketFrame.text();
        log.info("服务端收到客户端的消息：" + text);
            ChatRecord chatRecord = exchangeChatMessage(channelHandlerContext.channel(), text);
            if (Objects.nonNull(chatRecord.getGroupId())) {
                List<UserZkm> userZkmList = groupService.findGroupUserByGroupId(chatRecord.getGroupId());
                //群聊 给群里的每个人都发
                userZkmList.forEach(v -> {
                    //服务端向好友客户端发送消息
                    if (channelUserMap.containsKey(v.getId()) && !v.equals(chatRecord.getUserId())) {
                        try {
                            channelUserMap.get(v).writeAndFlush(new TextWebSocketFrame(new ObjectMapper().writeValueAsString(chatRecord)));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            } else {
                //单聊
                //服务端向好友客户端发送消息
                if (channelUserMap.containsKey(chatRecord.getFriendId())) {
                    channelUserMap.get(chatRecord.getFriendId()).writeAndFlush(new TextWebSocketFrame(new ObjectMapper().writeValueAsString(chatRecord)));
                }
            }
            //返回给服务端消息
            log.info(chatRecord.toString());
            TextWebSocketFrame tws = new TextWebSocketFrame(new ObjectMapper().writeValueAsString(chatRecord));
            channelHandlerContext.channel().writeAndFlush(tws);
        }

    private ChatRecord exchangeChatMessage(Channel channel, String text) {

        JSONObject chatRecordJson = JSONObject.parseObject(text);
        ChatRecord chatRecord = JSON.toJavaObject(chatRecordJson,ChatRecord.class);
        chatRecord.setReaded(0);
        chatRecordService.insert(chatRecord);
        return chatRecord;
    }

}

