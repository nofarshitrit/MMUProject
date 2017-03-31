package com.hit.algorithm;

import java.util.List;


public abstract class AbstractAlgoCache <K,V>  implements IAlgoCache <K,V>
{
	
	protected int capacity;
	
	public	AbstractAlgoCache(int capacity)
	{
		this.capacity=capacity;
	}
	
	public abstract V getElement(K key);
	
	public abstract V putElement(K key, V value);
		
	public abstract void removeElement(K key);
	
	
}
