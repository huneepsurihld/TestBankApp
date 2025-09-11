package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class CustomerLogin extends BaseClass {

	
	public CustomerLogin(WebDriver driver) 
	{
		super(driver);
		
	}

	
	private By nameDrp = By.id("userSelect");
	private By login = By.xpath("//button[text()='Login']");
	private By header = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/strong/span");
	private By accNumDrp = By.xpath("//select[@name='accountSelect']");
	private By accNum = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/strong[1]");
	private By bal = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/strong[2]");
	private By currency = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/strong[3]");
	private By trans = By.xpath("//button[@ng-click='transactions()']");
	private By deposit = By.xpath("//button[@ng-click='deposit()']");
	private By withdraw = By.xpath("//button[@ng-click='withdrawl()']");
	private By home= By.xpath("//button[@ng-click='home()']");
	private By logout = By.xpath("//button[@ng-click='byebye()']");
	private By reset=By.xpath("//button[@ng-click='reset()']");
	private By back=By.xpath("//button[@ng-click='back()']");
	private By transTable= By.xpath("//table[@class='table table-bordered table-striped']");
	private By amount = By.xpath("//input[@ng-model='amount']");
	private By depositButton = By.xpath("//button[text()='Deposit']");
	private String[] name= {"---Your Name---","Hermoine Granger","Harry Potter","Ron Weasly","Albus Dumbledore","Neville Longbottom"};
	private By depositMessage=By.xpath("//span[@ng-show='message']");
	private By amountWithdrawl=By.xpath("//button[text()='Withdraw']");
	private By withdrawlMessage=By.xpath("//span[@ng-show='message']");
	private By balAmount=By.xpath("//div[1]/div/div[2]/div/div[2]/strong[2]");
	
	
	
	public By getBalAmount()
	{
		return balAmount;
	}
	
	
	public By getWithdrawMessage()
	{
		return withdrawlMessage;
	}
	
	public By getamountWithdraw()
	{
		return amountWithdrawl;
	}
	
	
	public By getDepositMessage()
	{
		return depositMessage;
	}
	
	
	public By getAmount()
	{
		return amount;
	}
	
	public By getDepositButton()
	{
		return depositButton;
	}
	
	
	
	
	
	public By getTransTable()
	{
		return transTable;
	}
	
	public String[] getname()
	{
		return name;
	}
	
	public By getReset()
	{
		return reset;
	}
	
	public By getBack()
	{
		return back;
	}
	public By getnameDrp()
	{
		return nameDrp;
	}

	public By getLogin() {
		return login;
	}

	
	public By getHeader() {
		return header;
	}


	public By getAccNumDrp() {
		return accNumDrp;
	}

	public By getAccNum() {
		return accNum;
	}

	public By getBal() {
		return bal;
	}

	public By getCurrency() {
		return currency;
	}

	public By getTrans() {
		return trans;
	}

	public By getDeposit() {
		return deposit;
	}

	public By getWithdraw() {
		return withdraw;
	}

	public By getHome() {
		return home;
	}

	public By getLogout() {
		return logout;
	}
	
	public boolean withDraw(int amt)
	{
		if (amt>1000)
		{
			return true;
		}
		else
			return false;
	}

}
