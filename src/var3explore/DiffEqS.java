package var3explore;

public class DiffEqS
{
	double a;
	double b;
	double c;
	public DiffEqS(double alpha, double beta, double gamma)
	{
		a = alpha;
		b = beta;
		c = gamma;
	}
	public double value(double S, double I)
	{
		return -a*S*I+c*(1-S-I);
	}
}
