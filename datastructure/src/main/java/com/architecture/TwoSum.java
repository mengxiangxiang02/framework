package com.architecture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * 1:暴力破解，两层循环，需要时间复杂度为O(n2)
 * 2：通过空间换时间，建立一个hashmap,对于每个数组的值x ，先判断target-x是否在map中，如果不在，则将改值放到map中，继续循环。
 * 引申知识点：1 hashmap的底层实现逻辑是什么？hashmap如果查找一个key
 */
class Solution{
    public int[] twoSum(int[] nums,int target)
    {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(target-nums[i]))
            {
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}

public class TwoSum {
    public static void main(String[] args) throws Exception
    {
        try
        {
            Solution solution=new Solution();

            //测试用例1
            int nums[]=new int[]{2,7,11,15};
            int[] answer = solution.twoSum(nums, 9);

            int[] expect=new int[]{0,1};//测试用例的期望值

            //因为answer 和expect 指向的是不同的引用对象的地址，不能通过==判断两个数组是否相同 assert (answer==new int[]{0,1});
            //如果判断两个数组是否相同，需要进行循环

            //先用特殊值处理，如果两个数组都是空数组，直接认定两个数组相同
            if((expect.length==0||expect==null)&&(answer.length==0||answer==null))
            {
                System.out.println(true);
                return;
            }
            if(answer!=null&&expect!=null)
            {
               if(answer.length==expect.length)
               {
                  Arrays.sort(answer);
                  Arrays.sort(expect);
                  for(int i=0;i<answer.length;i++)
                  {
                      if(answer[i]!=expect[i])
                      {
                          System.out.println(false);
                      }
                  }
               }
               else {
                   System.out.println(false);//如果两个数组值不同，直接返回false
               }
            }else //如果有一个为空，另外一个不为空，返回false
            {
                System.out.println(false);
            }
            System.out.println(true);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
