package project15;

public class TMRCalculatorBackEnd {
	private int getDistanceBetweenCities [] [] = {{0,1004,1753,2752,3017,1520,1507,609,3155,448},
			 {1004,0,921,1780,2048,1397,919,515,2176,709},
			 {1753,921,0,1230,1399,1343,517,1435,2234,1307},
			 {2752,1780,1230,0,272,2570,1732,2251,1322,2420},
			 {3017,2048,1399,272,0,2716,1858,2523,1278,2646},
			 {1520,1397,1343,2570,2716,0,860,1494,3447,1057},
			 {1507,919,517,1732,1858,860,0,1307,2734,1099},
			 {609,515,1435,2251,2523,1494,1307,0,2820,571},
			 {3155,2176,2234,1322,1278,3447,2734,2820,0,2887},
			 {448,709,1307,2420,2646,1057,1099,571,2887,0}};
	
	public String getCityNames [] = {"Boston","Chicago","Dallas","Reno","Los Angeles","Miami","New Orleans","Toronto","Vancouver","Washington DC"};
	
	
	//private int previousdistance = 0;
	private double currentCost=0;
	private double totalCost=0;
	private int currentDistance=0;
	private int totalDistance=0;
	private double costPerMileInCents=0;
	private int currentDestination;
	private int currentOrigin;
	//private String finalDestination;
	private int actualOrigin;
	private int roundTrip=0;
	private int numberOfCitiesCompleted=0;
	
	public void resetPRGM() {
		currentCost=0;
		totalCost=0;
		currentDistance=0;
		totalDistance=0;
		costPerMileInCents=0;
		roundTrip=0;
		numberOfCitiesCompleted=0;
	}
//	 get CURRENT distance
		public int getCurrentDistance() {
			return this.currentDistance;
		}
//		gets total distance
		public int getTotalDistance() {
			return this.totalDistance;
		}
//		sets CURRENT distance
		public void setCurrentDistance(int row, int column) {
			this.currentDistance=getDistanceBetweenCities [row] [column];
		}
//		sets total distance
		private void setTotalDistance(int newTotalDistance) {
			this.totalDistance=newTotalDistance;
		}
//		adds to total distance
		public void addToTotalDistance(int row, int column) {
			this.setTotalDistance(this.getTotalDistance()+this.getDistanceBetweenCities [row] [column]);
		}
//		gets Number Of Cities Completed
		public int getNumberOfCitiesCompleted() {
			return this.numberOfCitiesCompleted;
		}
//		sets Number Of Cities Completed
		private void setNumberOfCitiesCompleted(int newNumberOfCitiesCompleted) {
			this.numberOfCitiesCompleted=newNumberOfCitiesCompleted;
		}
//		adds to Number Of Cities Completed
		public void addToNumberOfCitiesCompleted() {
			this.setNumberOfCitiesCompleted(this.getNumberOfCitiesCompleted()+1);
		}
//		gets CURRENT Destination
		public int getDestination() {
			return this.currentDestination;
		}
//		gets CURRENT Origin
		public int getOrigin() {
			return this.currentOrigin;
		}
//		sets CURRENT Origin
		public void setOrigin(int column) {
			this.currentOrigin=column;
		}
//		sets CURRENT destination(int)
		public void setDestination(int column) {
			this.currentDestination=column;
		}
//		gets Total cost
		public double getTotalCost() {
			return this.totalCost;
		}
//		adds Current cost to Total cost
		public void addToCurrentTotalCost() {
		 this.setTotalCost(this.getTotalCost()+getCurrentCost());

		}
//		sets Total cost
		private void setTotalCost(double newTotalCost) {
			this.totalCost=newTotalCost;
		}
//		gets CURRENT cost(Extra Feature)
	 	public double getCurrentCost() {
		 return this.currentCost;
	 	}
//	 	sets CURRENT cost(needed to add to final cost)
	 	public double setCurrentCost(int row, int column) {
			 return this.currentCost=costPerMileInCents/100*getDistanceBetweenCities [row] [column];
		}
//	 	sets cost per mile(in cents)
	 	public void setCostPerMileInCents(double cpm) {
	 		this.costPerMileInCents=cpm;
	 	}
//	 	gets cost per mile(in cents)
	 	public double getCostPerMileInCents() {
	 		 return this.costPerMileInCents;
		}
//	 	sets actual origin
	 	public void setActualOrigin(int column) {
			this.actualOrigin=column;
		}
//	 	gets actual origin(int)
	 	public int getActualOrigin() {
			return this.actualOrigin;
		}
//	 	sets round trip data, and returns the total distance for round trip.
	 	public int getRoundTripDistance() {
			 this.roundTrip=this.totalDistance+getDistanceBetweenCities [actualOrigin] [currentDestination];
			 return roundTrip;
		}
	 	
}//END OF CLASS
