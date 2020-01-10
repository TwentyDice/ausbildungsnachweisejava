package calendar2;


import java.lang.Object;
import java.time.temporal.IsoFields;
import java.time.LocalDate;
import java.util.Scanner;


public class testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner userInput = new Scanner(System.in);

		// get starting year of user 
		System.out.println("Enter starting year");
		int userStartingYear = userInput.nextInt();
		
		// get starting month of user
		System.out.println("Enter starting month(as number)");
		int userStartingMonth = userInput.nextInt();
		
		// get apprenticeship duration
		System.out.println("Enter duration of apprenticeship in months");
		System.out.println("[3 Years]   -> 36");
		System.out.println("[2.5 Years] -> 30");
		System.out.println("varying durations works too");
		int userApprenticeshipDuration = userInput.nextInt();

		// determine the first work day
		// account for first day on weekend
		LocalDate firstWorkDate;
		for(firstWorkDate = LocalDate.of(userStartingYear, userStartingMonth, 1);(firstWorkDate.getDayOfWeek().getValue() > 5);firstWorkDate = firstWorkDate.plusDays(1)) {
			System.out.println(firstWorkDate.toString());
		};
		
		// set the date to monday for easier funciton call
		
			
		//System.out.println("test" + firstWorkDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
		
		int curCW = firstWorkDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		
		System.out.println(curCW);
		
		
		
	}

	
	// for easier handling only input date that are monday
	private void generateFiles() {
		// TODO Auto-generated method stub

	}
	
	private LocalDate setDateToMonday(LocalDate d) {
		
		if (d.getDayOfWeek().getValue() != 1) {
			d = d.minusDays((d.getDayOfWeek().getValue()) -1 );
		}		
		return d;

	}
	
	
}
