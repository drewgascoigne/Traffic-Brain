package brain.violation;

public class RedLightViolation extends Violations
{
	//add more information on specific violation differeces here
	public RedLightViolation(int trafficIdOfViolation, String licensePlate,String dateOfViolation, String description) 
	{
		super(trafficIdOfViolation, licensePlate, dateOfViolation, description);
	}

}
