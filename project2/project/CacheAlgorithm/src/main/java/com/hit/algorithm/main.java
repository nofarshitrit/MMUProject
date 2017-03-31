package com.hit.algorithm;
public class main{

	
public static void main(String[] args) {
	MMU m = new MMU(new LRUAlgoCacheImpl(10));
	m.put(7, 9);
	System.out.println(m.get(7));
	
	MMU m1 = new MMU(new LRUAlgoCacheImpl(10));
	m1.put(2, "mati");
	System.out.println(m1.get(2));
	
	MMU m2 = new MMU(new SecondChanceAlgoCacheImpl(3));
	m2.put(1, "a");
	m2.put(2, "b");
	m2.put(3, "c");
	System.out.println(m2.put(4, "d"));
	System.out.println(m2.put(5, "e"));
	//System.out.println(m2.put(5, "e"));
	}
}
