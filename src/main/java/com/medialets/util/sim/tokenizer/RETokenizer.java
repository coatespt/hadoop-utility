package com.medialets.util.sim.tokenizer; 
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.medialets.util.sim.word.FilterPolicy;
import com.medialets.util.sim.word.IWordFilter;

/**
 * Tokenizer is constructed with a regular expression and runs
 * with or without an IWordFilter to do more complex transformations
 * and filtering.
 * 
 * Define an IWordFilter to discard classes of words, transform case,
 * etc.
 * 
 * TODO: Runs with null or one filter. Add capability to compose a series of filters
 * passed in as an array.
 * 
 * @see http://www.exampledepot.com/egs/java.util.regex/Tokenize.html
 * @author peterc
 *
 */
public class RETokenizer implements Iterator<Object> {
	
	private IWordFilter filter;
	
    // Holds the original input to search for tokens
    private CharSequence input;

    // Used to find tokens
    private Matcher matcher;

    // If true, the String between tokens are returned
    private boolean returnDelims;

    // The current delimiter value. If non-null, should be returned
    // at the next call to next()
    private String delim;

    // The current matched value. If non-null and delim=null,
    // should be returned at the next call to next()
    private String match;

    // The value of matcher.end() from the last successful match.
    private int lastEnd = 0;

	public final static boolean DO_NOT_RETURN_DELIMS = false;
	public final static boolean RETURN_DELIMS = true;

	//	private static final Logger log = 
	//		Logger.getLogger("utility.tokenizer.RETokenizer");
	
    // patternStr is a regular expression pattern that identifies tokens.
    // If returnDelims delim is false, only those tokens that match the
    // pattern are returned. If returnDelims true, the text between
    // matching tokens are also returned. If returnDelims is true, the
    // tokens are returned in the following sequence - delimiter, token,
    // delimiter, token, etc. Tokens can never be empty but delimiters might
    // be empty (empty string).
    public RETokenizer(CharSequence input, String patternStr, boolean returnDelims) {
        super();
        this.input = input;
        this.returnDelims = returnDelims; 
        // Compile pattern and prepare input
        Pattern pattern = Pattern.compile(patternStr);
        matcher = pattern.matcher(input);
        filter=(new LowerCaseDefaultFilter());
    }
    
    /**
     * Optionally use an IWordFilter
     * @param input
     * @param patternStr
     * @param returnDelims
     * @param filter
     */
    public RETokenizer(CharSequence input, String patternStr, boolean returnDelims, 
    		IWordFilter filter) {
    	this(input,patternStr,returnDelims); 
        this.filter=filter!=null ? filter : (new LowerCaseDefaultFilter() );
    }

    // Returns true if there are more tokens or delimiters.
    public boolean hasNext() {
        if (matcher == null) {
            return false;
        }
        if (delim != null || match != null) {
            return true;
        }
        if (matcher.find()) {
            if (returnDelims) {
                delim = input.subSequence(lastEnd, matcher.start()).toString();
            }
            match = matcher.group();
            lastEnd = matcher.end();
        } else if (returnDelims && lastEnd < input.length()) {
            delim = input.subSequence(lastEnd, input.length()).toString();
            lastEnd = input.length();

            // Need to remove the matcher since it appears to automatically
            // reset itself once it reaches the end.
            matcher = null;
        }
        return delim != null || match != null;
    }

    // Returns the next token (or delimiter if returnDelims is true).
    public Object next() {
        String result = null;

        if (delim != null) {
            result = delim;
            delim = null;
        } else if (match != null) {
            result = match;
            match = null;
        }
        return processRawToken(result);
    }

    /**
     * Process a raw token according to whatever filter is defined. If the
     * filter is null, the processing is null too --- you get whatever is the
     * result of the original tokeinzation expression you supplied to the constructor.
     * @param token
     * @return
     */
    private String processRawToken(Object token){
    	String ret=null; 
    	if(filter==null){
    		ret = ((String)token).toLowerCase().trim();
    	}
    	else { 
    		ret = filter.filter((String)token);
    	}
    	return ret;
    }
    
    // Returns true if the call to next() will return a token rather
    // than a delimiter.
    public boolean isNextToken() {
        return delim == null && match != null;
    }

    // Not supported.
    public void remove() {
        throw new UnsupportedOperationException();
    }

	public IWordFilter getFilter() {
		return filter;
	}

	public void setFilter(IWordFilter filter) {
		this.filter = filter;
	}
	
	/**
	 * A simple filter that runs toLowerCase() on the input.
	 * @author peterc
	 *
	 */
	private class LowerCaseDefaultFilter implements IWordFilter {

		public String filter(String str) {
			return (str==null) ? null : (str.toLowerCase());
		}

		public FilterPolicy getPolicy() { 
			return null;
		}

		public void setPolicy(FilterPolicy policy) { 
		} 
	}
}
