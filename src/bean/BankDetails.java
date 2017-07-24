package bean;

public class BankDetails {

	private int cardNumber;
	private int cvc;
	private String nameOfCardHolder;
	private String expiryMonth;
	private String expiryYear;
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	public String getNameOfCardHolder() {
		return nameOfCardHolder;
	}
	public void setNameOfCardHolder(String nameOfCardHolder) {
		this.nameOfCardHolder = nameOfCardHolder;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}


}
