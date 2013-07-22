package math.linearregression;

public class LinearRegression {
	
    double[] x ;
    double[] y ;
    
    double a ;
    double b ;
    double r2 ;
    double varOfa;
    double varOfb_1;
    double varOfb_2;
    
    /**
     * Performs linear Regression calculus
     * 
     * @param x
     * @param y
     */
    public LinearRegression(double[] x, double[] y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void calculate() {

		double sum_x = 0.0, sum_y = 0.0, sum_x2 = 0.0;
        double mean_x =0.0 , mean_y=0.0;
        double mean_xx = 0.0, mean_yy = 0.0, mean_xy = 0.0;

        int length = this.x.length;
        
		for (int i=0; i<length; i++) {
            sum_x  += x[i];
            sum_x2 += x[i] * x[i];
            sum_y  += y[i];
        }
		
        mean_x = sum_x / length;
        mean_y = sum_y / length;

		
        for (int i = 0; i < length; i++) {
            mean_xx += (x[i] - mean_x) * (x[i] - mean_x);
            mean_xy += (x[i] - mean_x) * (y[i] - mean_y);
            mean_yy += (y[i] - mean_y) * (y[i] - mean_y);

        }
        
        this.a = mean_xy / mean_xx;
        this.b = mean_y - this.a * mean_x;

        int df = length  - 2;
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < length; i++) {
            double fit = this.a*x[i] + this.b;
            rss += (fit - this.y[i]) * (fit - this.y[i]);
            ssr += (fit - mean_y) * (fit - mean_y);
        }
        
        this.r2    = ssr / mean_yy;
        double svar  = rss / df;
        this.varOfa = svar / mean_xx;
        this.varOfb_1 = svar / length + mean_x * mean_x * this.varOfa;
        this.varOfb_2 = svar * sum_x2 / (length * mean_xx);
	}

	/**
	 * Return the slope of regression line
	 * 
	 * @return
	 */
	public double getA() {
		return a;
	}

	/**
	 * Return the origin of regression line
	 * 
	 * @return
	 */
	public double getB() {
		return b;
	}

	/**
	 * Returns the R-square value 
	 * @return
	 */
	public double getR2() {
		return r2;
	}

	/**
	 * Returns the variance of the slope
	 * @return
	 */
	public double getVarOfa() {
		return varOfa;
	}

	/**
	 * Returns the variance of the origin - method 1
	 * @return
	 */
	public double getVarOfb_1() {
		return varOfb_1;
	}

	/**
	 * Returns the variance of the origin - method 2
	 * @return
	 */
	public double getVarOfb_2() {
		return varOfb_2;
	}
	
//	public static void main (String[] arg) {
//
//		LinearRegression ln = new LinearRegression(new double[]{1,3,2,5,4,7,6,4,5,8}, new double[]{2,1,4,6,3,8,7,9,8,7});
//		
//		ln.calculate();
//		
//		System.out.println(ln.getA());
//		
//		System.out.println(ln.getB());
//		
//		System.out.println(ln.getR2());
//
//		
//	}

}

