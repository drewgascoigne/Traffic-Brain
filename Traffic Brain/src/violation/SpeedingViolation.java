package violation;

public class SpeedingViolation extends Violations
{
	//add more information on specific violation differeces here
	public SpeedingViolation(int trafficIdOfViolation, String licensePlate,String dateOfViolation, String description) 
	{
		super(trafficIdOfViolation, licensePlate, dateOfViolation, description);
	}

}
