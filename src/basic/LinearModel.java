package basic;

public class LinearModel 
{
	public double m;
	public LinearModel(double slope)
	{
		m = slope;
	}
	public double f(double year)
	{
		return m;
	}
}
