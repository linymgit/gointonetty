package com.fy.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileInputStreamTest {


    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/main/java/com/fy/nio/Test.txt");
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(512);

            channel.read(buffer);

            buffer.flip();

             while (buffer.hasRemaining()) {
                  System.err.println(buffer.get());
             }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
