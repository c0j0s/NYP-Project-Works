/**
 *<Description> : This is a Ui class to unified UI experience in all methods
 *darkBorder() and lightBorder() is UI separators 
 *errorMsg() and endMsg() unifies message outputs
 *backbtn() provides options to exit to main menu or logout 
 *<Author> : Chen Jun Sheng
 */
import java.util.Scanner;

public class UiElement {

	//dark border to separate important method outputs
	public static void darkBorder(){
		System.out.println("=======================================");
	}

	//light border to separate menu options and outputs
	public static void lightBorder(){
		System.out.println("---------------------------------------");
	}

	//unified error message
	public static void errorMsg(){
		lightBorder();
		System.out.println("      v( @ . @ )v Invalid Input!       ");
		lightBorder();
	}

	//unified ending message
	public static void endMsg(){
		darkBorder();
		System.out.println("(ಥ_ಥ)_________THANK YOU__________(ಥ_ಥ)");
		System.out.println("Have a nice day!  (:3)  Bye Bye!");
		darkBorder();
		MainMenu.appLive = false;
		System.exit(0);	
	}

	public static void backbtn(Scanner sc){	
		darkBorder();
		System.out.println("What would you like to do next? :");
		darkBorder();
		System.out.println("0. Exit to main menu. ");
		if(LoginPg.isLogin == true){
			System.out.println("1. Logout and exit to main menu. ");
		}		
		int input;

		do{
			lightBorder();
			System.out.print("Enter your choice: ");
			input = sc.nextInt();
			if(input == 0){
				return;
			}else if(input == 1){
				if(LoginPg.isLogin == true){
					LoginPg.isLogin = false;
					return;
				}else{
					UiElement.errorMsg();
				}
			}else{
				errorMsg();
			}
		}while(input < 0 || input > 1);
	}

}
