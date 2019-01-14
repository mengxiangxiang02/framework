package io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/11
 * @description :从一个文件读到缓冲器中然后写到另一个文件中
 */
public class ChannelCopy {
    private static final int BSIZE=1024;

    public static void main(String[] args) throws IOException {
        if(args.length!=2)
        {
            System.out.println("arguments: sourcefile destfile:"+args);
            System.exit(1);
        }

        FileChannel in=new FileInputStream(args[0]).getChannel(),
                out=new FileOutputStream(args[1]).getChannel();

        //通过缓冲器传输数据
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        while(in.read(buffer)!=-1)
        {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }

        //两个channel之间也可以直接传递数据
        in.transferTo(0,in.size(),out);
    }
}
