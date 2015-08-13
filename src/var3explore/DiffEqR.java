package var3explore;

public class DiffEqR 
{
	double a;
	double b;
	double c;
	public DiffEqR(double alpha, double beta, double gamma)
	{
		a = alpha;
		b = beta;
		c = gamma;
	}
	public double value(double S, double I, double R)
	{
		return b*I-c*R;
	}
}
