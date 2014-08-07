package com.medialets.util.sim.list;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;



/**
 * Very simple class to represent a set of longs in minimal space while
 * supporting economical queries for existence of a long in the set.  It uses 
 * binary search, so its average efficiency is O(log n) per query and O(n log n)
 * to set up.
 * 
 * Space consumed is about 20 bytes per lookup.
 * 
 * Implementation is a simple array of longs which are searched by
 * binary search.
 *  
 * @author peterc
 *
 */
public class SearchableList {
	private long [] list;
    // TODO: There is no good way to declare this with the rest. This indicates a design flaw.
	// possibly searchable list should return it's contents and let the parent do it? But that
	// violates encapsulation. Anything you do violates encapsulation. Thus, this
	// should probably not be in Utility, but in the same project as the hash function?
	public static String H_SIZE = "__F_SIZE__";
	public void writeValues(StringBuffer sb){
		sb.append("\n");
		sb.append(H_SIZE);
		sb.append(",");
		sb.append(new Integer(list.length).toString());
		sb.append("\n"); 
		 
		for(int i=0; i<list.length; i++){
			sb.append(list[i]);
			if(i<list.length -1){
				sb.append(",");
				if(i%6 == 5 && i>0){
					sb.append("\n");
				} 
			}
			else{
				sb.append("\n\n");
			}
		} 
	}
	
//	private static final Logger log = 
//		Logger.getLogger("utility.list.SearchableList");
	/**
	 * Initialize a SearchableList with a collection of longs
	 * @param longs
	 */
	public SearchableList(List<Long>longs){ 
		Collections.sort(longs);
		list = new long[longs.size()];
		for(int i=0; i<longs.size(); i++){
			list[i]=longs.get(i);
		}
	}
	
	public SearchableList(Set<Long>longs){ 
		List<Long> tmp = new ArrayList<Long>();
		tmp.addAll(longs);
		Collections.sort(tmp);
		list = new long[longs.size()];
		for(int i=0; i<longs.size(); i++){
			list[i]=tmp.get(i);
		}
	}
	/**
	 * Answer whether the number is contained in the list.
	 * @param l
	 * @return
	 */
	public boolean search(long l){
		return search(list, 0, list.length -1, l);
			 
	}

	/**
	 * Answer whether the given val is contained in the defined portion of 
	 * the given storage array. The array is presumed to have been sorted
	 * in the constructor.
	 * @param list
	 * @param start
	 * @param end
	 * @param val
	 * @return
	 */
	private boolean search(long [] list, int start, int end, long val){
		if(start>=end){
			return list[start]==val;
		}
		if(list[start]==val || list[end]==val){
			return true;
		}
		int tmp = start + (end-start)/2;
		if(list[tmp] <= val){
			return search(list, tmp, end-1, val);
		}
		else {
			return search(list, start+1 , tmp, val);
		} 
	}
}
