package com.architecture.singleton;
/**
 * 简单的单例模式，
 * @author Administrator
 *
 */
public class SimpleSingleton {
	private static SimpleSingleton instance;
	private SimpleSingleton(){}
	//不能用于多线程
	public static SimpleSingleton getIntance(){
		if(instance == null)
			instance = new SimpleSingleton();
		return instance;
	}

	//线程安全的
	public static SimpleSingleton getInstance() {
		if(instance != null){//懒汉式
			return instance;
		}else{
			synchronized (SimpleSingleton.class) {
				if(instance == null){//二次检查
					instance = new SimpleSingleton();
				}
			}
		}
		return instance;
	}
}
