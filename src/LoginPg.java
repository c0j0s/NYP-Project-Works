/**
 *<Description> : This is the class for login functions
 *lgMenu() will be called from other methods to print login menus
 *lgMenu() calls lgMenuSelection to get user input and calls other
 *methods according to user input.
 *login() takes in user email and password, compare with values in
 *AccountDetails.txt, changes isLogin value to give or denied access.
 *signUp() allows user to create an account (default:student type), it 
 *writes user inputs in to AccountDetails.txt and allow user access immediately.
 *forgetPw will print security questions entered upon registering, prints user
 *password if answer matches in AccountDetails.txt
 *resetPw() allow user to reset their own passowrd
 *<Author> : Chen Jun Sheng
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//file index /0.email/1.username/2.passwd/3.schcode/4.actype/5.secQues/6.secAns

public class LoginPg {
	public static boolean isLogin = false;
	static Scanner sc = null;
	static Account ac = null;
	static int input;

	public LoginPg(Scanner sc,Account ac){
		this.sc = sc;
		this.ac = ac;
	}

	public static void lgMenu() {
		UiElement.darkBorder();	
		System.out.println("\t   Login/Sign Up");
		UiElement.darkBorder();	
		System.out.println("Select a Service : ");
		UiElement.lightBorder();
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Forget Password");
		UiElement.lightBorder();
		System.out.println("0. Back to Main Page");
		UiElement.lightBorder();

		lgMenuSelection();
		return;
	}

	public static void lgMenuSelection() {		
		do{
			System.out.print("Enter your choice (0-3):");
			input = sc.nextInt();
			if(input == 1){
				Login();
				return;
			}else if(input == 2){
				signUp();
				return;
			}else if(input == 3){
				forgetPw();
				return;
			}else if(input == 0){
				MainMenu.main(null);
			}else{
				UiElement.errorMsg();
			}
		}while(input < 1 || input > 4);

	}

	public static void Login(){
		while(isLogin == false){
			UiElement.lightBorder();
			System.out.print("Enter Your Email (abc@mail.com): ");
			ac.email = sc.next();
			System.out.print("Enter Your Password: ");
			ac.passwd = sc.next();

			File fe = new File("AccountDetails.txt");
			String[] acc = new String[3];

			try {
				Scanner readfile = new Scanner(fe);
				while(readfile.hasNextLine()){
					String line = readfile.nextLine();
					acc = line.split(";");

					if(ac.email.equals(acc[0])){
						if(ac.passwd.equals(acc[2])){
							ac.userName = acc[1];
							ac.actype = acc[4];
							ac.schcode = acc[3];
							ac.secQues = acc[5];
							ac.secAns = acc[6];
							isLogin = true;
							readfile.close();
							return;
						}else{
							isLogin = false;
						}
					}else{
						isLogin = false;
					}
				}
				readfile.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			UiElement.lightBorder();
			System.out.println("--------Invalid Account info!--------");
			System.out.println("Please try again!");
		}
		return;
	}

	public static void signUp() {
		UiElement.lightBorder();
		System.out.print("Enter Your Email (abc@mail.com): ");
		ac.email = sc.next();

		if(checkIfEmailReg(ac.email) == false){	
			System.out.print("Enter Your User Name: ");
			ac.userName = sc.next();
			System.out.print("Enter Your Password: ");
			ac.passwd = sc.next();
			System.out.print("Enter Your School Code (eg:SIT): ");
			ac.schcode = sc.next();
			ac.actype = "Student";
			System.out.print("Create a Security Questions to retrive password: ");
			sc.nextLine();
			ac.secQues = sc.nextLine();
			System.out.print("Enter an answer for the Security Questions: ");
			ac.secAns = sc.nextLine();
			LoginPg.isLogin = true;
		}else{
			UiElement.lightBorder();
			System.out.println("----------Email registered!---------");
			System.out.println("Please try again!");
			return;
		}

		try {
			FileWriter out = new FileWriter("AccountDetails.txt", true);
			out.write(ac.email + ";" + ac.userName + ";" + ac.passwd + ";" + ac.schcode + ";" + ac.actype+ ";" + ac.secQues + ";" + ac.secAns + "\r\n");
			out.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
		System.out.println("Successful!");
		return;
	}

	//to check if email is already registered 
	public static boolean checkIfEmailReg(String email) {
		File fe = new File("AccountDetails.txt");
		String[] acc;

		try {
			Scanner readfile = new Scanner(fe);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				acc = line.split(";");
				if(email.equals(acc[0])){
					readfile.close();
					return true;
				}
			}
			readfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return false;
	}

	public static void forgetPw() {
		UiElement.lightBorder();
		System.out.print("Enter Your Email: ");
		String email = sc.next();
		UiElement.lightBorder();

		File fe = new File("AccountDetails.txt");
		Scanner readfile;
		try {
			readfile = new Scanner(fe);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				String acc[] = line.split(";");
				if(email.equals(acc[0])){
					ac.email = acc[0];
					ac.userName = acc[1];
					ac.schcode = acc[3];
					ac.actype = acc[4];
					ac.secQues = acc[5];
					ac.secAns = acc[6];
					ac.passwd = acc[2];
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if(checkIfEmailReg(email) == true){
			sc.nextLine();
			for(int t = 0; t < 5; t++){
				System.out.println(ac.secQues);
				UiElement.lightBorder();
				System.out.print("Enter your answer: ");
				String ans = sc.nextLine();
				if(ans.equals(ac.secAns)){
					UiElement.lightBorder();
					System.out.println("Reset your password!");
					resetPw(ac);/////////////////////////////////////////
					break;
				}else{
					System.out.println("Please Try Again (Max 5 tries)");
					UiElement.lightBorder();
				}
			}	
			UiElement.backbtn(sc);
		}else{
			System.out.println("The Email you have Entered is not registered!");
			System.out.println("Please try again or sign up!");
		}
		return;
	}

	public static void resetPw(Account ac) {////////////////////////////
				
		UiElement.lightBorder();
		System.out.print("Enter your new password: ");
		String newPw = sc.next();
		
		String entry = ac.email + ";" + ac.userName + ";" + newPw + ";" + ac.schcode 
				+ ";" + ac.actype+ ";" + ac.secQues + ";" + ac.secAns + "\r\n";
		
		FileMethod.editFile(sc,"AccountDetails.txt",entry,ac.email,0);
		UiElement.lightBorder();
		System.out.println("Successful");
		return;
	}

}

