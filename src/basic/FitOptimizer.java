package basic;

public class FitOptimizer 
{
	private double[][] data;
	private double[] approx_rate;
	private LogisticModel log_model;
	private ExponentialModel exp_model;
	private LinearModel lin_model;
	
	private static double precision = .01;
	private static int max_cycles = 1000;
	private static double[][] data_set = newYork();
	
	public FitOptimizer(double[][] input)
	{
		this.data = input;
		approx_rate = new double[data.length-1];
		log_model = log_initialguess();
		exp_model = exp_initialguess();
		lin_model = lin_initialguess();
	}

	public static void main(String[] args)
	{
		FitOptimizer fo = new FitOptimizer(data_set);
		/* LOGISTIC MODEL
		for (int i = 0 ; i < 10 ; i++)
		{
			fo.log_model = fo.log_initialguess();
			int count = 0;
			boolean successfulOptimization = true;
			while (successfulOptimization && (count <  i*max_cycles))
			{
				successfulOptimization = fo.log_optimize();
				if (!successfulOptimization)
				{
					System.out.println("\nreached local min in state space at step "+count);
				}
				count++;
			}
			System.out.print("\n");
			LogisticModel result = fo.log_result();
			System.out.println("result for "+(count)+" cycles --> dP/dt = "+result.k+"*P * (1-(P/"+result.N+")) + "+result.c);
		}//*/
		///* EXPONENTIAL MODEL
		for (int i = 0 ; i < 10 ; i++)
		{
			fo.exp_model = fo.exp_initialguess();
			int count = 0;
			boolean successfulOptimization = true;
			while (successfulOptimization && (count <  i*max_cycles))
			{
				successfulOptimization = fo.exp_optimize();
				if (!successfulOptimization)
				{
					System.out.println("\nreached local min in state space at step "+count);
				}
				count++;
			}
			System.out.print("\n");
			ExponentialModel result = fo.exp_result();
			System.out.println("result for "+count+" cycles --> dP/dt = "+result.k+" * P");
		}//*/
		System.out.println("precision: "+precision);
		System.out.println("linear model - m = "+fo.lin_result().m);
	}
	public static double[][] massachusetts()
	{
		double[][] population_data = new double[22][2];
		//assign years
		for ( int i = 1790 ; i <= 2000 ; i = i+10)
		{
			population_data[(i-1790)/10][1] = (double) i;
		}
		population_data[0][0] = 379; population_data[1][0] = 423;
		population_data[2][0] = 472; population_data[3][0] = 523;

		population_data[4][0] = 610; population_data[5][0] = 738;
		population_data[6][0] = 995; population_data[7][0] = 1231;

		population_data[8][0] = 1457; population_data[9][0] = 1783;
		population_data[10][0] = 2239; population_data[11][0] = 2805;

		population_data[12][0] = 3366; population_data[13][0] = 3852;
		population_data[14][0] = 4250; population_data[15][0] = 4317;

		population_data[16][0] = 4691; population_data[17][0] = 5149;
		population_data[18][0] = 5689; population_data[19][0] = 5737;

		population_data[20][0] = 6016; population_data[21][0] = 6349;

		return population_data;
	}
	public static double[][] hawaii()
	{
		double[][] population_data = new double[11][2];
		//assign years
		for ( int i = 1900 ; i <= 2000 ; i = i+10)
		{
			population_data[(i-1900)/10][1] = (double) i;
		}
		population_data[0][0] = 154; population_data[1][0] = 192;
		population_data[2][0] = 226; population_data[3][0] = 368;

		population_data[4][0] = 423; population_data[5][0] = 500;
		population_data[6][0] = 633; population_data[7][0] = 770;

		population_data[8][0] = 965; population_data[9][0] = 1108;
		population_data[10][0] = 1212;

		return population_data;
	}
	public static double[][] montana()
	{
		double[][] population_data = new double[14][2];
		//assign years
		for ( int i = 1870 ; i <= 2000 ; i = i+10)
		{
			population_data[(i-1870)/10][1] = (double) i;
		}
		population_data[0][0] = 21; population_data[1][0] = 39;
		population_data[2][0] = 143; population_data[3][0] = 243;

		population_data[4][0] = 376; population_data[5][0] = 549;
		population_data[6][0] = 538; population_data[7][0] = 559;

		population_data[8][0] = 591; population_data[9][0] = 675;
		population_data[10][0] = 694; population_data[11][0] = 787;
		
		population_data[12][0] = 799; population_data[13][0] = 902;

		return population_data;
	}
	public static double[][] newYork()
	{
		double[][] population_data = new double[22][2];
		//assign years
		for ( int i = 1790 ; i <= 2000 ; i = i+10)
		{
			population_data[(i-1790)/10][1] = (double) i;
		}
		population_data[0][0] = 340; population_data[1][0] = 589;
		population_data[2][0] = 959; population_data[3][0] = 1373;

		population_data[4][0] = 1919; population_data[5][0] = 2429;
		population_data[6][0] = 3097; population_data[7][0] = 3881;

		population_data[8][0] = 4383; population_data[9][0] = 5083;
		population_data[10][0] = 6003; population_data[11][0] = 7269;

		population_data[12][0] = 9114; population_data[13][0] = 10385;
		population_data[14][0] = 12588; population_data[15][0] = 13479;

		population_data[16][0] = 14830; population_data[17][0] = 16782;
		population_data[18][0] = 18241; population_data[19][0] = 17558;

		population_data[20][0] = 17990; population_data[21][0] = 18976;

		return population_data;
	}
	public static double[][] northCarolina()
	{
		double[][] population_data = new double[22][2];
		//assign years
		for ( int i = 1790 ; i <= 2000 ; i = i+10)
		{
			population_data[(i-1790)/10][1] = (double) i;
		}
		population_data[0][0] = 394; population_data[1][0] = 478;
		population_data[2][0] = 556; population_data[3][0] = 639;

		population_data[4][0] = 738; population_data[5][0] = 753;
		population_data[6][0] = 869; population_data[7][0] = 993;

		population_data[8][0] = 1071; population_data[9][0] = 1400;
		population_data[10][0] = 1618; population_data[11][0] = 1893;

		population_data[12][0] = 2206; population_data[13][0] = 2559;
		population_data[14][0] = 3170; population_data[15][0] = 3572;

		population_data[16][0] = 4062; population_data[17][0] = 4556;
		population_data[18][0] = 5084; population_data[19][0] = 5880;

		population_data[20][0] = 6628; population_data[21][0] = 8049;

		return population_data;
	}
	public LogisticModel log_result()
	{
		return log_model;
	}
	public ExponentialModel exp_result()
	{
		return exp_model;
	}
	public LinearModel lin_result()
	{
		return lin_model;
	}
	public boolean log_optimize()
	{
		boolean better_soln_found = false;
		double fitness_rating = fitRating(log_model);
		LogisticModel[] test_set = log_local_test(log_model);
		for (int i = 0 ; i < test_set.length ; i++ )
		{
			double test_rating = fitRating(test_set[i]);
			if (test_rating < fitness_rating)
			{
				log_model = test_set[i];
				fitness_rating = test_rating;
				if (max_cycles < 1000 || fitness_rating < 5.6587)
				{
					System.out.print((" Lv"+fitness_rating).substring(0,9));
				}
				better_soln_found = true;
			}
		}
		return better_soln_found;
	}
	public boolean exp_optimize()
	{
		boolean better_soln_found = false;
		double fitness_rating = fitRating(exp_model);
		ExponentialModel[] test_set = exp_local_test(exp_model);
		for (int i = 0 ; i < test_set.length ; i++ )
		{
			double test_rating = fitRating(test_set[i]);
			if (test_rating < fitness_rating)
			{
				exp_model = test_set[i];
				fitness_rating = test_rating;
				if (max_cycles < 10000 || fitness_rating < 1.94)
				{
					System.out.print((" Ev"+fitness_rating).substring(0,9));
				}
				better_soln_found = true;
			}
		}
		return better_soln_found;
	}
	public LogisticModel log_initialguess()
	{
		//find dp/dt for the data, also find max (middle of logistic model)
		approx_rate = new double[data.length-1];
		double max_rate = 0;
		int max_index = 0;
		for (int i = 0 ; i < data.length -1 ; i++)
		{
			approx_rate[i] = data[i+1][0]-data[i][0];
			if (approx_rate[i] > max_rate)
			{
				max_rate = approx_rate[i];
				max_index = i;
			}
		}
		//System.out.println("max_index = "+max_index);

		//compare the ratio of rates to the beginning, max and end (max = 1)
		//the closer the beginning and end are to 0, the more complete the model

		// to approximate model, the normalized high point (max) is compared to beginning and end
		//  in order to approximate the part of P that is covered by data, use extended form of quadratic to back out to find percentage of P range
		// %P = -.5 +- .5*sqrt(1-(% of max))
		//System.out.println("max_value: "+approx_rate[max_index]+" right_value:"+
		//		approx_rate[approx_rate.length-1]+" left_value:"+approx_rate[0]);
		//System.out.println("left_ratio: "+(approx_rate[0]/approx_rate[max_index]+"").substring(0,4)+
		//		" right_ratio: "+(approx_rate[approx_rate.length-1]/approx_rate[max_index]+"").substring(0,4));
		//System.out.println("left quad. form.: -.5+- .5*sqrt(1-rate/max_rate)\n"+
		//		(.5-(.5*Math.sqrt(1-(approx_rate[0]/max_rate))))+"");
		//System.out.println("right quad. form.: -.5+- .5*sqrt(1-rate/max_rate)\n"+
		//		(.5+(.5*Math.sqrt(1-(approx_rate[approx_rate.length-1]/max_rate))))+"");
		double near_percent = Math.min(.5+(.5*Math.sqrt(1-(approx_rate[0]/max_rate))),
				.5-(.5*Math.sqrt(1-(approx_rate[0]/max_rate))));
		double far_percent = Math.max(.5+(.5*Math.sqrt(1-(approx_rate[approx_rate.length-1]/max_rate))),
				.5-(.5*Math.sqrt(1-(approx_rate[approx_rate.length-1]/max_rate))));
		double total_normalized_population_range = -near_percent+far_percent;

		//System.out.println("near_percent: .5-"+(.5-near_percent)+
		//		" far_percent: .5+"+(far_percent-.5));
		//if the near data is the max rate, assume that it is the mid point
		double min_population = 0;
		if (max_index > 0 && true)
		{
			//otherwise use my estimation
			//min_population = data[max_index][0]-((data[max_index][0]-data[0][0])*.5/(.5-near_percent));
			double current_range = data[max_index][0]-data[0][0];
			double ratio = (.5-near_percent)/.5;
			double est_full_range = current_range/ratio;
			//System.out.println("The min current range ("+current_range+") is "+ratio*100+"% of total range ("+est_full_range+")");
			min_population = data[max_index][0]-est_full_range;
			//System.out.println("min_population calc: "+min_population);
		}

		//if the data is at it's max growth (exponential pattern) assume that it is at half way point
		double max_population = data[max_index][0]*2;
		if (max_index < data.length-2)
		{
			//otherwise use my estimation
			//max_population = (data[data.length-2][0] - data[max_index][0])*.5/(far_percent-.5);
			double current_range = data[data.length-2][0]-data[max_index][0];
			double ratio = (far_percent-.5)/.5;
			double est_full_range = current_range/ratio;
			//System.out.println("The max current range ("+current_range+") is "+ratio*100+"% of total range ("+est_full_range+")");
			max_population = data[max_index][0]+est_full_range;
			//System.out.println("max_population calc: "+max_population);
		}

		//max_population is carrying capacity
		//min_population is the +c factor to adjust for position at 0
		// now I take the average value for k when calculated at each data point to get my initial estimate
		// k = rate/(P*(1-P/N))
		double sum = 0;
		int count = 0;
		for (int i = 0 ; i < data.length-1 ; i++ )
		{
			sum += approx_rate[i]/(data[i][0]*(1-data[i][0]/max_population));
			count++;
		}
		double k_estimate = sum/(double)(count);
		System.out.println("initial value --> dP/dt = "+k_estimate+"*P * (1-(P/"+max_population+")) + "+min_population);
		return new LogisticModel(k_estimate, max_population, min_population);
	}
	public ExponentialModel exp_initialguess()
	{
		double sum = 0;
		double count = 0;
		for (int i = 0 ; i < data.length-1 ; i++)
		{
			sum += data[i+1][0]/data[i][0]-1;
			count++;
		}
		double k = sum/count;
		System.out.println("initial value --> dP/dt = "+k+" * P");
		return new ExponentialModel(k);
	}
	public LinearModel lin_initialguess()
	{
		double x1 = data[data.length-2][1];
		double y1 = data[data.length-2][0];
		double x2 = data[data.length-1][1];
		double y2 = data[data.length-1][0];
		System.out.println("delta x: "+(x2-x1));
		System.out.println("delta y: "+(y2-y1));
		return new LinearModel( 10*(y2-y1)/(x2-x1) );
	}
	public double fitRating(LogisticModel test)
	{
		double rating = 100;
		double sum = 0;
		int count = 0;
		for (int i = 0 ; i < data.length-1 ; i++ )
		{
			sum += Math.pow(approx_rate[i]-test.f(data[i][0]), 2);
			count++;
		}
		//the rating is the sum of the squares to the line predicted vs. measured data
		// low is better
		// 100 is default
		rating = sum / (double) (count);
		//get the average distance to the fit, then divide by max population as an approximate
		// scaling factor so that output is comparable between runs.
		rating = 100*Math.sqrt(rating) / data[data.length-1][0];
		return rating;
	}
	public double fitRating(ExponentialModel test)
	{
		double rating = 100;
		double sum = 0;
		int count = 0;
		for (int i = 0 ; i < data.length-1 ; i++ )
		{
			sum += Math.pow( (approx_rate[i] - test.f(data[i][0])) , 2 );
			count++;
		}
		//the rating is the sum of the squares to the line predicted vs. measured data
		// low is better
		// 100 is default
		rating = sum / (double) (count);
		//get the average distance to the fit, then divide by max population as an approximate
		// scaling factor so that output is comparable between runs.
		rating = 100*Math.sqrt(rating) / data[data.length-1][0];
		return rating;
	}
	public LogisticModel[] log_local_test(LogisticModel start)
	{
		// returns an array of models near the value of the input model
		LogisticModel[] ret_models = new LogisticModel[6];
		ret_models[0] = new LogisticModel( start.k*(1+precision)  , start.N , start.c );
		ret_models[1] = new LogisticModel( start.k*(1-precision)  , start.N , start.c );
		ret_models[2] = new LogisticModel( start.k , start.N*(1+precision)  , start.c );
		ret_models[3] = new LogisticModel( start.k , start.N*(1-precision)  , start.c );
		ret_models[4] = new LogisticModel( start.k , start.N , start.c*(1+precision)  );
		ret_models[5] = new LogisticModel( start.k , start.N , start.c*(1-precision)  );
		return ret_models;
	}
	public ExponentialModel[] exp_local_test(ExponentialModel start)
	{
		// returns an array of models near the value of the input model
		ExponentialModel[] ret_models = new ExponentialModel[2];
		ret_models[0] = new ExponentialModel( start.k*(1+precision) );
		ret_models[1] = new ExponentialModel( start.k*(1-precision) );
		return ret_models;
	}
}
