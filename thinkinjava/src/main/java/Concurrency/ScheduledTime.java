package Concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * @Auther: mengxiangxiang
 * @Date: 2019/1/17 15:53
 * @Description:定义一个定时器
 */
public class ScheduledTime {
    private static long interval = 15; //s

    public static void main(String args[])
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                    try {
                        //postMonitorData();
                    } catch (Throwable ex) {
                    }

                }
        }, 3, interval, TimeUnit.SECONDS);
    }

    /**
     * 性能监控
     */
    private void postMonitorData() {
       /* Map<String, ResponseTimeCounter> counters = CounterRepository.getCounters();
        Collection<ResponseTimeCounter> responseTimeCounters = counters.values();
        if (responseTimeCounters.isEmpty()) {
            return;
        }

        List<List<NameValuePair>> actions = new ArrayList<List<NameValuePair>>();
        for (ResponseTimeCounter c : responseTimeCounters) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appId", appId));
            params.add(new BasicNameValuePair("version", SarsMonitorContext.VERSION));
            params.add(new BasicNameValuePair("counter", Utils.JSON.toJSONString(c)));
            params.add(new BasicNameValuePair("prefix", prefix));
            actions.add(params);
        }
        counters.clear();

        if (!actions.isEmpty()) {
            for (List<NameValuePair> params : actions) {
                HttpClientResult result = HttpClientUtils.execPostMethod(postUrl, params);
                if (result.getErrorCode() != 200) {
                    logger.error("SEND MONITOR DATA FAILED. ErrorCode=" + result.getErrorCode() + ", ErrorMessage=" + result.getErrorMessage());
                }
            }
        }*/
    }

    /**
     * MQ堆积量监控
     */
    private void postMqStatData() {
        /*Map<String, SarsMqStat> mqStatMap = SarsMqStatRepository.getMqStats();
        Collection<SarsMqStat> mqStatList = mqStatMap.values();
        if (mqStatList.isEmpty()) {
            return;
        }

        List<List<NameValuePair>> actions = new ArrayList<List<NameValuePair>>();
        for (SarsMqStat s : mqStatList) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appId", appId));
            params.add(new BasicNameValuePair("version", SarsMonitorContext.VERSION));
            params.add(new BasicNameValuePair("mqStat", Utils.JSON.toJSONString(s)));
            params.add(new BasicNameValuePair("prefix", prefix));
            actions.add(params);
        }
        mqStatMap.clear();

        for (List<NameValuePair> params : actions) {
            HttpClientResult result = HttpClientUtils.execPostMethod(postUrl, params);
            if (result.getErrorCode() != 200) {
                logger.error("SEND MQ STAT DATA FAILED. ErrorCode=" + result.getErrorCode() + ", ErrorMessage=" + result.getErrorMessage());
            }
        }*/
    }

    /**
     * JVM参数监控
     */
    private void postJvmStatData() {
       /* SarsJvmStat jvmStat = SarsJvmMonitor.currentJvmStat();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appId", appId));
        params.add(new BasicNameValuePair("version", SarsMonitorContext.VERSION));
        params.add(new BasicNameValuePair("jvmStat", Utils.JSON.toJSONString(jvmStat)));
        params.add(new BasicNameValuePair("prefix", prefix));
        HttpClientResult result = HttpClientUtils.execPostMethod(postUrl, params);
        if (result.getErrorCode() != 200) {
            logger.error("SEND JVM STAT DATA FAILED. ErrorCode=" + result.getErrorCode() + ", ErrorMessage=" + result.getErrorMessage());
        }*/
    }

    /**
     * CPU监控
     */
    private void postCpuUsageData() {
       /* SarsCpuStat cpuStat = SarsCpuMonitor.currentCpuUsage();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appId", appId));
        params.add(new BasicNameValuePair("version", SarsMonitorContext.VERSION));
        params.add(new BasicNameValuePair("cpuStat", Utils.JSON.toJSONString(cpuStat)));
        params.add(new BasicNameValuePair("prefix", prefix));
        HttpClientResult result = HttpClientUtils.execPostMethod(postUrl, params);
        if (result.getErrorCode() != 200) {
            logger.error("SEND CPU STAT DATA FAILED. ErrorCode=" + result.getErrorCode() + ", ErrorMessage=" + result.getErrorMessage());
        }*/
    }

    /**
     * 磁盘剩余空间监控
     */
    private void postDiskFreeSpaceData() {
        /*SarsDiskStat diskStat = SarsDiskMonitor.currentDiskStat();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appId", appId));
        params.add(new BasicNameValuePair("version", SarsMonitorContext.VERSION));
        params.add(new BasicNameValuePair("diskStat", Utils.JSON.toJSONString(diskStat)));
        params.add(new BasicNameValuePair("prefix", prefix));
        HttpClientResult result = HttpClientUtils.execPostMethod(postUrl, params);
        if (result.getErrorCode() != 200) {
            logger.error("SEND DISK STAT DATA FAILED. ErrorCode=" + result.getErrorCode() + ", ErrorMessage=" + result.getErrorMessage());
        }*/
    }
}
