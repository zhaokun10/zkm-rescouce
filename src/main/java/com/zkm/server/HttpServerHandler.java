package com.zkm.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
       log.info("我开始连接啦");
        String uri = req.uri();
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://" + req.headers().get(HttpHeaderNames.HOST) + uri,
                null, false);
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx
                    .channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
        Channel channel = ctx.channel();
        System.out.println(uri);
        if(Objects.equals("chat", uri.substring(1, uri.lastIndexOf("?")))){
            Integer userId = Integer.valueOf(uri.substring(uri.lastIndexOf("?")+1,uri.length()));
            MessageServerHandler.channelUserMap.put(userId,channel);
        }else if(Objects.equals("friend", uri.substring(1, uri.lastIndexOf("?")))){

        }
    }
}
