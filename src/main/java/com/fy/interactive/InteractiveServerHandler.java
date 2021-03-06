package com.fy.interactive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class InteractiveServerHandler  extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server receive data is "+msg);
        ctx.writeAndFlush("server send data"+ UUID.randomUUID());
    }
}
