package com.fy.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch->{
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress()+"发送的信息是："+msg+"\n");
            }else{
                ch.writeAndFlush("[自己]发的信息"+msg+"\n");
            }
        });
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.err.println("client added");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + " , 加入了服务器\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.err.println("client remove");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + " , 离开了服务器\n");
        channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channel active");
        channelGroup.writeAndFlush(ctx.channel().remoteAddress() + "上线了\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channel inactive");
        channelGroup.writeAndFlush(ctx.channel().remoteAddress() +"下线了\n");
    }
}
