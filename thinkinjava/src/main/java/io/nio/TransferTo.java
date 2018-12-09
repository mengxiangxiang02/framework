package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author : mengxiangxiang
 * @Date :   2018/12/9
 * @description :通道相连
 */
public class TransferTo {
    public static void main(String[] args) throws Exception {
        if(args.length!=2)
        {
            System.out.println("arguments: sourcefile destfile" );
            System.exit(1);
        }
        FileChannel in=new FileInputStream(args[0]).getChannel();
        FileChannel out=new FileOutputStream(args[1]).getChannel();

        out.transferFrom(in,0,in.size());


    }
}
