package edu.ustc.mix.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class UpdatingValues {
	
	public static void main(String[] args) {
		
		String word = "";
		
		// 多个线程同时修改普通的HashMap可能导致链接丢失或形成回路，最终使得数据结构不可用
		// Java8中ConcurrentHashMap使用树型结构组织块，不再使用列表结构，当键实现Comparable接口时可以保证性能为O(log(n))
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		Long oldValue = map.get(word);
		Long newValue = oldValue == null ? 1 : oldValue + 1;
		map.put(word, newValue); // Error—might not replace oldValue
		System.out.println(map);
		
		do {
			oldValue = map.get(word);
			newValue = oldValue == null ? 1 : oldValue + 1;
		} while (!map.replace(word, oldValue, newValue));
		
		ConcurrentHashMap<String, LongAdder> map2 = new ConcurrentHashMap<>();
		map2.putIfAbsent(word, new LongAdder()).increment();
		
		map.compute(word, (k, v) -> v == null ? 1 : v + 1);
		
		map2.computeIfAbsent(word, k -> new LongAdder()).increment();
		
		// 键第一次加入Map时可以做一些特殊的事
		map.merge(word, 1L, (existingValue, newValue2) -> existingValue + newValue2);
		map.merge(word, 1L, Long::sum);
	}
}
