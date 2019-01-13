package com.architecture.sort;

/**
 * @author : mengxiangxiang
 * @Date :   2018/12/24
 * @description :快排算法
 */
public class QucikSort {
    public static void main(String args[])
    {
        int[] data={45,58,56,56,98,36,91,25,67};
        sort(data,0,data.length - 1);
        for(int i=0;i<data.length;i++)
        {
            System.out.println(data[i]);
        }
     }

     public static void sort(int[] data,int low,int high)
     {
         if(low<high)
         {
             int partiction = partiction(data, low, high);
             sort(data,0,partiction-1);
             sort(data,partiction+1,high);
         }

     }

     public static int partiction(int[] data,int low,int high)
     {
         int middle=data[low];
         while(low<high)
         {
            while(low<high&&data[high]>=middle)
            {
                high--;
            }
            if(low<high)
            {
                data[low]=data[high];
            }
            while(low<high&&data[low]<middle)
            {
                low++;
            }
             if(low<high)
             {
                 data[high]=data[low];
             }
             data[low]=middle;
         }
         return low;
     }
}
