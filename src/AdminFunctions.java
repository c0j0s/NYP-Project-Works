/**
 *<Description> : This is a class for admin funstions, only admin can access
 *adminMenu() will be called, prints menu and calls admenuSelection() to 
 *get user input to call methods according to input.
 *printAllAcc() retrieve and prints account details from AccountDetails.txt.
 *signUp() allow admin to create different account types and write to file.
 *delAcc() allow admin to delete account from AccountDetails.txt that matches 
 *email entered.
 *<Author> : Chen Jun Sheng
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminFunctions extends LoginPg{

	//constructor
	public AdminFunctions(){
		super(sc,ac);
	}

	//Admin menu
	public void adminMenu(){
		UiElement.darkBorder();
		System.out.println("        ADMIN PAGE: CAUTION!!");
		UiElement.darkBorder();
		System.out.println("Select a Service : ");
		UiElement.lightBorder();
		System.out.println("1. Show All Accounts");
		System.out.println("2. Create Accounts");
		System.out.println("3. Delete Accounts");
		System.out.println("4. Retrieve All Delivery Records");
		System.out.println("5. Retrieve All Booking Records");
		UiElement.lightBorder();
		System.out.println("0. Back to Main Page");
		UiElement.lightBorder();

		admenuSelection();
	}

	//Admin menu selection
	public void admenuSelection(){
		int input;
		do{
			System.out.print("Enter your choice (0-3):");
			input = sc.nextInt();
			if(input == 2){
				signUp();
				return;
			}else if(input == 3){
				UiElement.lightBorder();
				System.out.print("Enter Account Email to be removed :");
				String accToRemove = sc.next();
				FileMethod.deleteFromFile(sc,"AccountDetails.txt",accToRemove,0);
				return;
			}else if(input == 1){
				printAllAcc();
				return;
			}else if(input == 0){
				return;
			}else if(input == 4){
				retrieveDeliRecord();
				return;
			}else if(input == 5){
				retrieveBkRecord();
				return;
			}else{
				UiElement.errorMsg();
			}
		}while(input < 1 || input > 6);
	}

	private void retrieveBkRecord() {
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
				UiElement.lightBorder();
				System.out.println("ID:[" + details[0] + "] " + "Booked By Lecture: " + details[9]);
				System.out.println("Attendee Representative: "+ details[1]);
				System.out.println("["+details[2] + "] Contact: " + details[3]);
				System.out.println("Canteen: [" + details[4] + "] " + details[5] + " Attending");
				System.out.println("Date (DD/MM/YYYY): " + details[6] + " on " + details[7]);
				System.out.println("Booking Purpose: " + details[8]);
				UiElement.lightBorder();
			}
		}catch (FileNotFoundException e) 
		{
			System.out.println("Input file not found!");
		}

	}

	private void retrieveDeliRecord() {
		File inputFile = new File("deliveryReceipt.txt");
		String[] getdetails;
		try {
			Scanner readfile = new Scanner(inputFile);
			String[] getFile = new String[100];
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				getdetails = line.split(";");

				System.out.println("Email             : "+ getdetails[0]);
				System.out.println("Address           : "+ getdetails[1]);
				System.out.println("Contact Number    : "+ getdetails[2]);
				System.out.println("All orders        : "+ getdetails[3]);
				System.out.println("Total Amount      : $"+ getdetails[4]);
				UiElement.lightBorder();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		UiElement.backbtn(sc);
		return;
	}

	private void printAllAcc() {
		File inputFile = new File("AccountDetails.txt");
		String[] getdetails;
		try {
			Scanner readfile = new Scanner(inputFile);
			String[] getFile = new String[100];
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				getdetails = line.split(";");
				System.out.println("Email             : "+ getdetails[0]);
				System.out.println("User Name         : "+ getdetails[1]);
				System.out.println("School            : "+ getdetails[3]);
				System.out.println("Account Type      : "+ getdetails[4]);
				System.out.println("Security Questions: "+ getdetails[5]);
				UiElement.lightBorder();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		UiElement.backbtn(sc);
		return;
	}

	//Admin remove account


	//create special permission account
	public static void signUp(){
		String userName,passwd,schcode,actype,secQues,secAns;

		UiElement.lightBorder();
		System.out.println("Warning! Please give the approprate \naccount type for different permission!");
		UiElement.lightBorder();
		System.out.print("Enter Email (abc@mail.com): ");
		String email = sc.next();

		if(checkIfEmailReg(email) == false){	
			System.out.print("Enter User Name: ");
			userName = sc.next();
			System.out.print("Enter Password: ");
			passwd = sc.next();
			System.out.print("Enter School Code (eg:SIT): ");
			schcode = sc.next();
			System.out.print("Enter Account type (admin/staff/student): ");
			actype = sc.next();
			System.out.print("Create a Security Questions to retrive password: ");
			sc.nextLine();
			secQues = sc.nextLine();
			System.out.print("Enter an answer for the Security Questions: ");
			secAns = sc.nextLine();
			actype.toLowerCase();
		}else{
			UiElement.lightBorder();
			System.out.println("----------Email registered!---------");
			System.out.println("Please try again!");
			return;
		}

		try {
			FileWriter out = new FileWriter("AccountDetails.txt", true);
			out.write(email + ";" + userName + ";" + passwd + ";" + schcode + ";" + actype+ ";" + secQues + ";" + secAns + "\r\n");
			out.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}

		System.out.println("Successful!");
		return;
	}
}

