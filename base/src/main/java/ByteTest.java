import java.lang.reflect.Constructor;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ByteTest implements Enumeration {
    public static void main(String args[])
    {
        ByteBuffer buffer=ByteBuffer.allocate(1000);
        IntBuffer intBuffer=IntBuffer.allocate(200);
        ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String,String>();
        Integer x=5;
        Integer y=5;
        System.out.println(x==y);//true

        Integer x1=128;
        Integer y1=128;
        System.out.println(x1==y1);//false

        int i=5;
        short var=5;

//        Scanner scanner=new Scanner(System.in);
//        String inputString=scanner.next();
//        System.out.println("输入的内容为"+inputString);

        Constructor<?>[] constructors = ByteTest.class.getConstructors();
        System.out.println(Math.round(-1.5));

    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public Object nextElement() {
        return null;
    }


}
