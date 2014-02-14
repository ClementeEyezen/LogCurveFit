package basic;

public class LogisticModel 
{
	public double k;
	public double N;
	public double c;
	public LogisticModel(double k, double carry_capacity, double vert_adjust)
	{
		this.k = k;
		this.N = carry_capacity;
		this.c = vert_adjust;
	}
	public double f(double population)
	{
		return k*population*(1-(population/N))+c;
	}
}
