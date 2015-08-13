package var3explore;

public class PointSIR 
{
	double S;
	double I;
	double R;
	public PointSIR(double s, double i)
	{
		S=s;
		I=i;
		R=1-s-i;
	}
	public void euler_increment(double dt, DiffEqS dSdt, DiffEqI dIdt)
	{
		S = S+dt*dSdt.value(S, I);
		I = I+dt*dIdt.value(S, I);
	}
}
