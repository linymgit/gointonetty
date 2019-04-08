package com.fy.interactive;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class InteractiveClient {

    public static void main(String []args){

        NioEventLoopGroup work = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();

        try {
            ChannelFuture cf = client.group(work).channel(NioSocketChannel.class).handler(new InteractiveClientInitialize()).connect("localhost", 8088).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }
    }
}
