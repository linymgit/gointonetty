package com.fy.interactive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class InterractiveClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client receive data is "+msg);
        ctx.writeAndFlush("client send data "+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client first request!!!");
        ctx.writeAndFlush("client first request!!!");
       // ctx.fireChannelActive();
    }
}
