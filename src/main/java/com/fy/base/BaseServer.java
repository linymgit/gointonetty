package com.fy.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BaseServer {

    public static void main(String[]args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        try {
            ChannelFuture cf = server.group(boss, work).channel(NioServerSocketChannel.class).childHandler(new BaseInit()).bind(8088).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
