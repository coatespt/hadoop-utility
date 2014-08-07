package com.medialets.util.sim.word;

import java.util.logging.Logger;


/**
 * Converts strings to words of standard form or to null, allowing for policies
 * such as case-sensitive/case-insensitive, ignoring/not-ignoring numbers, acronyms,
 * or anything else you might wish to have a policy on.
 * 
 * TODO: There is nothing here but a null conversion. 
 * TODO: Implement use of the policies.
 *   
 * @author peterc
 *
 */
public class WordFilterBasicImpl implements IWordFilter{ 
	private static final Logger log = 
		Logger.getLogger("utility.word.WordFilter");
	private FilterPolicy policy;
	
	/**
	 * Apply the configured filters to the input string and return the result. 
	 * This is currently a null-filter.
	 * 
	 * @param str
	 * @return
	 */
	public String filter(String str){
		log.warning("WordFilter.filter(string) IS AN EMPTY IMPLEMENTATION.");
		return str;
	}
	public FilterPolicy getPolicy() {
		return policy;
	}
	public void setPolicy(FilterPolicy policy) {
		this.policy=policy;
	}
}
