package basic;

public class LinearModel 
{
	public double m;
	public double b;
	public double x0;
	public LinearModel(double slope , double y_intercept , double year_0 )
	{
		m = slope;
		b = y_intercept;
		x0 = year_0;
	}
	public double f(double year)
	{
		return m*(year - x0 )+b;
	}
}
