package com.fy.base;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;

public class BaseInit extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch){

            ChannelPipeline p = ch.pipeline();
            p.addLast("abc", new HttpServerCodec())
             .addLast("baseHandler",new BaseHandler());


    }
}
