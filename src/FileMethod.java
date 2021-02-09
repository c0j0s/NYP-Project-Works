import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *<Description> : This a class to contain common file method
 *deleteFromFile() remove line from file when getcondition is true
 *editFile() remove file when getcondition is true and write in new line with 
 *updated entry
 *<Author> : Chen Jun Sheng,Kenny Lim,Ting Xian Hao
 */

public class FileMethod {
	
	//to delete, require to get ToRemove and file name as string 
	//int y refers to file index that wish to compare, eg: 0 = email
	//file index refer to your own specific file
	public static void deleteFromFile(Scanner sc,String filename, String ToRemove,int y) {
		File inputFile = new File(filename);
		String[] getCondition;
		int i = 0;

		try {
			Scanner readfile = new Scanner(inputFile);
			String[] getFile = new String[200];
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				getCondition = line.split(";");
				if(!getCondition[y].equals(ToRemove)){
					getFile[i] = line;
					i++;
				}
			}
				FileWriter out = new FileWriter(inputFile, true);
				String[] writeFile = new String[i];
			
				for(int x = 0; x < writeFile.length;x++){
					writeFile[x] = getFile[x];
				}
				
				PrintWriter writer = new PrintWriter(inputFile);
				writer.print("");
				writer.close();
				
				for(String z :writeFile){
					out.write(z+"\n");
				}
				out.close();
				readfile.close();
			

		}catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	//to delete, require to get updated entry and file name as string 
	//getCondition is the string you want to compare with values in file
	//int y refers to file index that wish to compare, eg: 0 = email
	//file index refer to your own specific file
	public static void editFile(Scanner sc,String filename,String entry,String getCondition,int y) {
		File fe = new File(filename);
		String[] acc;

		try {
			FileWriter fw = new FileWriter(fe,true);
			Scanner readfile = new Scanner(fe);
			while(readfile.hasNextLine()){
				String line = readfile.nextLine();
				acc = line.split(";");
				if(getCondition.equals(acc[y])){
					deleteFromFile(sc,filename,acc[y],y);
					fw.write(entry);
					fw.close();
				}
			}
			fw.close();
			readfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
