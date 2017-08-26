/**
 *<Description> :This is the class for canteen booking function, bkMain() will
 *be called first to call other methods in sequences.
 *bkMain() check if user login or not
 *getMenu() Menu selection for admin/staff
 *menuSelection() get user selected and go to different class
 *selectCanteen() get user selected canteen
 *getBkDetail() get details that user booked from text file
 *selectTime() get user selected time
 *bkCanteen() show booking statement and save to text file
 *getBkContactDetail() get user enter personal details
 *searchEdit() admin/staff edit user details/booking(s)
 *searchDelete() admin delete user booking(s)
 *<Author> : Kenny Lim (165257W)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Booking {
	static Account ac = null;
	static Scanner sc = null;
	String canteen;

	//constructor
	public Booking(Scanner sc, Account ac) {
		this.ac = ac;
		this.sc = sc;
	}

	//booking main method
	public void bkMain() {
		getMenu();
		String ans;

		do{
			System.out.print("Do you want to book canteen/search details ? (Y/N): ");
			ans = sc.next();
			if(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")))
			{
				UiElement.errorMsg();
			}
		}while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")));

		if(ans.equalsIgnoreCase("y")){
			if(LoginPg.isLogin == true){
				menuSelection();
			}else{
				UiElement.lightBorder();
				System.out.println("Please login to book/search !");
				LoginPg.lgMenu();
				menuSelection();
			}
		}
		else{
			UiElement.backbtn(sc);
			return;
		}		
	}

	//booking main menu
	public void getMenu(){
		UiElement.darkBorder();
		System.out.println("\t      BOOKINGS");
		UiElement.darkBorder();

		System.out.println("Booking Function");
		UiElement.lightBorder();
		System.out.println("1. Book Canteen");
		System.out.println("2. Booked Details");
		UiElement.lightBorder();

	}

	//get user menu selection
	public void menuSelection(){

		if(ac.actype.equalsIgnoreCase("admin")||ac.actype.equalsIgnoreCase("staff"))
		{
			int input;
			String canteen = null;
			UiElement.lightBorder();///////////////////////////////////////

			System.out.println("1. Book Canteen");
			System.out.println("2. Booked Details");
			System.out.println("0. Back to Main Page");
			UiElement.lightBorder();///////////////////////////////////////
			do{
				System.out.print("Enter your choice (0-2):");
				input = sc.nextInt();
				if(input < 0 || input > 2)
				{
					UiElement.errorMsg();
				}
			}while(input < 0 || input > 2);


			if(input == 2)
			{	
				int selectInput;

				UiElement.lightBorder();
				System.out.println("[0] ID [1] Name [2] Email [3] Contact Number");
				UiElement.lightBorder();///////////////////////////////////////
				do{
					System.out.print("Search By (0-3): ");
					selectInput = sc.nextInt();
					UiElement.lightBorder();///////////////////////////////////////
					if(selectInput < 0 || selectInput > 3)
					{
						UiElement.errorMsg();
					}
				}while(selectInput < 0 || selectInput > 3);
				
				
				getBkDetail(selectInput);
				return;
			}
			else if(input ==1)
			{
				canteen = selectCanteen();
				bkCanteen(canteen);
			}
			else if (input == 0)
			{
				UiElement.backbtn(sc);
				return;
			}

		}
		else if(LoginPg.isLogin == true)
		{
			UiElement.lightBorder();
			System.out.println("You can only search your booked detail(s)");
			int selectInput = 2;
			getBkDetail(selectInput);
			UiElement.backbtn(sc);
			return;
		}
	}

	//choose canteen
	public String selectCanteen() {

		int canInput;
		UiElement.lightBorder();
		System.out.println("[1] Koufu		[2] FoodCentral \r\n"
				+"[3] North Canteen	[4] South Canteen \r\n"
				+"[5] Foodgle Hub");
		UiElement.lightBorder();//////////////////////////////////////////////////
		
		do{
			System.out.print("Enter your choice (1-5):");
			canInput = sc.nextInt();
			switch(canInput)
			{
			case 1:
				canteen = "Koufu";
				break;
			case 2:
				canteen = "FoodCentral";
				break;
			case 3:
				canteen = "North Canteen";
				break;
			case 4:
				canteen = "South Canteen";
				break;
			case 5:
				canteen = "Foodgle Hub";
				break;
			default :
				UiElement.errorMsg();
			}
		}while(canInput < 1 || canInput > 5);

		return canteen;
	}

	//get user booking records
	private void getBkDetail(int selectInput) {

		String search = null;		

		if(selectInput == 0)
		{
			do
			{
				System.out.print("Enter ID: ");
				search = sc.next();

				if(search.length() < 1)
				{
					UiElement.errorMsg();
				}
			}while(search.length() < 1);
		}

		if(selectInput == 1)
		{
			do
			{
				System.out.print("Enter Name: ");
				sc.nextLine();
				search = sc.nextLine();

				if(search.length() < 1)
				{
					UiElement.errorMsg();
				}
			}while(search.length() < 1);
		}

		else if(selectInput == 2)
		{
			do
			{
				System.out.print("Enter Email: ");
				search = sc.next();

				if(search.length() < 1)
				{
					UiElement.errorMsg();
				}
			}while(search.length() < 1);
		}	

		else if(selectInput == 3)
		{
			do
			{
				System.out.print("Enter Contact Number: ");
				search = sc.next();

				if(search.length() < 1)
				{
					UiElement.errorMsg();
				}
			}while(search.length() < 1);
		}

		File inputFile = new File("BookingDetails.txt");
		int counter = 0;
		String userInput = null;
		try 
		{	
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{
				String line = sc1.nextLine();
				String[] details = line.split(";");

				userInput = details[selectInput];

				if(search.equalsIgnoreCase(details[selectInput]))
				{
					UiElement.lightBorder();
					System.out.println("	    Booked Details");
					UiElement.lightBorder();
					System.out.println("ID 			: " + details[0]);
					System.out.println("Name 			: " + details[1]);
					System.out.println("Email 			: " + details[2]);
					System.out.println("Contact 		: " + details[3]);
					System.out.println("Canteen 		: " + details[4]);
					System.out.println("Number of Attendees	: " + details[5]);
					System.out.println("Date (DD/MM/YYYY)	: " + details[6]);
					System.out.println("Time 			: " + details[7] + " (BOOKING LAST FOR 1HR)");
					System.out.println("Booking Purpose 	: " + details[8]);
					UiElement.lightBorder();
					System.out.println("Booked By -⬎");
					System.out.println("Lecture Name		: " + details[9]);
					if(ac.actype.equalsIgnoreCase("admin")||ac.actype.equalsIgnoreCase("staff"))
					{
						System.out.println("Lecture Email		: " + details[10]);
					}
					counter++;
				}
			}
			if(!(search.equalsIgnoreCase(userInput))&& counter < 1)
			{
				System.out.println(search + " not found!");
			}

			if(ac.actype.equalsIgnoreCase("admin")||ac.actype.equalsIgnoreCase("staff"))//Admin can delete, Staff and Admin can edit
			{
				int input;
				UiElement.lightBorder();
				if(counter > 0)
				{
					System.out.println("[1] Edit Detail(s)");
					if(ac.actype.equalsIgnoreCase("admin"))
					{
						System.out.println("[2] Delete Detail(s)");
					}	
				}
				System.out.println("[0] Back to Booking Menu");	
				UiElement.lightBorder();
				if(counter > 0 && ac.actype.equalsIgnoreCase("admin"))
				{
					do{
						System.out.print("Please enter your choice (0-2): ");
						input = sc.nextInt();
						if(input < 0 || input > 2)
						{
							UiElement.errorMsg();
						}
					}while(input < 0 || input > 2);
				}
				else if(counter > 0)
				{
					do{
						System.out.print("Please enter your choice (0-1): ");
						input = sc.nextInt();
						if(input < 0 || input > 1)
						{
							UiElement.errorMsg();
						}
					}while(input < 0 || input > 1);
				}

				else
				{
					do{
						System.out.print("Please enter (0): ");
						input = sc.nextInt();
						if(input != 0)
						{
							UiElement.errorMsg();
						}
					}while(input != 0);
				}

				if(input == 0)
				{
					menuSelection();
				}

				if(input == 1)
				{
					searchEdit();
				}
				else if(input == 2)
				{
					searchDelete();
				}
			}
			sc1.close(); 
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}


	}


	//booking time
	private String [] selectTime ()
	{
		int userInput;
		String [] datetime = new String[2];
		UiElement.lightBorder();
		System.out.println("Booking Time & Date");
		UiElement.lightBorder();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date getDate = new Date();
		Date enterDate = null;

		do{
			System.out.print("Enter booking date (DD/MM/YYYY): ");
			datetime[0] = sc.next();
			try {
				enterDate = dateFormat.parse(datetime[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(enterDate.before(getDate))
			{
				UiElement.errorMsg();
			}
		}while(enterDate.before(getDate));

		UiElement.lightBorder();
		System.out.println("    [1] 10AM [2] 11AM [3] 12PM"
				+"\n    [4] 1 PM [5] 2 PM [6] 3 PM"
				+"\n    [7] 4 PM [8] 5 PM");
		UiElement.lightBorder();
		System.out.println("EACH BOOKING LAST FOR 1HR.");
		UiElement.lightBorder();///////////////////////////////////////
		do
		{	
			System.out.print("Please enter your choice (1-8): ");
			userInput = Integer.parseInt(sc.next());

			switch(userInput)
			{
			case 1 :
				datetime[1] = "10AM"; 
				break;
			case 2 :
				datetime[1] = "11AM"; 
				break;
			case 3 :
				datetime[1] = "12PM"; 
				break;
			case 4 :
				datetime[1] = "1PM"; 
				break;
			case 5 :
				datetime[1] = "2PM"; 
				break;
			case 6 :
				datetime[1] = "3PM"; 
				break;
			case 7 :
				datetime[1] = "4PM"; 
				break;
			case 8 :
				datetime[1] = "5PM"; 
				break;
			default :
				UiElement.errorMsg();
			}

		}while(userInput < 1 || userInput > 8);

		return datetime;
	}

	//book canteen
	private void bkCanteen(String canteen) {

		String datetime [] = new String[2];		
		datetime = selectTime();

		String[] userDetails = getBkContactDetail();

		UiElement.darkBorder();
		System.out.println("          Booking Statement           ");
		UiElement.darkBorder();
		System.out.println("Name 			: " + userDetails[0]);
		System.out.println("Email 			: " + userDetails[1]);
		System.out.println("Contact 		: " + userDetails[2]);
		System.out.println("Canteen 		: " + canteen);
		System.out.println("Number of Attendees	: " + userDetails[3]);
		System.out.println("Date (DD/MM/YYYY)	: " + datetime[0]);
		System.out.println("Time 			: " + datetime[1] + " (BOOKING LAST FOR 1HR)");
		System.out.println("Booking Purpose 	: " + userDetails[4]);
		UiElement.lightBorder();
		System.out.println("Booked By -⬎");
		System.out.println("Lecture Name		: " + ac.userName);

		int id = 0;
		try 
		{	
			File inputFile = new File("BookingDetails.txt");
			int readID = 0;
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{
				String line = sc1.nextLine();
				String[] details = line.split(";");
				readID = Integer.parseInt(details[0]);
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


		String entry = id + ";" + userDetails[0] + ";" + userDetails[1] + ";" + userDetails[2] + ";" + canteen  + ";" + userDetails[3] + ";" + datetime[0]  + ";" + datetime[1] + ";" + userDetails[4] + ";" + ac.userName + ";" + ac.email;

		try
		{
			FileWriter fw = new FileWriter("BookingDetails.txt",true);
			fw.write(entry + "\r\n");
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		UiElement.lightBorder();
		System.out.println("[0] Booking Menu");
		UiElement.lightBorder();
		int input;
		do{
			System.out.print("Please enter (0): ");
			input = sc.nextInt();
			if(input == 0)
			{
				menuSelection();
			}
			if(input != 0)
			{
				UiElement.errorMsg();
			}
		}while(input != 0);

	}

	//get user details for canteen booking
	private String[] getBkContactDetail() {
		String [] details = new String [5];

		UiElement.lightBorder();
		System.out.println("Booking Details");
		UiElement.lightBorder();
		sc.nextLine();
		do{
			System.out.print("Name of Attendee Representative: ");
			details [0] = sc.nextLine();
			if(details[0].length() < 1)
			{
				UiElement.errorMsg();
			}
		}while(details[0].length() < 1);

		do{
			System.out.print("Email Address: ");
			details [1] = sc.next();
			if(details[1].length() < 1)
			{
				UiElement.errorMsg();
			}
		}while(details[1].length() < 1);

		do{
			System.out.print("Contact: ");
			details [2] = sc.next();
			if(details[2].length() != 8)
			{
				UiElement.errorMsg();
			}
		}while(details[2].length() != 8);

		do{
			System.out.print("Number of Attendees(Min 5): ");
			details [3] = sc.next();
			if(Integer.parseInt(details[3]) < 5)
			{
				UiElement.errorMsg();
			}
		}while(Integer.parseInt(details[3]) < 5);

		sc.nextLine();
		do{
			System.out.print("Booking Purpose: ");
			details [4] = sc.nextLine();
			if(details[4].length() < 1)
			{
				UiElement.errorMsg();
			}
		}while(details[4].length() < 1);
		return details;
	}

	//Admin/Staff Search Edit 
	private void searchEdit()
	{			
		int searchInput, counter = 0, editCounter = 0, counter2 = 0;
		do{
			System.out.print("Please enter the ID you wish to edit (0 to exit): ");
			searchInput = sc.nextInt();
		}while(searchInput < 0);

		if(searchInput == 0)
		{
			menuSelection();
			return;
		}

		File inputFile = new File("BookingDetails.txt");
		String [] editDetails = new String [11];

		try 
		{	
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{
				String line = sc1.nextLine();
				String[] details = line.split(";");

				if(searchInput != Integer.parseInt(details[0]))
				{
					String entry = line;

					try
					{
						if(counter == 0)
						{
							PrintWriter pw = new PrintWriter("BookingDetails.txt");
							pw.write(entry + "\r\n");
							pw.close();
							counter ++;
						}
						else
						{
							FileWriter fw = new FileWriter("BookingDetails.txt",true);
							fw.write(entry + "\r\n");
							fw.close();
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				if(searchInput == Integer.parseInt(details[0]))
				{
					for(int i = 0; i < editDetails.length; i++)
					{
						editDetails [i] = details [i];
						editCounter++;
					}
					if(counter == 0)
					{
						PrintWriter pw = new PrintWriter("BookingDetails.txt");
						pw.close();
					}
					counter2++;
				}	
			}

			String ans,entry = null;
			String datetime [] = null, details[] = null, canteen = null;
			int editInput = 0;

			UiElement.lightBorder();
			
			if(counter2 > 0)
			{
				System.out.println(("What do you want to edit ?\r\n")
						+"[1] Canteen [2] Time & Date [3] Details");
				UiElement.lightBorder();///////////////////////////////////////
				do{	
					System.out.print("Please enter your choice (1-3): ");
					editInput = sc.nextInt();
					if(editInput < 1 || editInput > 3)
					{
						UiElement.errorMsg();
					}
				}while(editInput < 1 || editInput > 3);
			}

			else
			{
				System.out.println("ID " + searchInput + " not found!");
				UiElement.lightBorder();
				System.out.println("[0] Booking Menu");
				UiElement.lightBorder();
				int input;
				do{
					System.out.print("Please enter (0): ");
					input = sc.nextInt();
					if(input == 0)
					{
						menuSelection();
					}
					if(input != 0)
					{
						UiElement.errorMsg();
					}
				}while(input != 0);
			}
			
			if(editCounter > 0)
			{
				if(editInput == 1)
				{
					canteen = selectCanteen();
					UiElement.lightBorder();
					System.out.println("          Edit Statement           ");
					UiElement.lightBorder();
					System.out.println("ID 			: " + editDetails[0]);
					System.out.println("Name 			: " + editDetails[1]);
					System.out.println("Email 			: " + editDetails[2]);
					System.out.println("Contact			: " + editDetails[3]);
					System.out.println("Canteen			: " + canteen + " [Change from: " + editDetails[4] + "]");
					System.out.println("Number of Attendees	: " + editDetails[5]);
					System.out.println("Date (DD/MM/YYYY)	: " + editDetails[6]);
					System.out.println("Time			: " + editDetails[7] + " (BOOKING LAST FOR 1HR)");
					System.out.println("Booking Purpose 	: " + editDetails[8]);
					UiElement.lightBorder();
				}
				else if(editInput == 2)
				{
					datetime = selectTime();
					UiElement.lightBorder();
					System.out.println("          Edit Statement           ");
					UiElement.lightBorder();
					System.out.println("ID 			: " + editDetails[0]);
					System.out.println("Name 			: " + editDetails[1]);
					System.out.println("Email 			: " + editDetails[2]);
					System.out.println("Contact			: " + editDetails[3]);
					System.out.println("Canteen			: " + editDetails[4]);
					System.out.println("Number of Attendees	: " + editDetails[5]);
					System.out.println("Date (DD/MM/YYYY)	: " + datetime[0] + " [Change from: " + editDetails[6] + "]");
					System.out.println("Time			: " + datetime[1] + " [Change from: " + editDetails[7] + "]"  + " (BOOKING LAST FOR 1HR)");
					System.out.println("Booking Purpose 	: " + editDetails[8]);
					UiElement.lightBorder();
				}
				else
				{
					details = getBkContactDetail();
					UiElement.lightBorder();
					System.out.println("          Edit Statement           ");
					UiElement.lightBorder();
					System.out.println("ID 			: " + editDetails[0]);
					System.out.println("Name 			: " + details[0] + " [Change from: " + editDetails[1] + "]");
					System.out.println("Email 			: " + details[1] + " [Change from: " + editDetails[2] + "]");
					System.out.println("Contact			: " + details[2] + " [Change from: " + editDetails[3] + "]");
					System.out.println("Canteen			: " + editDetails[4]);
					System.out.println("Number of Attendees	: " + details[3] + " [Change from: " + editDetails[5] + "]");
					System.out.println("Date (DD/MM/YYYY)	: " + editDetails[6]);
					System.out.println("Time			: " + editDetails[7] + " (BOOKING LAST FOR 1HR)");
					System.out.println("Booking Purpose 	: " + details[4] + " [Change from: " + editDetails[8] + "]");
					UiElement.lightBorder();
				}


				System.out.println("Booked By -⬎");
				System.out.println("Lecture Name		: " + editDetails[9] );
				System.out.println("Lecture Email		: " + editDetails[10] );
				UiElement.lightBorder();
				do{
					System.out.print("Do you want to save ? (Y/N): ");
					ans = sc.next();
					if(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")))
					{
						UiElement.errorMsg();
					}
				}while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")));

				if(ans.equalsIgnoreCase("y") && editInput == 1)
				{
					entry = editDetails[0] + ";" + editDetails[1] + ";" + editDetails[2] + ";" +  editDetails[3] + ";" + canteen + ";" + editDetails[5] + ";" + editDetails[6] + ";" + editDetails[7] + ";" + editDetails[8] + ";" + editDetails[9] + ";" + editDetails[10];
					System.out.println("ID " + editDetails[0] + " was saved.");
				}
				if(ans.equalsIgnoreCase("y") && editInput == 2) 
				{
					entry = editDetails[0] + ";" + editDetails[1] + ";" + editDetails[2] + ";" +  editDetails[3] + ";" + editDetails[4] + ";" + editDetails[5] + ";" + datetime[0] + ";" + datetime[1] + ";" + editDetails[8] + ";" + editDetails[9] + ";" + editDetails[10];
					System.out.println("ID " + editDetails[0] + " was saved.");
				}

				if(ans.equalsIgnoreCase("y") && editInput == 3) 
				{
					entry = editDetails[0] + ";" + details[0] + ";" + details[1] + ";" +  details[2] + ";" + editDetails[4] + ";" + details[3] + ";" + editDetails[6] + ";" + editDetails[7] + ";" + details[4] + ";" + editDetails[9] + ";" + editDetails[10];
					System.out.println("ID " + editDetails[0] + " was saved.");
				}

				if(ans.equalsIgnoreCase("n")) 
				{
					entry = editDetails[0] + ";" + editDetails[1] + ";" + editDetails[2] + ";" +  editDetails[3] + ";" + editDetails[4] + ";" + editDetails[5] + ";" + editDetails[6] + ";" + editDetails[7] + ";" + editDetails[8] + ";" + editDetails[9] + ";" + editDetails[10];
					System.out.println("ID " + editDetails[0] + " was not saved.");
				}

				try
				{
					FileWriter fw = new FileWriter("BookingDetails.txt",true);
					fw.write(entry + "\r\n");
					fw.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				if(editCounter == 0)
				{
					System.out.println("ID " + searchInput + " not found!");
				}
				sc1.close();
				UiElement.lightBorder();
				System.out.println("[0] Booking Menu");
				UiElement.lightBorder();
				int input;
				do{
					System.out.print("Please enter (0): ");
					input = sc.nextInt();
					if(input == 0)
					{
						menuSelection();
					}
					if(input != 0)
					{
						UiElement.errorMsg();
					}
				}while(input != 0);
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}

	}

	//Admin Search Delete
	private void searchDelete()
	{
		int searchInput, counter = 0, counter2 = 0;
		do{
			System.out.print("Please enter the ID you wish to delete (0 to exit): ");
			searchInput = sc.nextInt();
		}while(searchInput < 0);

		if(searchInput == 0)
		{
			UiElement.backbtn(sc);
			return;
		}

		File inputFile = new File("BookingDetails.txt");

		try 
		{	
			Scanner sc1 = new Scanner(inputFile); 
			while (sc1.hasNextLine())
			{

				String line = sc1.nextLine();
				String[] details = line.split(";");
				String entry = line;

				if(searchInput != Integer.parseInt(details[0]))
				{
					try
					{
						if(counter == 0)
						{
							PrintWriter pw = new PrintWriter("BookingDetails.txt");
							pw.write(entry + "\r\n");
							pw.close();
							counter ++;
						}
						else
						{
							FileWriter fw = new FileWriter("BookingDetails.txt",true);
							fw.write(entry + "\r\n");
							fw.close();
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				if(searchInput == Integer.parseInt(details[0]))
				{
					try
					{
						if(counter == 0)
						{
							PrintWriter pw = new PrintWriter("BookingDetails.txt");
							pw.write("");
							pw.close();
							counter ++;
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					System.out.println("ID " + details[0] + " was deleted.");
					counter2++;
				}
			}
			if(counter2 == 0)
			{
				System.out.println("ID " + searchInput + " not found!");
			}
			sc1.close();
			UiElement.lightBorder();
			System.out.println("[0] Booking Menu");
			UiElement.lightBorder();
			int  input;
			do{
				System.out.print("Please enter (0): ");
				input = sc.nextInt();
				if(input == 0)
				{
					menuSelection();
				}
				if(input != 0)
				{
					UiElement.errorMsg();
				}
			}while(input != 0);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}

	}

}