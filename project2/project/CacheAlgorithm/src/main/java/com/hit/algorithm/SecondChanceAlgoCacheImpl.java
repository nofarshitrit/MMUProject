package com.hit.algorithm;
import java.util.*;

public class SecondChanceAlgoCacheImpl<K,V> extends AbstractAlgoCache<K, V> {
	
	private LinkedHashMap<K,V> cache;
	private LinkedHashMap<K,Boolean> referenceBit;
	private Random randomReferenceBit;
	private V FirstElement = null;
	int recursiveLoop=1;
	public SecondChanceAlgoCacheImpl(int capacity) {
		super(capacity);
		this.cache= new LinkedHashMap<K,V>(capacity){
		
			
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				K eldestKey;
				
				if(size() > capacity && recursiveLoop!=size())
				{
					eldestKey=eldest.getKey();
					
					if(referenceBit.get(eldestKey))
					{
						recursiveLoop++;
						
						if(recursiveLoop == size())
						{
							return false;
						}
						
						FirstElement = eldest.getValue();
						referenceBit.put(eldestKey, false);
						removeElement(eldestKey);
						putElement(eldestKey, FirstElement);
			
						return false;
					}
					else
					{
						FirstElement = eldest.getValue();
						referenceBit.remove(eldestKey);
						return true;
					}
				}
				else 
				{
					FirstElement=null;
					return false;	
				}
				
			}
		};
		
		this.referenceBit= new LinkedHashMap<>(capacity);
		this.randomReferenceBit= new Random();
	}
	
	@Override
	public  V getElement(K key)
	{
		return cache.get(key);
	}

	@Override
	public  V putElement(K key, V value)
	{
		
		referenceBit.put(key,randomReferenceBit.nextBoolean());
		cache.put(key, value);
		return FirstElement;		
	}

	@Override
	public void removeElement(K key)
	{
		cache.remove(key);
		referenceBit.remove(key);
	}
}
