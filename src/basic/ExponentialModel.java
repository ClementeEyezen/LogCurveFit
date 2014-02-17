package basic;

public class ExponentialModel 
{
	public double k;
	public ExponentialModel(double k)
	{
		// dp/dt = A*exp(kx)+ c
		this.k = k;
	}
	public double f(double population)
	{
		return k*population;
	}
}
