package com.architecture.factorymethod;
//只是需要遍历一堆数据，那么只需要2个方法就可以了

/**
 * 抽象产品是Iterator接口，具体产品就是Collection接口的实现中对Iterator接口的实现，
 * 构造者是Collection接口，其提供的工厂方法就是Iterator iterator();
 ，具体构造者就是Collection的实现。
 而工厂方法模式的结构，也就是由前面加粗的4部分组成。
 */

public interface Iterator<T> {
	boolean hasNext();	//是否还有下一个元素
	T next();			//得到下一个元素
}
