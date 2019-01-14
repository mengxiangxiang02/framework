package io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/13
 * @description :
 */
public class BufferOperator {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.mark());

        buffer.asCharBuffer().put('a');
        buffer.get();
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.mark());
        buffer.rewind();
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.mark());
    }
}
