package var3explore;

public class Explorys 
{
	double run_time;
	double dt;
	
	public Explorys(double run_length, double granularity, double dt, boolean make_assumptions)
	{
		int space_size = 10;
		if (granularity < .1 && granularity >= 1.0/1000000)
		{
			double approx_size = 1/granularity;
			space_size = (int) approx_size;
		}
		for(int i = 0; i < space_size; i++)
		{
			for(int j = 0; j < space_size; j++)
			{
				for(int k = 0; k < space_size; k++)
				{
					System.out.println("new CaseSpace("+(double)(i)*granularity*10.0+","+(double)(j)*granularity*10.0+","+(double)(k)*granularity*10.0+","+granularity+","+dt+")");
					CaseSpace current = new CaseSpace((double)(i)*granularity,(double)(j)*granularity,(double)(k)*granularity, granularity, dt);
					System.out.println("Begin processing");
					current.process(500, false);
					System.out.println("End processing");
					boolean bool_check = current.has_non_null_case();
					if (bool_check)
					{
						System.out.println("pause for a moment and revel in the glory");
					}
					System.out.println("has non-null case? "+bool_check);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		double granularity = .01;
		double dt = 1;
		System.out.println("Test length");
		Explorys e = new Explorys(100, granularity, dt, false);
	}
}
