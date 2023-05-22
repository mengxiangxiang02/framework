package com.example.demo.corejava;

import java.util.*;

public class CollectionTest {
    public static void main(String args[])
    {
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("zhangsan");
        linkedList.add("lisi");
        linkedList.add("wangwu");

        List<String> strings = linkedList.subList(1, 2);
        strings.add("insert");
        System.out.println(strings);



        //集合转数组
        String[] strings0 = strings.toArray(new String[0]);


        String[] strings1 = strings.toArray(new String[strings.size()]);

        String[] a=new String[]{"mxx","mxx1"};
        List<String> listConvert = Arrays.asList(strings1);

        ListIterator<String> iterator=linkedList.listIterator();
        String first=iterator.next();
        iterator.set("张三");
        System.out.println(linkedList.toString());


        Set<String> hashSet=new HashSet<>();
        hashSet.add("mxx1");
        hashSet.add("mxx2");
        hashSet.add("mxx3");
        for(String s:hashSet)
        {
            System.out.println(s);
        }
        Iterator<String> iteratorSet = hashSet.iterator();
        while(iteratorSet.hasNext())
        {
            System.out.println(iteratorSet.next());
        }

        Set<String> treeSet=new TreeSet<>();
        treeSet.add("mxx1");
        treeSet.add("mxx2");
        treeSet.add("mxx3");
        for(String s:treeSet)
        {
            System.out.println(s);
        }
        Iterator<String> iteratorTreeSet = treeSet.iterator();
        while(iteratorTreeSet.hasNext())
        {
            System.out.println(iteratorTreeSet.next());
        }
    }
}
