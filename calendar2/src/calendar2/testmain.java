package calendar2;


import java.io.IOException;
import java.lang.Object;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.temporal.IsoFields;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class testmain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner userInput = new Scanner(System.in);

		// get starting year of user
		System.out.println("Enter all as number");
		System.out.println("year");
		int userStartingYear = userInput.nextInt();
		
		// get starting month of user
		System.out.println("month");
		int userStartingMonth = userInput.nextInt();

		// get starting day of user
		System.out.println("day");
		int userStartingDay = userInput.nextInt();		
		
		// get apprenticeship duration in weeks
		System.out.println("Enter duration of apprenticeship in weeks");
		System.out.println("[3 Years]   -> 156");
		System.out.println("[2.5 Years] -> 130");
		System.out.println("varying durations works too");
		int userApprenticeshipDurationWeeks = userInput.nextInt(); 

		// get template path
		System.out.println("Enter template path");
		String templatePath = userInput.next();

		// get template path
		System.out.println("Enter target path");
		String targetPath = userInput.next();
		
		// determine the first work day
		// account for first day on weekend
		LocalDate firstWorkDate;
		for(firstWorkDate = LocalDate.of(userStartingYear, userStartingMonth, userStartingDay);(firstWorkDate.getDayOfWeek().getValue() > 5);firstWorkDate = firstWorkDate.plusDays(1)) {
			System.out.println(firstWorkDate.toString());
		};
		
		// set the date to monday for easier funciton call
		
		
		LocalDate curDate = firstWorkDate;
		
		if (curDate.getDayOfWeek().getValue() != 1) {
			curDate = curDate.minusDays((curDate.getDayOfWeek().getValue()) -1 );
		}		
			
		for(int i = 0; i < userApprenticeshipDurationWeeks ; i++) {
			
			// mon - fri, laufende nummer, kw, ausbildungsjarh
			
				int runningNumber = (i + 1);
				
				int curCW = curDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
			
				int asyr=1;
				
				if (curDate.isAfter(firstWorkDate.plusYears(1))) {
					asyr = 2;
				}
				
				if (curDate.isAfter(firstWorkDate.plusYears(2))) {
					asyr = 3;
				}
				
				String mon="",tue="",wed="",thu="",fri="";
				
			for(int counterWeekDays=0; counterWeekDays < 7; counterWeekDays++) {

				switch (curDate.getDayOfWeek().getValue()) {
				case 1:
					mon = formatDateToDesired(curDate);
				case 2:
					tue = formatDateToDesired(curDate);
				case 3:
					wed = formatDateToDesired(curDate);
				case 4:
					thu = formatDateToDesired(curDate);
				case 5:
					fri = formatDateToDesired(curDate);
				}
				
				curDate = curDate.plusDays(1);
			}
			
			//create String for call .bat
			StringBuilder s = new StringBuilder();
			
			s.append("notready");
			s.append("_");
			s.append(asyr);
			s.append("_");
			
			if(curCW < 10) {
				s.append("0");
			}
			
			s.append(curCW);
			s.append("_");
			
			
			if(runningNumber < 100) {
				s.append("0");
			}
			if(runningNumber < 10) {
				s.append("0");
			}
			
			s.append(runningNumber);
			s.append("_");
			s.append(mon);
			s.append("_");
			s.append(tue);
			s.append("_");
			s.append(wed);
			s.append("_"); 
			s.append(thu);
			s.append("_");
			s.append(fri);
			
			System.out.println(s);
			
	        Path template = Paths.get(templatePath);
//	        System.out.println("file to copy: " + template);
	        Path copiedFile = Files.copy(
	        		template,
	                Paths.get((targetPath + "\\recordBooks\\" + s.toString() + ".docm"))
	                );
		}
		
	}	
	
	public static String formatDateToDesired(LocalDate d) {
	
		DateTimeFormatter asshead = DateTimeFormatter.ofPattern("dd.MM.yyyy");	
		
		return 	d.format(asshead);
		
	}
	
	
}