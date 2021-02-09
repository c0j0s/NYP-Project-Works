/**
*<Description> : This is the class for delivery function, deMain() will
*be called first to call other methods in sequences.
*getMenu() list down the menu
*getOrder() receives orders from user and input it into the system
*getInfo() collects personal details from user such as contact number etc.
*reOrder() allows user to reorder in case they selected wrongly
*getReceipt() stores values to be displayed for receipt in text file and also prints out for user
*writeReceipt() write receipts into a text file  which can be retrieved from admin function
*<Author> : Abdullah
*/
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Delivery{
	static Account ac = null;
	static Scanner sc = null;
	int counter = 0;
	static DecimalFormat fm = new DecimalFormat(".00");

	//constructor 
	public Delivery(Scanner sca, Account acc){
		ac = acc;
		sc = sca;
	}

	//main method 
	public void deMain(){
		String[][] receipt;
		getMenu();

		System.out.print("Do you want to place an order? (Y/N): ");
		String ans = sc.next();

		if(ans.equalsIgnoreCase("y")){
			if(LoginPg.isLogin == true){
				receipt = getOrder(ans);
			}else{
				UiElement.lightBorder();
				System.out.println("Please login to place orders!");
				LoginPg.lgMenu();
				receipt = getOrder(ans);
			}
		}else{
			UiElement.backbtn(sc);
			return;
		}

		getInfo(receipt);
		UiElement.backbtn(sc);
		return;
	}

	//delivery menu
	public void getMenu(){
		UiElement.darkBorder();
		System.out.println("\t      Delivery");
		UiElement.darkBorder();
		System.out.println("Menu: ");
		UiElement.lightBorder();
		System.out.println("1. Bento Box            $3.00");
		System.out.println("2. Chicken Chop         $5.00");
		System.out.println("3. Chicken Rice         $3.00");
		System.out.println("4. Fried Noodles        $3.50");
		System.out.println("5. Chicken Noodles      $3.00");
		UiElement.lightBorder();
	}

	//get user orders
	public String[][] getOrder(String ans){
		String[][] list = new String[50][4];
		counter = 0;

		while(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")){
			UiElement.lightBorder();
			System.out.print("Enter Dish Number To Order? : ");
			int choice = sc.nextInt();

			while(choice < 1 || choice > 5){
				UiElement.errorMsg();
				System.out.print("Enter Dish Number To Order? : ");
				choice = sc.nextInt();
			}

			switch(choice){
			case 1 : list[counter][0] = "Bento Box      "; list[counter][1] = "3.0" ; break;
			case 2 : list[counter][0] = "Chicken Chop   "; list[counter][1] = "5.0" ; break;
			case 3 : list[counter][0] = "Chicken Rice   "; list[counter][1] = "3.0" ; break;
			case 4 : list[counter][0] = "Fried Noodles  "; list[counter][1] = "3.5" ; break;
			case 5 : list[counter][0] = "Chicken Noodles"; list[counter][1] = "3.0" ; break;
			}

			System.out.print("How Many Do You Want? : ");
			list[counter][2] = sc.next();
			counter++;

			System.out.print("Do You Still Want To Order? (Y/N) :");
			ans = sc.next();

		}
		return list;
	}

	//get user details
	private void getInfo(String[][] receipt) {
		UiElement.lightBorder();
		System.out.print("Please enter your Contact number: ");
		ac.contact = sc.next();
		System.out.print("Please enter your Address: ");
		sc.nextLine();
		ac.address = sc.nextLine();
		UiElement.lightBorder();
		System.out.println("Payment Method: ");
		UiElement.lightBorder();
		System.out.println("1. Cash on Delivery \n2. Credit Card/NETS \n3. Edit Order\n0. Cancel Order");
		UiElement.lightBorder();
		int choice = 5;	
		while(choice < 1 || choice > 3){
			System.out.print("Pay By? (1/2):");
			choice = sc.nextInt();
			if(choice == 0){
				return;	
			}else if(choice == 1){
				getReceipt(receipt);
				return;			
			}else if(choice == 2){
				System.out.print("Credit card Number: ");
				String CcNum = sc.next();
				System.out.print("CSV: ");
				String Csv = sc.next();
				System.out.print("Expired Date (mm/yy): ");
				String CcDate = sc.next();
				System.out.println();
				getReceipt(receipt);
				return;
			}else if(choice==3){
				getMenu();
				String[][] newReceipt = new String[50][4]; 
				newReceipt = getOrder("y");
				getInfo(newReceipt);
				return;
			}else{
				UiElement.errorMsg();
			}
		}
	}

	private void reOrder() {
		getMenu();
		String[][] newReceipt = new String[50][4]; 
		newReceipt = getOrder("y");
		getInfo(newReceipt);
	}

	//print final receipt
	public void getReceipt(String[][] list){
		String[][] finalReceipt = new String[counter][4];		
		//copying data to new array
		for(int i = 0; i < finalReceipt.length; i++){
			for(int k = 0; k < 3; k++){
				finalReceipt[i][k] = list[i][k];
			}			
		}
		//calculate cost for each list
		double ttAmt = 0;
		for(int i = 0; i < finalReceipt.length; i++){
			//individual sum
			double sum =  Double.parseDouble(finalReceipt[i][1]) * Double.parseDouble(finalReceipt[i][2]);
			finalReceipt[i][3] = Double.toString(sum);
			//total sum
			ttAmt = ttAmt + sum;
		}

		//output final orders
		UiElement.darkBorder();	
		System.out.println("               RECEIPT               ");
		UiElement.darkBorder();	
		System.out.println("Name:                   "+ ac.userName);
		System.out.println("Email:                  "+ ac.email);
		System.out.println("Contact No:             "+ ac.contact);
		System.out.println("Address:                "+ ac.address);		
		UiElement.lightBorder();				
		System.out.println("Your orders: ");
		UiElement.lightBorder();
		for(int i = 0; i < finalReceipt.length; i++){
			System.out.println(finalReceipt[i][2] + " " + finalReceipt[i][0] + "        $" + finalReceipt[i][3] +"0.");
		}
		UiElement.lightBorder();
		System.out.println("Total Amount:            $" + fm.format(ttAmt));
		UiElement.lightBorder();
		System.out.println("Your order will arrive in 1 hr.");
		UiElement.lightBorder();
		System.out.println("Thank you for using our service.");
		writeReceipt(ttAmt,finalReceipt);
		return;
	}
	
	//write receipt to file
	public static void writeReceipt(double ttAmt, String[][] receipt){
			String dish="";
	
			for(int i =0;i <receipt.length;i++){
	
				dish = dish + receipt[i][2] + " " + receipt[i][0]+"|" ;
	
			}
			String entry = ac.email +";"+ ac.address +";" + ac.contact +";"+ dish + ";" + fm.format(ttAmt) + "\r\n";
			try
			{
				FileWriter fw = new FileWriter("deliveryReceipt.txt", true);
				fw.write(entry + "\r\n");
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
}
