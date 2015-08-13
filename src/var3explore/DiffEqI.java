package var3explore;

public class DiffEqI 
{
	double a;
	double b;
	double c;
	public DiffEqI(double alpha, double beta, double gamma)
	{
		a = alpha;
		b = beta;
		c = gamma;
	}
	public double value(double S, double I)
	{
		return a*S*I-b*I;
	}
}
