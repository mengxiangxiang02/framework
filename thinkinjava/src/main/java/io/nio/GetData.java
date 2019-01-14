package io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/13
 * @description :用各种视图缓冲器操作byteBuffer
 */
public class GetData {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        System.out.println(buffer.position());

        buffer.asCharBuffer().put("Howdy");
        char c;
        while((c=buffer.getChar())!=0)
        {
            System.out.print(c);
        }
        System.out.println();

        buffer.rewind();//返回到buffer的起始位置
        buffer.asIntBuffer().put(4541);
        System.out.println(buffer.getInt());

        buffer.rewind();//返回到buffer的起始位置
        buffer.asShortBuffer().put((short)4541); //short最大 2^16位
        System.out.println(buffer.getShort());

        buffer.rewind();//返回到buffer的起始位置
        buffer.asDoubleBuffer().put(4541);
        System.out.println(buffer.getDouble());

        //视图还允许我们成批的读取基本类型数据
        buffer.rewind();
        IntBuffer ib=buffer.asIntBuffer();
        ib.put(new int[]{11,42,55,25,258});
        System.out.println(ib.get(3));
        ib.put(3,33);
        //设置一个新的limit
        ib.flip();
        while(ib.hasRemaining())
        {
            int i=ib.get();
            System.out.println(i);
        }
    }
}
