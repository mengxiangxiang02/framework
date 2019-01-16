package com.architecture;

/**
 * @Auther: mengxiangxiang
 * @Date: 2019/1/10 16:34
 * @Description:
 */
public class Solution {
    public static int  atMostNGivenDigitSet(String[] D, int N) {
        if(N<=0||D==null||D.length==0) return 0;
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K+1];
        dp[K] = 1;

        for (int i = K-1; i >= 0; --i) {
            // compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d: D) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(D.length, K-i-1);
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i+1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i);
        return dp[0];
    }

    public static void main(String args[])
    {
        String[] D= new String[]{"1", "2", "4","6"};
        int N=300;
        int i = atMostNGivenDigitSet(D, N);
        System.out.println(i);
    }
}
