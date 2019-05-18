package com.fy.nio;

import java.nio.IntBuffer;
import java.util.Random;

public class NioTest {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i=0;i<10;i++){
            buffer.put(new Random().nextInt(10));
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            System.err.println(buffer.get());
        }

    }
}
