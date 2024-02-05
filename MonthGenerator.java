//
// Name: 	Buckman, Liam
// Project: 3
// Due: 	2023-10-19
// Course: 	cs-1400-02-f23
//
// Description:
// 			Generate a calendar based on the month and year provided by the user.
//			Assume valid inputs.
//

import java.io.*;
import java.util.Scanner;

public class MonthGenerator {
	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Calendar by L. Buckman\n");
		System.out.print("Enter month year ? ");
		int month = keyboard.nextInt();
		int year = keyboard.nextInt();

		String filename = String.format("%d-%d.txt", month, year);
		PrintWriter outfile = new PrintWriter(filename);
		outfile.println(getMonthName(month) + " " + year + "\n");
		outfile.println("Sun  Mon  Tue  Wed  Thu  Fri  Sat");
		outfile.println("---------------------------------");

		int firstDay = getDayOfTheWeek(1, month, year);
		int totalDays = getNumberOfDaysInMonth(month, year);
		int daysPerWeekCounter = 0;
		while (daysPerWeekCounter < firstDay) {
			outfile.print("     ");
			daysPerWeekCounter++;
		}

		final int LAST_INDEX_PER_WEEK = 6;
		for (int days = 1; days <= totalDays; days++) {
			outfile.printf("%3d", days);
			if (days == totalDays) {
				outfile.printf("%n");
			} else if (daysPerWeekCounter != LAST_INDEX_PER_WEEK) {
				outfile.print("  ");
				daysPerWeekCounter++;
			} else {
				outfile.printf("%n");
				daysPerWeekCounter = 0;
			}
		}
		
		outfile.close();
		keyboard.close();
		System.out.println();
		System.out.println(filename + " generated.");
	}

	public static int getDayOfTheWeek(int day, int month, int year) {
		int a = (14 - month) / 12;
		int y = year - a;
		int m = month + 12*a -2;

		return (day + y + y/4 - y/100 + y/400 + 31*m/12) % 7;
	}

	public static int getNumberOfDaysInMonth(int month, int year) {
		return switch(month) {
			case 4, 6, 9, 11-> {yield 30;}
			case 2 -> {yield isLeapYear(year) ? 29 : 28;}
			default -> {yield 31;}
		};
	}

	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	public static String getMonthName(int month) {
		final String[] MONTHS = {
			"January", "February", "March", "April", 
			"May", "June", "July", "August", 
			"September", "October", "November", "December"
		};

		return MONTHS[month - 1];
	}
}