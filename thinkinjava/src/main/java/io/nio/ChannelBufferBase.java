package io.nio;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * @author : mengxiangxiang
 * @Date :   2018/12/9
 * @description :定义通道filechannel和缓冲器bytebuffer
 */
public class ChannelBufferBase {
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        // 获取通道，该通道允许写操作
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        // 将字节数组包装到缓冲区中
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        // 关闭通道
        fc.close();

        // 随机读写文件流创建的管道
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        // fc.position()计算从文件的开始到当前位置之间的字节数
        System.out.println("此通道的文件位置：" + fc.position());
        // 设置此通道的文件位置,fc.size()此通道的文件的当前大小,该条语句执行后，通道位置处于文件的末尾
        fc.position(fc.size());
        // 在文件末尾写入字节
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();

        // 用通道读取文件
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        // 将文件内容读到指定的缓冲区中
        fc.read(buffer);
        //buffer.flip();一定得有，如果没有，就是从文件最后开始读取的，当然读出来的都是byte=0时候的字符。
        //通过buffer.flip();这个语句，就能把buffer的当前位置更改为buffer缓冲区的第一个位置。
        buffer.flip();//此行语句一定要有
        while (buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }
        fc.close();
    }
}
