/**
 *<Description> :This class is to show the reviews, allow users and admin to delete, edit or add a review.
 *               CRMain(): To call other methods and print out the menu.
 *               CRnormalmenu(): To call the addReviews method for account type student and staff.
 *               CRadminmenu(): To print out the menu for account type admin and call the respective method for the respective choice.
 *               getReviews(): To read from file and print out the things in the file
 *               delR(): To delete the review that the user want to.
 *               addReviews(): To allow user to write their reviews and add it to file.
 *               ChangeR(): To allow user to edit their reviews and add it to file.
 *<Author> : Ting xian hao
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
//file index /0.name/1.date/2.subject/3.reviews

public class CReviews{
	static Account ac = null;
	static Scanner sc = null;
	static String[] community;

	//constructor
	public CReviews(Scanner sc,Account ac){
		this.ac = ac;
		this.sc = sc;

	}

	//normal account menu
	public static void CRnormalmenu(){
		addReview();
		System.out.println("Successful! Thank you for your review.");
		return;

	}

	//reviews main method
	public void CRMain(Scanner sc){
		UiElement.darkBorder();	
		System.out.println("           Community Reviews");
		UiElement.darkBorder();
		getReviews();
		if(ac.actype.equalsIgnoreCase("admin")){
			CRadminmenu();
		}
		else{
			getmenu();
		}
		return;
	}


	//admin function menu
	private static void CRadminmenu() {
		UiElement.lightBorder();
		System.out.println("Select a choice");
		UiElement.lightBorder();
		System.out.println("1. Add reviews");
		System.out.println("2. Edit your reviews");
		System.out.println("3. Delete reviews");
		System.out.println("0. Exit to main menu");
		UiElement.lightBorder();
		System.out.println("Enter your choice: ");
		int choice = sc.nextInt();

		switch(choice){
		case 1: addReview();
		System.out.println("Successful! Thank you for your review.");
		break;
		case 2: ChangeR();
		break;
		case 3: delR();
		break;
		default: UiElement.backbtn(sc);
		break;
		}
	}

	//Retrieve reveiws from txt file
	public static void getReviews(){


		try{
			File information = new File("CommunityReview.txt");
			Scanner readfile = new Scanner(information);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				community = line.split(";");

				//to split review content into individual words
				String content = community[6];
				String[] paragraph = content.split(" ");
				community[6] = "   ";
				//user for loop to do paragraphing
				for(int i = 0; i<paragraph.length;i++){
					community[6] = community[6] + paragraph[i] + " ";
					//estimated 7 empty spacing indentation looks best
					if(i%7 == 0 && i != 0){
						community[6] = community[6] + "\n";
					}
				}

				System.out.println("Subject: " + community[4]);
				System.out.println("["+community[5] +"] [" + community[3] +"]");
				System.out.println("Ratings: ["+community[7]+"/out of 5]");
				UiElement.lightBorder();
				System.out.println("Review: ");
				UiElement.lightBorder();
				System.out.println(community[6]);
				UiElement.darkBorder();
			}
			readfile.close();
		}
		catch(IOException e){
			System.out.println("File not found");
		}

	}

	//delete review
	public static void delR() {
		File inputFile = new File("CommunityReview.txt");
		if(ac.actype.equalsIgnoreCase("admin")){
			try{
				Scanner sc1 = new Scanner(inputFile);
				while (sc1.hasNextLine())
				{
					String line = sc1.nextLine();
					community = line.split(";");
					UiElement.lightBorder();
					System.out.println("ID: " + community[0]);
					System.out.println("Subject: " + community[4]);

				}

				System.out.println(" ");
				System.out.print("Please enter the id you wish to delete: ");
				int Input = sc.nextInt();
				FileMethod.deleteFromFile(sc, "CommunityReview.txt", Integer.toString(Input), 0);
				UiElement.lightBorder();
				System.out.println("Delete of review sucessful");
				UiElement.backbtn(sc);
			}
			catch(FileNotFoundException e){
				System.out.println("Input file not found!");
			}
		}
		else if(!ac.actype.equalsIgnoreCase("admin")){	
			try 
			{	
				Scanner sc2 = new Scanner(inputFile); 
				while (sc2.hasNextLine())
				{
					String line = sc2.nextLine();
					community = line.split(";");
					if (ac.email.equalsIgnoreCase(community[1])){
						UiElement.lightBorder();
						System.out.println("ID: " + community[0]);
						System.out.println("Subject: " + community[4]);
						UiElement.lightBorder();
					}

				}
				if(!ac.email.equalsIgnoreCase(community[1])){
					UiElement.lightBorder();
					System.out.println("No review is found !");
					UiElement.lightBorder();
					getmenu();
				}



				System.out.print("Please enter the id you wish to delete: ");
				int Input = sc.nextInt();
				try{
					Scanner sc3 = new Scanner(inputFile);
					while(sc3.hasNextLine()){
						String line = sc3.nextLine();
						community = line.split(";");
						if(Integer.toString(Input).equals(community[0])){
							if(ac.email.equals(community[1])){
								FileMethod.deleteFromFile(sc, "CommunityReview.txt", Integer.toString(Input), 0);
								UiElement.lightBorder();
								System.out.println("Delete of review sucessful");
								UiElement.backbtn(sc);
								return;
							}
							else{
								UiElement.lightBorder();
								System.out.println("Please don't delete other users review.");
								UiElement.backbtn(sc);
								return;
							}
						}
					}
					if(!Integer.toString(Input).equals(community[0])){
						System.out.println("No id is found!!");
						UiElement.backbtn(sc);
						return;
					}
				} 
				catch(FileNotFoundException e) 
				{
					System.out.println("Input file not found!");
				}
			}
			catch(FileNotFoundException e) 
			{
				System.out.println("Input file not found!");
			}
		}
		return;
	}



	//write into txt file
	public static void addReview() {
		UiElement.lightBorder();
		System.out.println("Adding your reviews");
		UiElement.lightBorder();
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ssa");
		System.out.print("Enter subject: ");
		sc.nextLine();
		String subject = sc.nextLine();
		System.out.print("Enter location: ");
		String location = sc.nextLine();
		System.out.print("Enter reviews: ");
		String reviews = sc.nextLine();
		System.out.print("Give ratings(out of 5): ");
		double ratings = sc.nextDouble();
		UiElement.lightBorder();
		int id = 0;
		try 
		{	
			File inputFile = new File("CommunityReview.txt");
			int readID = 0;
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{
				String line = sc1.nextLine();
				community = line.split(";");
				readID = Integer.parseInt(community[0]);
				if(id < readID)
				{
					id = readID;
					if(id < readID)
					{
						id = readID;
					}
				}
			}
			id++;
			sc1.close();
		}	
		catch (FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}




		try {
			FileWriter out = new FileWriter("CommunityReview.txt", true);
			out.write(id + ";" + ac.email + ";" + ac.userName + ";"+ df.format(today) + ";" + subject + ";" + location + ";" + reviews + ";" + ratings +"\r\n");
			out.close();

		} catch (IOException e) {
			System.out.println("File not found");
		}

		getReviews();
	}

	//Edit Review
	public static void ChangeR(){
		File inputFile = new File("CommunityReview.txt");
		try 
		{	
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{

				String line = sc1.nextLine();
				community = line.split(";");
				if (ac.email.equalsIgnoreCase(community[1])){
					UiElement.lightBorder();
					System.out.println("ID: " + community[0]);
					System.out.println("Subject: " + community[4]);
					UiElement.lightBorder();
				}

			}
			if(!ac.email.equalsIgnoreCase(community[1])){
				UiElement.lightBorder();
				System.out.println("No review is found! ");
				UiElement.lightBorder();
				getmenu();
			}

			System.out.print("Please enter the id you wish to edit: ");
			int Input = sc.nextInt();

			try 
			{	
				Scanner sc2 = new Scanner(inputFile); 
				while (sc2.hasNextLine())
				{
					String line = sc2.nextLine();
					community = line.split(";");
					if(Integer.toString(Input).equals(community[0])){
						if(ac.email.equals(community[1])){
							Date today = new Date();
							SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ssa");
							System.out.print("Enter subject: ");
							sc.nextLine();
							String subject = sc.nextLine();
							System.out.print("Enter location: ");
							String location = sc.nextLine();
							System.out.print("Enter reviews: ");
							String reviews = sc.nextLine();
							System.out.print("Give ratings(out of 5): ");
							double ratings = sc.nextDouble();
							String entry = Input + ";" + ac.email + ";" + ac.userName + ";"+ df.format(today) + ";" + subject + ";" + location + ";" + reviews + ";" + ratings +"\r\n";
							FileMethod.editFile(sc, "CommunityReview.txt", entry,Integer.toString(Input), 0);
							UiElement.lightBorder();
							System.out.println("Edit of review sucessful");
							UiElement.backbtn(sc);
							return;
						}
						else{
							UiElement.lightBorder();
							System.out.println("Please don't Edit other people's review.");
							getmenu();
						}
					}


				}
				if(!Integer.toString(Input).equals(community[0])){
					System.out.println("No id is found!!");
					getmenu();
				}

			}
			catch(FileNotFoundException e) 
			{
				System.out.println("Input file not found!");
			}
		}

		catch(FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}
		return;
	}
	public static void getmenu(){
		if(!ac.actype.equalsIgnoreCase("admin")){
			System.out.println("What would u like to do?");
			UiElement.lightBorder();////////////////////////////
			System.out.println("1. Write a Review");
			System.out.println("2. Edit your Review");
			System.out.println("3. Delete your review");
			System.out.println("0. Exit To Main Menu");
			UiElement.lightBorder();/////////////////////////////		
		}
		System.out.print("Enter your choice: "); ////////////////////////////////////////
		int choice = sc.nextInt();

		if(choice == 1){
			if(LoginPg.isLogin == true){

				if(ac.actype.equalsIgnoreCase("admin")){
					CRadminmenu();
				}
				else{
					CRnormalmenu();
				}
			}
			else{
				UiElement.lightBorder();
				System.out.println("Please login to add reviews!");
				LoginPg.lgMenu();
				if(ac.actype.equalsIgnoreCase("admin")){
					CRadminmenu();
				}
				else{
					CRnormalmenu();
				}
			}
		}
		if (choice == 2){
			if(LoginPg.isLogin == true){
				ChangeR();
			}
			else{
				System.out.println("please login to edit your reviews");
				LoginPg.lgMenu();
				if(ac.actype.equalsIgnoreCase("admin")){
					CRadminmenu();
				}
				else{
					ChangeR();
				}
			}
		}
		if (choice == 3){
			if(LoginPg.isLogin == true){
				delR();
			}
			else{
				System.out.println("please login to delete your reviews");
				LoginPg.lgMenu();
				if(ac.actype.equalsIgnoreCase("admin")){
					CRadminmenu();
				}
				else{
					delR();
				}
			}
		}

	}
}
