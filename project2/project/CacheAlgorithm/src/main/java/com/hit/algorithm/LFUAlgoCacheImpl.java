package com.hit.algorithm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;



public class LFUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K, V>{

	private Map<K,V> cacheLfu;
	private Map<K,Integer> countUseCache;
	private V EldestElement = null;
	
	public LFUAlgoCacheImpl(int capacity) {
		super(capacity);
		this.cacheLfu= new LinkedHashMap<K,V>(capacity,1.0f,true){
		
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

				if(size() > capacity)
				{
					K KeyOfLowUsage = getKeyOfLowUsage();
					EldestElement = getElement(KeyOfLowUsage);
					removeElement(KeyOfLowUsage);
					return false;
				}
				else 
				{
					EldestElement=null;
					return false;	
				}
			}
		};
		
		this.countUseCache= new LinkedHashMap<>(capacity);
	}
	
	@Override
	public  V getElement(K key)
	{
		if (countUseCache.containsKey(key))
			{
				Integer counter =  countUseCache.get(key); 
				countUseCache.replace(key, counter, ++counter);
			}
		
	return cacheLfu.get(key);

	}
	
	@Override
	public  V putElement(K key, V value)
	{
		cacheLfu.put(key, value);
		countUseCache.put(key, 0);
		return EldestElement;		
	}
	
	// method that return the key of the low use from cachecount.
	private K getKeyOfLowUsage()
	{
		Integer minValue = (Collections.min(countUseCache.values() ));
		for(Entry<K, Integer> entry: countUseCache.entrySet())
		{
			if(entry.getValue()== minValue)
			{
				return entry.getKey();
			}
		}
		return null;
	}
		
	@Override
	public  void removeElement(K key)
	{
		cacheLfu.remove(key);
		countUseCache.remove(key);
	}
}
