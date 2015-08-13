package var3explore;

public class CaseSpace 
{
	DiffEqS dSdt;
	DiffEqI dIdt;
	PointSIR[][] current_space;

	double dt;
	double granularity;
	double current_time;
	
	public double alpha;
	public double beta;
	public double gamma;

	public CaseSpace(double a, double b, double c, double granularity, double dt)
	{
		alpha = a;
		beta = b;
		gamma = c;
		//for a given alpha, beta, gamma, track a broad range of initial points
		dSdt = new DiffEqS(a,b,c);
		dIdt = new DiffEqI(a,b,c);
		this.dt = dt;
		int space_size = 10;
		if (granularity < .1 && granularity >= 1.0/1000000)
		{
			double approx_size = 1/granularity;
			space_size = (int) approx_size;
		}
		current_space = new PointSIR[space_size][space_size];
	}
	public void process(double time_advance, boolean quick_refine)
	{
		double start_time = 0;
		//while in the current time increment
		while(current_time-time_advance <= start_time)
		{
			int null_tracker = 0;
			//for all of the initial conditions
			for(int i = 0; i < current_space.length; i++)
			{

				for(int j = 0; j < current_space[i].length; j++)
				{
					// if the case hasn't already been eliminated
					if(current_space[i][j] != null)
					{
						current_space[i][j].euler_increment(dt, dSdt, dIdt);
						if(current_time%1==0)
						{// if the current time is a whole number
							if (check_elim_case(current_space[i][j]))
							{//check if the case can be eliminated by trivial (no more infected)
								current_space[i][j] = null;
							}
						}
					}
					else
					{
						if (quick_refine)
						{
							null_tracker++;
						}
					}
				}
			}
			if (quick_refine && (double)(null_tracker)/(.9) > Math.pow(current_space.length, 2))
			{
				//if 90% of cases are invalid
				break;
			}
			current_time += dt;
		}
	}
	public boolean check_elim_case(PointSIR psir)
	{
		double epsilon = .001;
		return psir.I < epsilon || psir.I > 1-epsilon;
	}
	public boolean has_non_null_case()
	{
		boolean toReturn = false;
		for(int i = 0; i < current_space.length; i++)
		{

			for(int j = 0; j < current_space[i].length; j++)
			{
				// if the case hasn't already been eliminated
				if(current_space[i][j] != null)
				{
					System.out.println("Non-terminated case: PointS:"+current_space[i][j].S+" I:"+current_space[i][j].I+" R:"+current_space[i][j].R);
					toReturn = true;
				}
			}
		}
		return toReturn;
	}
}
