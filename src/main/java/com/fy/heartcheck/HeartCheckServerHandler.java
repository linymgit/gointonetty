package com.fy.heartcheck;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartCheckServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()){
                case ALL_IDLE:
                    System.err.println("all idle");
                    break;
                case READER_IDLE:
                    System.err.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.err.println("write idle");
                    break;
            }
        }
    }


}
