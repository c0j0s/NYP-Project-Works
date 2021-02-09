/**
 *<Description> : This is the main method to run on start.
 *main() calls getMainMenu() to print the main menu.
 *main() calls startActivity() to prompt for a user input and 
 *calls other methods according to user input.
 *getMainMenu() will be loop again once return from other methods appLive is true
 *<Author> : Chen Jun Sheng
 */

import java.util.Scanner;

public class MainMenu{
	public static boolean appLive = true;

	//Run Main
	public static void main(String[] args) {
		
		Account ac = new Account();

		//main menu continue to print after returning from other method
		while(appLive == true){
			
			try{
				Scanner sc = new Scanner(System.in);
				getMainMenu(ac);
				startActivity(sc,ac);
			}
			catch(Exception e){
				UiElement.darkBorder();
				System.out.println("v( -.- )v \nDon't enter funny funny values\n@Abdullah 2016");
			}
		}
	}

	//Main menu
	public static void getMainMenu(Account ac){
		UiElement.darkBorder();
		System.out.println(" Welcome to Interactive Canteen Portal");
		UiElement.darkBorder();
		System.out.println("Select a Service : ");
		UiElement.lightBorder();
		System.out.println("1. Community Reviews");
		System.out.println("2. Delivery");
		System.out.println("3. Bookings");
		System.out.println("4. Canteen Playlist");
		UiElement.lightBorder();
		if(LoginPg.isLogin == false){
			System.out.println("5. Login/Sign up");
		}else if(ac.actype.equalsIgnoreCase("admin")){
			System.out.println("7. My Profile");
			System.out.println("8. Admin Funtions");
			System.out.println("9. Change Password");
			UiElement.lightBorder();
			System.out.println("0. Sign out");
		}else{
			System.out.println("7. My Profile");
			System.out.println("9. Change Password");
			UiElement.lightBorder();
			System.out.println("0. Sign out");
		}
		UiElement.lightBorder();
		System.out.println("6. Exit");
		UiElement.lightBorder();
	}

	//call other classes
	private static void startActivity(Scanner sc,Account ac) {
		LoginPg lg = new LoginPg(sc,ac);
		int input;

		do{
			System.out.print("Enter your choice: ");
			input = sc.nextInt();
			if(input == 1){
				CReviews cr = new CReviews(sc,ac);
				cr.CRMain(sc);
				return;
			}else if(input == 2){
				Delivery de = new Delivery(sc,ac);
				de.deMain();
				return;
			}else if(input == 3){
				Booking bk = new Booking(sc,ac);
				bk.bkMain();
				return;
			}else if(input == 4){
				PlayList ot = new PlayList(sc,ac);
				ot.plMain();
				return;
			}else if(input == 5){
				lg.lgMenu();
				if(LoginPg.isLogin == true){
					Portfolio pf = new Portfolio(sc,ac);
					pf.getPortfolio();
				}
				return;
			}else if(input == 6){
				UiElement.endMsg();
				return;
			}else if(input == 0){
				if(LoginPg.isLogin == true ||ac.actype.equals("admin")){ 
					LoginPg.isLogin = false;
					main(null);
				}else if(LoginPg.isLogin == false){
					UiElement.errorMsg();
				}
				return;
			}else if(input == 9){
				if(LoginPg.isLogin == true){ 
					lg.resetPw(ac);
				}else if(LoginPg.isLogin == false){
					UiElement.errorMsg();
				}
				return;
			}else if(input == 7){
				if(LoginPg.isLogin == true){ 
					Portfolio pf = new Portfolio(sc,ac);
					pf.getPortfolio();
				}else if(LoginPg.isLogin == false){
					UiElement.errorMsg();
				}
				return;
			}else if(input == 8){
				if(ac.actype.equals("admin")){ 
					AdminFunctions af = new AdminFunctions();
					af.adminMenu();
				}else{
					UiElement.errorMsg();
				}
				return;
			}else{
				UiElement.errorMsg();
				return;
			}
		}while(input < 1 || input > 9);

	}

}
