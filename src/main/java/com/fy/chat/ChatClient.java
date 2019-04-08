package com.fy.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient {

    public static void main(String []args){
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        try {
            ChannelFuture cf = client.group(work).channel(NioSocketChannel.class).handler(new ChatClientInitialize()).connect("localhost", 8089);

            Channel channel = cf.channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(;;){

                channel.writeAndFlush(br.readLine()+"\r\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully();
        }
    }
}
