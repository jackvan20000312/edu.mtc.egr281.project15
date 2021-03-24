package project15;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TMRCalculatorFrontEnd_Console {
	static TMRCalculatorBackEnd TMRcalc = new TMRCalculatorBackEnd();
	static Scanner kb = new Scanner(System.in);
	public static DecimalFormat df = new DecimalFormat("$#,###,###.00");
	public static int distance;
	public static int destinationCity;
	public static int initialCity;
	public static int citiesToVisit;
	public static int currentCity;
	
	public static void main(String[] args) {
		System.out.println("Enter Cost Per Mile(in Cents), 57.5 is standard.");
		double costPerMile = kb.nextDouble();
		TMRcalc.setCostPerMileInCents(costPerMile);
		
		System.out.println("How many Cities would you like to visit?");
		citiesToVisit = kb.nextInt();
		System.out.println("Where would you like to start?");
		initialCity = getCityInput();
		currentCity=initialCity;
		TMRcalc.setActualOrigin(initialCity);
		TMRcalc.setOrigin(currentCity);
		travel();
		
		for(int i=1; i<citiesToVisit;++i) {//i=1, because we already went through first city...
			travel();
		}
		
		int halfTrip=TMRcalc.getTotalDistance();
		System.out.println("Right now, in "+TMRcalc.getCityNames[currentCity]+", You have driven "+halfTrip+" miles.");
		int roundTrip=TMRcalc.getRoundTripDistance(); 
		TMRcalc.setCurrentCost(initialCity, destinationCity);
		TMRcalc.addToCurrentTotalCost();
		System.out.println("Total Trip Will Take "+roundTrip+" miles, and will cost "+df.format(TMRcalc.getTotalCost()));
		kb.close();
	}
	
	private static void travel() {//updates all settings in BackEnd, must run every time user enters a new city
		System.out.println("Where would you like to go now?");
		destinationCity = getCityInput();
		TMRcalc.setDestination(destinationCity);
		TMRcalc.setCurrentDistance(currentCity, destinationCity);
		TMRcalc.addToTotalDistance(currentCity, destinationCity);
		distance = TMRcalc.getCurrentDistance();
		TMRcalc.setOrigin(destinationCity);
		TMRcalc.setCurrentCost(currentCity, destinationCity);
		TMRcalc.addToCurrentTotalCost(); 
		System.out.println(distance+" miles.");
		currentCity = destinationCity;
		
	}
private static int getCityInput() {//will be using this multiple times, hence, why it is in a seperate method
		System.out.println("Select an Option: (Use Numbers Only)");
		for(int i=0; i<TMRcalc.getCityNames.length;++i) {
			System.out.println((i+1)+". "+TMRcalc.getCityNames[i]);
		}
		int cityInput = kb.nextInt()-1;
		return cityInput;
	}
}//ending bracket of class TMRCalculatorFrontEnd_Console
