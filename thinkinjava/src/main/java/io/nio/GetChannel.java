package io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/11
 * @description :文件相当于一个通道。与通道交互的称为缓冲器，通过操作缓冲器来完成交互
 */
public class GetChannel {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws IOException {
        //获取文件输出流通道
        FileChannel fc=new FileOutputStream("D:\\tmp\\data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();

        fc=new RandomAccessFile("D:\\tmp\\data.txt","rw").getChannel();
        fc.position(fc.size());//移动到文件的最后
        fc.write(ByteBuffer.wrap(" append text".getBytes()));
        fc.close();

        fc=new FileInputStream("D:\\tmp\\data.txt").getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        fc.read(buffer);//从通道中像缓冲器中写入字节

        buffer.flip();

        while(buffer.hasRemaining())
        {
            System.out.print((char)buffer.get());
        }


    }
}
