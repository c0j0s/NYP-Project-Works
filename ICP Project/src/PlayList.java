/**
*<Description> : This is the class for music function, plMain() will
*be called first to call other methods in sequences.
*getCurrentPl() retrieve music that is currently playing from text file
*getCanteenMenu() user select location of canteen
*getMusicMenu() list down the category of music
*musicMenuSelection() allows program to store information based on user selection.
*engsong() lists down English songs retrieved from text file
*chisong() lists down Chinese songs retrieved from text file
*kosong() lists down groan songs retrieved from text file
*WriteMlist() retrieves input from user and add it to text file for retrieving in the current playlist.
*<Author> : Desmond Koh Ti Yong, Ting Xian Hao
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayList {
	static Scanner sc = null;
	static Account ac = null;
	static String name,canteen;

	public PlayList(Scanner sc, Account ac) {
		this.sc = sc;
		this.ac = ac;
	}

	public void plMain() {
		int choice = 0;
		UiElement.darkBorder();	
		System.out.println("\t   Canteen Playlist");
		UiElement.darkBorder();	
		System.out.println("Select Canteen :");
		UiElement.lightBorder();
		getCurrentPl();
		UiElement.lightBorder();
		System.out.println("1. Add Music to Playlist");
		if(ac.actype.equals("admin")){
			System.out.println("2. Delete Music from Playlist");
		}
		UiElement.lightBorder();
		System.out.println("0. Exit");
		UiElement.lightBorder();
		
		if(LoginPg.isLogin == true){
			while(choice > 0 || choice < 2) {
				if(ac.actype.equalsIgnoreCase("admin"))
				{
					System.out.print("Enter your choice (0-2): ");	
				}
				else
				{
					System.out.print("Enter your choice (0-1): ");
				}
				choice = sc.nextInt();
				
				if(choice == 1){
					getCanteenMenu();
					
					
					return;
				}else if(choice == 2){
					if(ac.actype.equals("admin")){
						delSong();
					}else{
						UiElement.errorMsg();
					}
					return;
				}else if(choice == 0){
					UiElement.backbtn(sc);
					return;
				}else {
					UiElement.errorMsg();
				}
			}
			return;
		}else{
			UiElement.lightBorder();
			System.out.println("Please login Select Your Service!");
			LoginPg.lgMenu();
			UiElement.lightBorder();
			plMain();
			return;
		}	
		
		
	}

	private void delSong() {
		UiElement.lightBorder();
		System.out.println("Enter the Song Name to delete from list");
		sc.nextLine();
		String songToDel = sc.nextLine();
		FileMethod.deleteFromFile(sc, "MusicList.txt", songToDel, 2);
		UiElement.lightBorder();
		System.out.println("Successful!");
	}

	private void getCurrentPl() {
		String []List;
		int index = 1;
		System.out.println("Currently Playing:");
		UiElement.lightBorder();
		try{
			File Music = new File("MusicList.txt");
			Scanner readfile = new Scanner(Music);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				List = line.split(";");
				System.out.println("["+ index+ "] " +"["+ List[1]+ "] " + List[2]);
				index++;
			}
			readfile.close();
		}catch(IOException e){
			System.out.println("File not found");
		}

	}

	private void getCanteenMenu() {
		int choice = -1;
		UiElement.lightBorder();
		System.out.println("1. North Canteen");
		System.out.println("2. South Canteen");
		System.out.println("3. KouFu");
		System.out.println("4. Foodgle");
		UiElement.lightBorder();
		System.out.println("0. Exit");
		UiElement.lightBorder();
		
		while(choice < 0 || choice > 4) {
			System.out.print("Enter your choice (0-4): ");
			choice = sc.nextInt();
			
			if(choice == 1){
				canteen = "North Canteen";
				getMusicMenu();
				musicMenuSelection();
				return;
			}else if(choice == 2){
				canteen = "South Canteen";
				getMusicMenu();
				musicMenuSelection();
				return;
			}else if(choice == 3){
				canteen = "KouFu";
				getMusicMenu();
				musicMenuSelection();
				return;
			}else if(choice == 4){
				canteen = "Foodgle";
				getMusicMenu();
				musicMenuSelection();
				return;
			}else if(choice == 0){
				UiElement.backbtn(sc);
				return;
			}else if (choice < 0 || choice > 4){
				UiElement.errorMsg();
			}

		}
	
	}

	//music menu
	private void getMusicMenu() {
		UiElement.darkBorder();	
		System.out.println("     Background Music Selection");
		UiElement.darkBorder();	

		System.out.println("Title Menu");
		UiElement.lightBorder();
		System.out.println("1. English song");
		System.out.println("2. Chinese song");
		System.out.println("3. Korean song");
		System.out.println("4. Enter your own song");
		System.out.println("5. Display playlist");
		UiElement.lightBorder();
		System.out.println("0. Exit");		
		UiElement.lightBorder();
	
	}

	//music menu selection
	private void musicMenuSelection() {
		int choice = -1;
		while(choice < 0 || choice > 5) {
			
			System.out.print("Enter the your choice (0-5): ");
			choice = sc.nextInt();
			UiElement.lightBorder();
			if (choice == 1) {
				engsong();
				break;
			} else if (choice == 2) {
				chisong();
				break;
			} else if (choice == 3) {
				kosong();
				break;
			} else if (choice == 4) {
				System.out.print("Enter your title :");
				sc.nextLine();
				name = sc.nextLine();
				writeMlist(name);
				break;

			} 
			else if(choice == 5){
				getCurrentPl();
				getMusicMenu();
				musicMenuSelection();
			}
			else if(choice == 0 ){
				UiElement.backbtn(sc);
				break;
			}
			if(choice < 0 || choice > 5)
			{
				UiElement.errorMsg();
			}
		}
		//UiElement.backbtn(sc);

	}

	//english song list
	public static void engsong(){
		UiElement.lightBorder();
		System.out.println("English Song");
		UiElement.lightBorder();
		System.out.println("1. 7 Years");
		System.out.println("2. This Love");
		System.out.println("3. Payphone");
		System.out.println("4. sugar");
		System.out.println("5. Rude");
		System.out.println("6. Black and Yellow");
		UiElement.lightBorder();
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		switch(choice){
		case 1: name = "7 Years";
		writeMlist(name);
		break;
		case 2: name = "This Love";
		writeMlist(name);
		break;
		case 3: name = "Payphone";
		writeMlist(name);
		break;
		case 4: name = "sugar";
		writeMlist(name);
		break;
		case 5: name = "Rude";
		writeMlist(name);
		break;
		case 6: name = "Black and Yellow";
		writeMlist(name);
		break;
		default: UiElement.backbtn(sc);
		}
	}

	//chinese song list
	public static void chisong(){
		UiElement.lightBorder();
		System.out.println("Chinese Song");
		UiElement.lightBorder();
		System.out.println("1. 木乃伊 ");
		System.out.println("2. 说好的幸福呢");
		System.out.println("3. 給我一首歌的時间");
		System.out.println("4. 她说");
		System.out.println("5. 我难过");
		System.out.println("6. 爱情漫游");
		UiElement.lightBorder();
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		switch(choice){
		case 1: name = "木乃伊";
		writeMlist(name);
		break;
		case 2: name = "说好的幸福呢";
		writeMlist(name);
		break;
		case 3: name = "給我一首歌的時间";
		writeMlist(name);
		break;
		case 4: name = "她说";
		writeMlist(name);
		break;
		case 5: name = "我难过";
		writeMlist(name);
		break;
		case 6: name = "爱情漫游";
		writeMlist(name);
		break;
		default: UiElement.backbtn(sc);
		}
	}

	//korea song list
	public static void kosong(){
		UiElement.lightBorder();
		System.out.println("Korea Song");
		UiElement.lightBorder();
		System.out.println("1. Gentleman");
		System.out.println("2. Wedding Dress");
		System.out.println("3. Heart Attack");
		System.out.println("4. Confused");
		System.out.println("5. Get Out");
		System.out.println("6. Love Is Only You");
		UiElement.lightBorder();
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		switch(choice){
		case 1: name = "Gentleman";
		writeMlist(name);
		break;
		case 2: name = "wedding Dress";
		writeMlist(name);
		break;
		case 3: name = "Heart Attack";
		writeMlist(name);
		break;
		case 4: name = "Confused";
		writeMlist(name);
		break;
		case 5: name = "Get Out";
		writeMlist(name);
		break;
		case 6: name = "Love Is Only You";
		writeMlist(name);
		break;
		default: UiElement.backbtn(sc);
		}
	}

	public static void writeMlist(String name){
		try {
			FileWriter out = new FileWriter("MusicList.txt", true);
			out.write(ac.email + ";" + canteen + ";" + name + "\r\n" );
			System.out.println(name + " has been added to the list.");
			out.close();

		} catch (IOException e) {
			System.out.println("File not found");
		}

	}

}
