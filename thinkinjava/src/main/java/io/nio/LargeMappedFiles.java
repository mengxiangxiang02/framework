package io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/13
 * @description :内存映射文件，允许我们创建和修改那些因为太大而不能放入内存的文件。
 */
public class LargeMappedFiles {
    private static final int BSIZE=0X8FFFFFF;//128MB
    public static void main(String[] args) throws IOException {

        FileChannel rw = new RandomAccessFile("D:\\backup.sql", "rw").getChannel();
        MappedByteBuffer map = rw.map(FileChannel.MapMode.READ_WRITE, 0, BSIZE);
        while(map.hasRemaining())
        {
            char aChar = (char)map.get();
            System.out.println(aChar);
        }
    }
}
