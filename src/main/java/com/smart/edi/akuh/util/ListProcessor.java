/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John.Simiyu
 */
public class ListProcessor {
    
    	// Generic function to partition a list into sublists of size n each
	// in Java using List.subList() (The final list might have less items)
	public static<T> List[] partition(List<T> list, int n)
	{
		// get size of the list
		int size = list.size();

		// calculate number of partitions --> m sublists each of size n
		int m = size / n;
		if (size % n != 0)
			m++;

		// create m empty lists
		List<T>[] partition = new ArrayList[m];
		for (int i = 0; i < m; i++)
		{
			int fromIndex = i*n;
			int toIndex = (i*n + n < size) ? (i*n + n) : size;

			partition[i] = new ArrayList(list.subList(fromIndex, toIndex));
		}

		// return the lists
		return partition;
	}
    
}
