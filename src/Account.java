/**
 *<Description> : This a class for Account object, contains all values
 *that can be access by other class
 *All variables will be initialise on login/sign up
 *<Author> : Chen Jun Sheng
 */

public class Account {
	String email;
	String passwd;
	String userName;
	String contact;
	String address;
	String actype;
	String schcode;
	String secQues,secAns;

	//default constructors
	public Account(){
		actype = "Student";//default account type
		
	}
}
