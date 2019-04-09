package com.fy.heartcheck;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeartCheckClient {

    public static void main(String args[]){

        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        try {
            ChannelFuture cf = client.group(work).channel(NioSocketChannel.class).handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                }
            }).connect("localhost", 8010).sync();
            Channel channel = cf.channel();
            channel.closeFuture().sync();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            for(;;){
                channel.writeAndFlush(in.readLine());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
