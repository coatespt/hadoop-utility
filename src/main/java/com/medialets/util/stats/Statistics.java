package com.medialets.util.stats;

import java.util.Random;

/**
 * Various numerically oriented code snips.
 * @author peterc
 *
 */
public class Statistics {

	 static Random rand = new Random();
	 
	/**
	 * Compute a random variable grouped around the parameter average.
	 * The smaller of average-min and average-max is chosen as the unit of variation to
	 * give the random numbers a general ballpark size.
	 * 
	 * This basic quantity is scaled by a gaussian distribution which is generated such that sds 
	 * standard deviations will fall within the range -1 to 1. The scaling controls how tightly
	 * grouped around "average" the random values will be.  If one is chosen, for instance, 
	 * 68% of the values will be within one base unit of the average.
	 *  
	 * If you choose sds=3, the grouping is tighter---68% will be within one third of a base unit of
	 * average, and 99% will be within three base units.
	 * 
	 * The larger the nubmer, the tighter the grouping, but you can still get the odd outlier, just
	 * as in real life.
	 * 
	 * Thus, SDS tightens the grouping of the generateed value around the parameter "average".
	 * 
	 * 
	 * 
	 * @param min
	 * @param max
	 * @param average
	 * @param sds
	 * @return
	 * @throws Exception
	 */
	public static float rand(int min, int max, float average, float sds ) throws Exception {
		if(max<min || (average<min || average>max) || min<0){
			throw new Exception("contradictory min:" + min + " max:" + max + " average:" + average);
		} 
		
		// float range = ((float) max - min);
		// to minimize the effect of outliers, i.e., either the min or the max is far from the average,
		// we take the lesser of the two differences between average-min and average-max.
		float range = Math.min(Math.abs(average-min),Math.abs(average-max));
		float scaledRange = range * getGaussian(sds); 
		float scaledValue = average + scaledRange;
		return scaledValue;
	}
	
	/**
	 * Compute a Gaussian-distributed random variable.
	 * The raw distribution is computed with a mean of 0 standard deviation of 1.
	 * 
	 * The returned value scaled by 1/n, i.e.  E.g., if n=3, only about
	 * one value in 100 will be as great as 1.
	 * 
	 * Thus, if n=1, app. .68 of the values will be between -1 an 1, and if
	 * n=3, app. 0.997 of hte values will be between -1 and 1, etc.
	 * 
	 * @param n  a double which is the number of standard deviations to be included.
	 * @return
	 */
	public static float getGaussian(float n){ 
	 double x1, x2, w, y1; 
     	do {
             x1 = 2.0 * rand.nextFloat() - 1.0;
             x2 = 2.0 * rand.nextFloat() - 1.0;
             w = x1 * x1 + x2 * x2;
     	} while ( w >= 1.0 );

     	w = Math.sqrt(-2.0 * Math.log(( w ) ) / w );
     	y1 = x1 * w;
     	//y2 = x2 * w;
     	// y1=Math.abs(y1);
     	return (float) y1/n;
	}

}
