package com.architecture.util;

import java.util.zip.CRC32;

/**
 * @author : mengxiangxiang
 * @Date :   2018/12/30
 * @description :根据订单号计算Hash值
 */
public class Hash {
    /**
     * 根据订单号产生一个HASH值
     * @param key
     * @return
     */
    private static Long crc32Hash(String key) {
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return crc32.getValue();
    }
    public static void main(String args[])
    {
        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++)
        {
            Long aLong = crc32Hash("000001");
            //System.out.println(aLong);
            //对hash值取模得到对应的表
            System.out.println(aLong%10);
        }
        long end=System.currentTimeMillis();

        System.out.print(end-start);
        System.out.print("ms");

    }
}
