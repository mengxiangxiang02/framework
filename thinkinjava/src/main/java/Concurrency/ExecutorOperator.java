package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: mengxiangxiang
 * @Date: 2019/1/18 10:09
 * @Description:执行器操作
 */
public class ExecutorOperator {
    public static  void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.shutdown();
        executorService.isShutdown();
    }
}
