package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUAlgoCacheImpl<K, V>extends AbstractAlgoCache<K, V> {
	
	private LinkedHashMap<K,V> cache ;
	private V EldestElement = null;
	
	public LRUAlgoCacheImpl(int capacity){
		super(capacity);
		
		this.cache= new LinkedHashMap<K,V>(capacity,1.0f,true){
		
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

				if(size() > capacity)
				{
					EldestElement=eldest.getValue();
					return true;
				}
				else
				{
					EldestElement=null;
					return false;	
				}
			}
		};
	}

	public  V getElement(K key){
		
		return this.cache.get(key);
	}
	
	public  V putElement(K key, V value){
		cache.put(key, value);
		return EldestElement;
	}
		
	public void removeElement(K key){
		
		if (this.cache.containsKey(key))
			this.cache.remove(key);
	}
	
}
