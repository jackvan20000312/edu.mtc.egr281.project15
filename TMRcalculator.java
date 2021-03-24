package project15;
//****************************************************************************************
//Author: Jacob Vaught       Modified: 12/04/20, 12/09/2020
//
//EGR-281  Project 15			  Due: 12/09/20 6:00PM
//****************************************************************************************
//finished coding 12-04-2020  3:25PM

//I Hope You like the GUI. I also made a Console based version, but I spent a long time making this GUI. also, I did not put any 
//Fail-Safes, so if you click Start before both destination and origin are selected, it will cause problems 
//i made the GUI Yellow, DarkGray, and Blue.

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TMRcalculator implements ActionListener {

	static TMRCalculatorBackEnd TMRcalc = new TMRCalculatorBackEnd();
	public static DecimalFormat df = new DecimalFormat("$#,###,###.00");
	
	private final static String cityNames [] = {"Boston","Chicago","Dallas","Reno","Los Angeles","Miami","New Orleans","Toronto","Vancouver","Washington DC"};
	
    private static JFrame frame= new JFrame("TMR Calculator GUI");

    private static int currentCity;
    private static int destinationCity;
    private static int numberOfCities = cityNames.length;
    private static int numberOfDestinations=0;
    private static int originCity;
    //private static int totalDistanceTraveled = 0;

    private static String clickedButtonColor = "#66FCF1";//light blue-ish
    private static String defaultBackgroundColor = "#141824";//black-ish
    private static String defaultButtonColor = "#00458B";//dark blue-ish
    private static String goButtonColor = "#9B870C";//yellow-ish (easier to see for people))

	private static boolean hasTravelMethodRunYet=false;
	private static boolean hasFinalGUIMethod=false;
	
	private static JButton goButton;
	private static JButton[] leftButtons = new JButton[numberOfCities];
	private static JButton[] rightButtons = new JButton[numberOfCities];
	
	private static JLabel costPerMileLabel,numberOfDestinationsLabel,l3,l4,l5,l6;  
	private static JTextField costPerMileTexteBox;
	
	private static JTextField currentPriceTextuBox = new JTextField();
	private static JTextField currentDistanceTextuBox = new JTextField();
	private static JTextField totalPriceTextuBox = new JTextField();
	private static JTextField totalDistanceTextuBox = new JTextField();
	private static JTextField numberOfDestinationTexteBox = new JTextField();

	private final int BUTTONWIDTH = 125;
	private final static int BUTTONHEIGHT = 30;
	private final static int LABELHEIGHT = 18;
	private final static int DISTANCETOTOP = 50;
	private final int DISTANCEFROMLEFTSIDELEFTBUTTON = 10;
	private final int DISTANCEFROMLEFTSIDERIGHTBUTTON = 348;
	private final static int DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON = 142;
	private final static int HUNDRED=100;
	
	public TMRcalculator(){
		 try {
				frame.getContentPane().setBackground(getColor(defaultBackgroundColor));
			} catch (UnknownColorException e) {
				frame.getContentPane().setBackground(Color.BLACK);
			}//end of catch for frame background color
		
		 for(int i = 0; i < numberOfCities; ++i) {//creates 10 buttons on each side!!!
		 leftButtons[i]=new JButton(cityNames[i]);
		 leftButtons[i].setBounds(DISTANCEFROMLEFTSIDELEFTBUTTON, DISTANCETOTOP+2*BUTTONHEIGHT*i, BUTTONWIDTH, BUTTONHEIGHT );

		 rightButtons[i]=new JButton(cityNames[i]);
		 rightButtons[i].setBounds(DISTANCEFROMLEFTSIDERIGHTBUTTON, DISTANCETOTOP+2*BUTTONHEIGHT*i, BUTTONWIDTH, BUTTONHEIGHT );
		 try {
			rightButtons[i].setBackground(getColor(clickedButtonColor));
			rightButtons[i].setForeground(getColor(defaultButtonColor));
			leftButtons[i].setBackground(getColor(clickedButtonColor));
			leftButtons[i].setForeground(getColor(defaultButtonColor));
		} catch (UnknownColorException e) {
			rightButtons[i].setBackground(Color.BLUE);
			rightButtons[i].setForeground(Color.WHITE);
			leftButtons[i].setBackground(Color.BLUE);
			leftButtons[i].setForeground(Color.WHITE);
		} //end of catch for 20 button colors
		 
		 leftButtons[i].addActionListener(this);
		 rightButtons[i].addActionListener(this);

		 frame.add(leftButtons[i]);
		 frame.add(rightButtons[i]);
		 }//end of for loop to add 20 buttons and set their colors
		 
		 l5=new JLabel("Origin City");
		 l5.setBounds(DISTANCEFROMLEFTSIDELEFTBUTTON, 30, BUTTONWIDTH, LABELHEIGHT );
		 l6=new JLabel("Destination City");
		 l6.setBounds(DISTANCEFROMLEFTSIDERIGHTBUTTON, 30, BUTTONWIDTH, LABELHEIGHT );
		 l5.setFont(new Font("Arial", Font.PLAIN, 16));
		 l6.setFont(new Font("Arial", Font.PLAIN, 16));
		 
		 goButton=new JButton("Start!");  
	     goButton.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,DISTANCETOTOP+4*BUTTONHEIGHT, 200,BUTTONHEIGHT); 
	     goButton.addActionListener(this);
	     
		 l3=new JLabel("Current Dist:          Current Cost:");  
		 l3.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,35+6*BUTTONHEIGHT, 200,LABELHEIGHT);
		 l4=new JLabel("Total Dist:               Total Cost:");  
		 l4.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,35+8*BUTTONHEIGHT, 200,LABELHEIGHT);
		
		 totalPriceTextuBox = new JTextField("$#.##");//placeholder  
	     totalPriceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON+HUNDRED, DISTANCETOTOP+8*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );  
	     totalPriceTextuBox.setEditable(false);
	     totalDistanceTextuBox = new JTextField("#,### mile(s)");//placeholder  
	     totalDistanceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON, DISTANCETOTOP+8*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );  
	     totalDistanceTextuBox.setEditable(false);
		 currentPriceTextuBox = new JTextField("$#.##");//placeholder  
	     currentPriceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON+HUNDRED, DISTANCETOTOP+6*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );  
	     currentPriceTextuBox.setEditable(false);
	     currentDistanceTextuBox = new JTextField("#,### mile(s)");//placeholder  
	     currentDistanceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON, DISTANCETOTOP+6*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );  
	     currentDistanceTextuBox.setEditable(false);
		 
	     
		 costPerMileLabel=new JLabel("Cost Per Mile(Cents):");  
		 costPerMileLabel.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,27+2*BUTTONHEIGHT, 200,BUTTONHEIGHT);
		 costPerMileTexteBox=new JTextField("57.5");  
	     costPerMileTexteBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON, DISTANCETOTOP+2*BUTTONHEIGHT, 200, BUTTONHEIGHT ); 
	     costPerMileTexteBox.addActionListener(this);
		
		 numberOfDestinationsLabel=new JLabel("How Many Cities Are You Going To?");  
		 numberOfDestinationsLabel.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,27, 200,BUTTONHEIGHT);
		 numberOfDestinationTexteBox=new JTextField("2");  
	     numberOfDestinationTexteBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON, DISTANCETOTOP, 200, BUTTONHEIGHT );  
	     numberOfDestinationTexteBox.addActionListener(this);

	     
		  
	     try {//set all colors and add a "backup" plan(catch)
	    	goButton.setBackground(getColor(goButtonColor));
	    	l4.setForeground(getColor(clickedButtonColor));
	    	l3.setForeground(getColor(clickedButtonColor));
	    	l5.setForeground(getColor(clickedButtonColor));
	    	l6.setForeground(getColor(clickedButtonColor));
	    	costPerMileLabel.setForeground(getColor(clickedButtonColor));
			numberOfDestinationsLabel.setForeground(getColor(clickedButtonColor));
			totalPriceTextuBox.setBackground(getColor(clickedButtonColor));
			totalDistanceTextuBox.setBackground(getColor(clickedButtonColor));
			currentPriceTextuBox.setBackground(getColor(clickedButtonColor));
			currentDistanceTextuBox.setBackground(getColor(clickedButtonColor));
			numberOfDestinationTexteBox.setBackground(getColor(clickedButtonColor));
			costPerMileTexteBox.setBackground(getColor(clickedButtonColor));
		} catch (UnknownColorException e) {
		    goButton.setBackground(Color.GREEN); 
		    l4.setForeground(Color.WHITE);
		    l3.setForeground(Color.WHITE);
		    l5.setForeground(Color.WHITE);
	    	l6.setForeground(Color.WHITE);
			costPerMileLabel.setForeground(Color.WHITE);
			numberOfDestinationsLabel.setForeground(Color.WHITE);
			currentPriceTextuBox.setBackground(Color.WHITE);
			currentDistanceTextuBox.setBackground(Color.WHITE);
			numberOfDestinationTexteBox.setBackground(Color.WHITE);
			costPerMileTexteBox.setBackground(Color.WHITE);
		}//end of catch
        
	     frame.add(goButton);
	     frame.add(costPerMileTexteBox);
	     frame.add(numberOfDestinationTexteBox);
	     frame.add(costPerMileLabel);
	     frame.add(numberOfDestinationsLabel);
	     frame.add(totalDistanceTextuBox);
	     frame.add(totalPriceTextuBox);
	     frame.add(currentDistanceTextuBox);
	     frame.add(currentPriceTextuBox);
	     frame.add(l3);
	     frame.add(l4);
	     frame.add(l5);
	     frame.add(l6);
	     frame.setSize(500, 700);  
	     frame.setLayout(null);  
	     frame.setVisible(true);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	}//ending bracket of Constructor TMRcalculator
	
	    public static void setAllLeftButtons(int currentCity) {
	    	for(int i = 0; i < numberOfCities; ++i) {//creates 10 buttons on each side!!!
	   		 	if(i==currentCity) {
	   		 		try {
	   		 			leftButtons[i].setBackground(getColor(defaultButtonColor));
   		 				leftButtons[i].setForeground(getColor(clickedButtonColor));
	   		 		}catch(UnknownColorException e) {
	   		 			leftButtons[i].setBackground(Color.WHITE);
   		 				leftButtons[i].setForeground(Color.BLUE);
	   		 		}
	   		 	}else{
	   		 		try {
	   		 			leftButtons[i].setBackground(getColor(clickedButtonColor));
	   		 			leftButtons[i].setForeground(getColor(defaultButtonColor));
	   		 		}catch(UnknownColorException e) {
	   		 			leftButtons[i].setBackground(Color.BLUE);
	   		 			leftButtons[i].setForeground(Color.WHITE);
	   		 		} //end of catch for 20 button colors
	   		 	} //end of else statement
	   		 }//end of for loop
	    }//end of method setAllLeftButtons
	    
	    public static void setAllRightButtons(int destinationCity) {
	    	for(int i = 0; i < numberOfCities; ++i) {//creates 10 buttons on each side!!!
	   		 	if(i==destinationCity) {
	   		 		try {
	   		 			rightButtons[i].setBackground(getColor(defaultButtonColor));
   		 				rightButtons[i].setForeground(getColor(clickedButtonColor));
	   		 		}catch(UnknownColorException e) {
	   		 			rightButtons[i].setBackground(Color.WHITE);
   		 				rightButtons[i].setForeground(Color.BLUE);
	   		 		}
	   		 	}else{
	   		 		try {
	   		 			rightButtons[i].setBackground(getColor(clickedButtonColor));
	   		 			rightButtons[i].setForeground(getColor(defaultButtonColor));
	   		 		}catch(UnknownColorException e) {
	   		 			rightButtons[i].setBackground(Color.BLUE);
	   		 			rightButtons[i].setForeground(Color.WHITE);
	   		 		} //end of catch for 20 button colors
	   		 	} //end of else statement
	   		 }//end of for loop
	    }//end of method setAllRightButtons
	    
	 public static Color getColor(String color) throws UnknownColorException {
	    	    try {
	    	    // get color if user enters hexadecimal value- 
	    	    //if i get penalized for this extra feature, I will remove it. 
	    	      return Color.decode(color);
	    	    } catch (NumberFormatException numberformatexception) {
	    	      // if we can't decode, try to get it by name of color (i.e. "BLACK")    
	    	try {	    
	    		Field field = Class.forName("java.awt.Color").getField(color);
	    	    	    return (Color)field.get(null);   	
	    	} catch (Exception exception) {
	    		throw new UnknownColorException();
	    	    	}
	    	    }	
	    }//ending bracket of method getColor
	 
	    public static void main(String[] args) {              
			new TMRcalculator();
	    }//ending bracket of method main


		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==goButton) {
				numberOfDestinationsUpdate();
				costPerMileUpdate();
				goButtonRepeatMethod();
			}for(int i =0; i<numberOfCities; ++i) {
		       	if(ae.getSource()==leftButtons[i]&&!hasTravelMethodRunYet) {    //NEED TO FORCE THIS TO STOP ONCE TRAVEL HAS BEEN INITALIZED
		       		originCity=i;
		       		TMRcalc.setActualOrigin(i);
		       		setAllLeftButtons(i);
		       	}if(ae.getSource()==rightButtons[i]&&!hasFinalGUIMethod) {
		       		destinationCity=i;
		       		setAllRightButtons(i);
			    }
			}if(ae.getSource()==costPerMileTexteBox) {
				costPerMileUpdate();
		    }if(ae.getSource()==numberOfDestinationTexteBox) {
		    	numberOfDestinationsUpdate();
		    }
		}//end of method ActionPerformed
	
		private static void travel() {//updates all settings in BackEnd, must run every time user enters a new city;
			TMRcalc.setDestination(destinationCity);
			TMRcalc.setCurrentDistance(currentCity, destinationCity);
			TMRcalc.addToTotalDistance(currentCity, destinationCity);
			TMRcalc.setOrigin(destinationCity);
			TMRcalc.setCurrentCost(currentCity, destinationCity);
			TMRcalc.addToCurrentTotalCost(); 
			currentCity = destinationCity;
			TMRcalc.addToNumberOfCitiesCompleted();
			setAllLeftButtons(currentCity);
			hasTravelMethodRunYet=true;
		}//end of method travel        (Really important)

		private static void costPerMileUpdate() {
			TMRcalc.setCostPerMileInCents(Double.parseDouble(costPerMileTexteBox.getText()));
	       	costPerMileTexteBox.setEditable(false);
	       	costPerMileTexteBox.setBackground(Color.DARK_GRAY);
	       	costPerMileTexteBox.setForeground(Color.WHITE);
		}//end of method costpermileupdate
		private static void numberOfDestinationsUpdate() {
	       	numberOfDestinations = (Integer.parseInt(numberOfDestinationTexteBox.getText()));
	       	numberOfDestinationTexteBox.setEditable(false);
	       	numberOfDestinationTexteBox.setBackground(Color.DARK_GRAY);
	       	numberOfDestinationTexteBox.setForeground(Color.WHITE);
		}//end of method numberofDestinationUpdate
		private static void updateGUI() {
			totalPriceTextuBox.setText(df.format(TMRcalc.getTotalCost()));
			totalDistanceTextuBox.setText(TMRcalc.getTotalDistance()+" miles"); 
			currentPriceTextuBox.setText(df.format(TMRcalc.getCurrentCost()));
			currentDistanceTextuBox.setText(TMRcalc.getCurrentDistance()+" miles");
		}//end of method updateGUI
		private static void updateFinalGUI() {
			TMRcalc.addToNumberOfCitiesCompleted();
			TMRcalc.setCurrentCost(originCity, currentCity);
			TMRcalc.addToCurrentTotalCost();
			totalPriceTextuBox.setText(df.format(TMRcalc.getTotalCost()));
			totalDistanceTextuBox.setText(TMRcalc.getRoundTripDistance()+" miles"); 
			totalPriceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON+HUNDRED, DISTANCETOTOP+6*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );
			totalDistanceTextuBox.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON, DISTANCETOTOP+6*BUTTONHEIGHT, HUNDRED, BUTTONHEIGHT );  
			setAllRightButtons(originCity);
			currentPriceTextuBox.setVisible(false);
			currentDistanceTextuBox.setVisible(false);
			l3.setVisible(false);
			l4.setBounds(DISTANCEFROMLEFTSIDEFORMIDDLEBUTTON,35+6*BUTTONHEIGHT, 200,LABELHEIGHT);
			hasFinalGUIMethod=true;
		}//end of method updateFinalGUI
		
		private static void goButtonRepeatMethod() {
			if(TMRcalc.getNumberOfCitiesCompleted()<numberOfDestinations-1) {
				travel();
				updateGUI();
				//System.out.println(TMRcalc.getNumberOfCitiesCompleted()+"<"+(numberOfDestinations-1));
			}else if(TMRcalc.getNumberOfCitiesCompleted()<numberOfDestinations) {
				goButton.setText("Calculate Round Trip!");
				hasFinalGUIMethod=true;
				travel();
				updateGUI();
				
				//System.out.println(TMRcalc.getNumberOfCitiesCompleted()+"<"+numberOfDestinations);
			}else if(TMRcalc.getNumberOfCitiesCompleted()==numberOfDestinations){
				//travelFinal();
				updateFinalGUI();
				goButton.setText("Close!");
			}else {
				System.exit(0);
			}//end of else if
			
		}//end of method costpermileupdate

}//end of CLASS
