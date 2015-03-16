package violation;


public abstract class Violations 
{
	//Id of the traffic light at which the violation occured
	protected int trafficIdOfViolation;
	//license plate of the car in question
	protected String licensePlate; 
	//date the violation occured
	protected String dateOfViolation;
	//description of the violation
	protected String description;
	//extra variables that are sure to be added
	
	/*
	 * initialize a violations
	 */
	public Violations(int trafficIdOfViolation, String licensePlate, String dateOfViolation, String description)
	{
		//set data that is passed in
		this.trafficIdOfViolation=trafficIdOfViolation;
		this.licensePlate = licensePlate;
		this.dateOfViolation = dateOfViolation;
		this.description = description;
	}
	//public abstract void name(parameters);
	
	
	/*
	 * get license plate of the car in question
	 */
	public String getLicensePlate()
	{
		return licensePlate;
	}
	/*
	 * get date the violation occured
	 */
	public String getDate()
	{
		return dateOfViolation;
	}
	/*
	 * get description of the violation
	 */
	public String getDescription()
	{
		return description;
	}
	/*
	 * get the id of where the traffic violation occured
	 */
	public int getTrafficId()
	{
		return trafficIdOfViolation;
	}
}

