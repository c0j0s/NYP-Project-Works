/**
 *<Description> This class is for the portfolio features.
 *                getPortfolio(): To call the different methods. 
 *                getReviews(): To print out the time and date and the subject from the text file.
 *                getBkDetails(): To print out the location, booked time, number of people, booking purpose and contact number from the text file.
 *<Author> : Ting xian hao
 */


import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
public class Portfolio {
	static String[] views ;
	static Account ac = null;
	static Scanner sc = null;
	
	//constructor
	public Portfolio(Scanner sc, Account ac) {
		this.ac = ac;
		this.sc = sc;
	}
	
	//call other read method and output profolio 
	public void getPortfolio() {

		UiElement.darkBorder();	
		System.out.println("\t     Portfolio");
		UiElement.darkBorder();	
		System.out.println("Welcome " + ac.userName);
		UiElement.lightBorder();
		System.out.println("[" + ac.schcode.toUpperCase() + "] " + ac.actype.toUpperCase() + " " + ac.email);
		UiElement.lightBorder();
		System.out.println("Recent orders");
		UiElement.lightBorder();
		getdeliveryinfo();
		System.out.println("Recent Bookings");
		UiElement.lightBorder();
		getBkDetails();
		System.out.println("Recent Posts");
		UiElement.lightBorder();
		getReviews();
		UiElement.backbtn(sc);

	}
	
	//read review from txt file with same email
	public static void getReviews(){  
		File review = new File("CommunityReview.txt");
		int id = 0;
		try{
			Scanner readfile = new Scanner(review);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				views = line.split(";");
				if(views[1].equals(ac.email)){
					System.out.println(views[3]);
					System.out.println(views[4]);
					System.out.println(" ");
				}
		        }
		       

			readfile.close();
			return;	
		}
		catch(IOException e){
			System.out.println("File not found");
		}
		}
	
	//read booking details from txt file with same email
	public static void getBkDetails(){
		File details = new File("BookingDetails.txt");
		try{

			Scanner readfile = new Scanner(details);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				views = line.split(";");
				if(ac.actype.equalsIgnoreCase("admin")|| ac.actype.equalsIgnoreCase("staff")){
					if (views[10].equals(ac.email)){
						System.out.println("Student Name: " + views[1]);
						System.out.println("Student Email: " + views[2]);
						System.out.println("Location: " + views[4]);
						System.out.println("Booked Time: " + views[7]);
						System.out.println("Number of people: " + views[5]);
						System.out.println("Booking Purpose: " + views[8]);
						System.out.println("Contact Number: " + views[3]);
						UiElement.lightBorder();
						
					}
				}
				else{
				if(views[2].equals(ac.email)){
					System.out.println("Location: " + views[4]);
					System.out.println("Booked Time: " + views[7]);
					System.out.println("Number of people: " + views[5]);
					System.out.println("Booking Purpose: " + views[8]);
					System.out.println("Contact Number: " + views[3]);
					UiElement.lightBorder();
					

				}
			}
			}
			readfile.close();
			return;	
		}
		catch(IOException e){
			System.out.println("File not found");
		}

	}
	public static void getdeliveryinfo(){
		File info = new File("deliveryReceipt.txt");
		try{
			Scanner readfile = new Scanner(info);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				views = line.split(";");
				if(views[0].equals(ac.email)){
					System.out.println("Address: " + views[1]);
					System.out.println("Contact Number: " + views[2]);
					System.out.println("Food Ordered: " + views[3]);
					System.out.println("Total Price: " + views[4]);
					UiElement.lightBorder();
				}
			}
			readfile.close();
			return;	
		}
		
	catch(IOException e){
		System.out.println("File not found");
	}
		
	}


}