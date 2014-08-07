package com.medialets.util.sim.word;


public interface IWordFilter {
		String filter(String str);
		void setPolicy(FilterPolicy policy);
		FilterPolicy getPolicy();
}
