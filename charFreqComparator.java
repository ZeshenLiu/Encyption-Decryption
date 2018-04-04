package edu.upenn.cis573.hwk1;

import java.util.*;

/**
 * charFreqComparator implements the comparator for the use of sorting the map.entry list
 * @author Jason
 *
 */
public class charFreqComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Integer freq1 = (Integer) (((Map.Entry<Character,Integer>) o1).getValue());
		Integer freq2 = (Integer) (((Map.Entry<Character,Integer>) o2).getValue());
		return freq1 - freq2;
	}

}
