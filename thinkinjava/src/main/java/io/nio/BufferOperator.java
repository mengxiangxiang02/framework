package io.nio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/13
 * @description :
 */
public class BufferOperator {
    private static final int BSIZE = 1024;
    static IntBuffer intBuffer = null;

    public static void main(String[] args) throws IOException {
        intBuffer = IntBuffer.allocate(BSIZE);//创建一个IntBuffer实例
        System.out.println(intBuffer.position());//输出0
        System.out.println(intBuffer.limit());//输出1024
        System.out.println(intBuffer.capacity());//输出1024

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        System.out.println(intBuffer.position());//输出5
        System.out.println(intBuffer.limit());//输出1024
        System.out.println(intBuffer.capacity());//输出1024

        intBuffer.flip();
        System.out.println(intBuffer.position());//输出0
        System.out.println(intBuffer.limit());//输出5
        System.out.println(intBuffer.capacity());//输出1024
        for (int i = 0; i < 5; i++) {
            intBuffer.get();
        }
        System.out.println(intBuffer.position());//输出5
        System.out.println(intBuffer.limit());//输出5
        System.out.println(intBuffer.capacity());//输出1024
        intBuffer.rewind();
        System.out.println(intBuffer.position());//输出0
        System.out.println(intBuffer.limit());//输出5
        System.out.println(intBuffer.capacity());//输出1024


        intBuffer.clear();
        System.out.println(intBuffer.position());//输出0
        System.out.println(intBuffer.limit());//输出1024
        System.out.println(intBuffer.capacity());//输出1024



    }


}
