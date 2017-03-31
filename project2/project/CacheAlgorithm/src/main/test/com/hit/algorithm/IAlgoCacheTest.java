package com.hit.algorithm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.*;

public class IAlgoCacheTest {

	//testing the eldest element, in this case 'b' should be the eldest
	@Test
	public void EldestLRUAlgoUnitTest()
	{
		LRUAlgoCacheImpl LRUAlgo = new LRUAlgoCacheImpl(3);
		
		LRUAlgo.putElement(1, "a");
		LRUAlgo.putElement(2, "b");
		LRUAlgo.getElement(1);
		LRUAlgo.putElement(3, "c");
		
		assertEquals("b",LRUAlgo.putElement(4, "d"));		
	}
	
	//testing "overflow" while inserting elements
	@Test
	public void OverflowLRUAlgoUnitTtest()
	{
		LRUAlgoCacheImpl LRUAlgo = new LRUAlgoCacheImpl(3);
		
		try
		{
			LRUAlgo.putElement(1, "a");
			LRUAlgo.putElement(2, "b");
			LRUAlgo.putElement(3, "c");
			LRUAlgo.putElement(4, "d");
			LRUAlgo.putElement(5, "e");
		}
		catch (Exception e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void FunctionsLRUAlgoUnitTest()
	{
		LRUAlgoCacheImpl LRUAlgo = new LRUAlgoCacheImpl(3);
		
		//test get and put functions
		LRUAlgo.putElement(1, "a");
		LRUAlgo.putElement(2, "b");
		LRUAlgo.putElement(3, "c");
		assertEquals("a",LRUAlgo.getElement(1));
		
		//test remove function
		LRUAlgo.removeElement(1);
		assertNull(LRUAlgo.getElement(1));
	}
	@Test
	public void EldestLfUAlgoUnitTest()
	{
		LFUAlgoCacheImpl LFUAlgo = new LFUAlgoCacheImpl(3);
		
		LFUAlgo.putElement(1, "a");
		LFUAlgo.putElement(2, "b");
		LFUAlgo.getElement(1);
		LFUAlgo.getElement(1);
		LFUAlgo.getElement(2);
		LFUAlgo.putElement(3, "c");
		LFUAlgo.getElement(3);
		LFUAlgo.getElement(3);
		
		
		assertEquals("b",LFUAlgo.putElement(4, "d"));	
	}
	
	public void NullLfUAlgoUnitTest()
	{
		LFUAlgoCacheImpl LFUAlgo = new LFUAlgoCacheImpl(4);
		
		LFUAlgo.putElement(1, "a");
		LFUAlgo.putElement(2, "b");
		LFUAlgo.getElement(1);
		LFUAlgo.getElement(1);
		LFUAlgo.getElement(2);
		LFUAlgo.putElement(3, "c");
		LFUAlgo.getElement(3);
		LFUAlgo.getElement(3);
		assertEquals(null,LFUAlgo.putElement(4, "d"));	
	}
	
	@Test
	public void FunctionsLFUAlgoUnitTest()
	{
		LFUAlgoCacheImpl LFUAlgo = new LFUAlgoCacheImpl(3);
		
		//test get and put functions
		LFUAlgo.putElement(1, "a");
		LFUAlgo.putElement(2, "b");
		LFUAlgo.putElement(3, "c");
		assertEquals("a",LFUAlgo.getElement(1));
		assertEquals("b",LFUAlgo.getElement(2));
		
		//test remove function
		LFUAlgo.removeElement(1);
		assertNull(LFUAlgo.getElement(1));
	}
	
	@Test
	public void SecondChanceAlgoTest()
	{
		/** 
		*reference bits are initialized to true for the first time 
		*after the "second chance" they will be false
		*/
		SecondChanceAlgoCacheImpl algo = new SecondChanceAlgoCacheImpl(3);
		
		algo.putElement(1, "a"); //reference bit = true
		algo.putElement(2, "b"); //reference bit = true
		algo.putElement(3, "c"); //reference bit = true
		assertEquals("a", algo.putElement(4, "d")); //reference bit of a,b,c = false and for d = true
		assertEquals("d", algo.getElement(4));
		assertEquals("b", algo.putElement(5, "e"));
	}
	
}
	

