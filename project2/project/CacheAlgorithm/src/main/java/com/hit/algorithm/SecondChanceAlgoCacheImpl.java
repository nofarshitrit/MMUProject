package com.hit.algorithm;
import java.util.*;

public class SecondChanceAlgoCacheImpl<K,V> extends AbstractAlgoCache<K, V> {
	private LinkedHashMap<K,V> cache;
	private HashMap<K,Boolean> referenceBit;
	private V FirstElement = null;
	int recursiveLoop=1;
	
	public SecondChanceAlgoCacheImpl(int capacity) {
		super(capacity);
		this.cache= new LinkedHashMap<K,V>(capacity)
		{
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
				if(size() > capacity )
				{
					FirstElement=eldest.getValue();
					referenceBit.remove(eldest.getKey());
					return true;
				}
				else
				{
					FirstElement=null;
					return false;
				}

			}
		};
		
		this.referenceBit= new HashMap<>(capacity);
	}
	
	@Override
	public  V getElement(K key)
	{
		return cache.get(key);
	}
	
	@Override
	public  V putElement(K key, V value)
	{		
		if(cache.size()==capacity)
		{
			referenceBitChecker(key, value);
		}
		
		referenceBit.put(key,true);
		cache.put(key, value);
		return FirstElement;		
	}

	@Override
	public void removeElement(K key)
	{
		cache.remove(key);
		referenceBit.remove(key);
	}
	
	private void referenceBitChecker(K key, V value)
	{
		HashMap<K,Boolean> temp=new HashMap<>(capacity);
		temp=referenceBit;
		Iterator<K> it =temp.keySet().iterator();
		K k;
		V v=null;
		while(it.hasNext())
		{
			k=it.next();
			if(referenceBit.get(k))
			{
				referenceBit.put(k, false);
				v=cache.get(k);
				cache.remove(k);
				cache.put(k, v);
			}
			else
			{
				return;
			}
			
		}
	}
}
