package com.hit.algorithm;

public class MMU<K,V> {

IAlgoCache<K, V> algo;	
	public  MMU(IAlgoCache<K, V> algo) {
	
	 this.algo = algo;
	}
	
	public V put(K key,V value)
	{
		return algo.putElement(key, value);
	}

	public V get(K key)
	{
		return algo.getElement(key);
	}
	
	public void remove(K key)
	{
		algo.removeElement(key);
	}
}
