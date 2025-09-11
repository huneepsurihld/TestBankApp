package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class BankManagerLogin extends  BaseClass{

	public BankManagerLogin(WebDriver driver) 
	{
		super(driver);
	}

	private By addCust=By.xpath("//button[@ng-click='addCust()']");
	private By openAcc = By.xpath("//button[@ng-click='openAccount()']");
	private By customers = By.xpath("//button[@ng-click='showCust()']");
	private By fName= By.xpath("//input[@ng-model=\"fName\"]");
	private By lName=By.xpath("//input[@ng-model=\"lName\"]");
	private By code=By.xpath("//input[@ng-model=\"postCd\"]");
	private By addCustButton=By.xpath("//button[@type=\"submit\"]");
	private By custDrp=By.id("userSelect");
	private By currencyDrp = By.id("currency");
	private By process = By.xpath("//button[@type='submit']");
	private By search=By.xpath("//input[@type='text']");
	private By tableHeader=By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/thead/tr/td");
	private By delete=By.xpath("//button[@ng-click='deleteCust(cust)']");
	private By tableRows = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr");
	
	public String getCell(int index)
	{
		String path="/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr["+index+"]/td[last()]/button";
		return path;
	}
	
	public By getTableRows()
	{
		return tableRows;
	}
	
	public By getDelete()
	{
		return delete;
	}
	
	
	public By getTable()
	{
		return tableHeader;
	}
	
	public By getSearch()
	{
		return search;
	}
	
	
	public By getCustDrp()
	{
		return custDrp;
	}
	
	public By getCurrencyDrp()
	{
		return currencyDrp;
	}
	
	public By getProcess()
	{
		return process;
	}
	
	
	
	public By getAddCustButton()
	{
		return addCustButton;
	}
	
	
	public By getfName()
	{
		return fName;
	}
	
	public By getlName()
	{
		return lName;
	}
	
	public By getCode()
	{
		return code;
	}
	
	
	public By getAddCust()
	{
		return addCust;
	}
	
	public By getOpenAcc()
	{
		return openAcc;
	}
	
	public By getCustomer()
	{
		return customers;
	}
	
	public String fname()
	{
		String  fname="First Name";
		return fname;
	}
	
	public String lname()
	{
	String lName="Last Name";
	return lName;
	}
		
	public String code()
		{
		String code="Post Code";
		return code;
		}
	
	public String getAlertMsg()
	{
		String msg="Customer added successfully with customer id :";
		return msg;
	}
	
	public String getOpenAccMsg()
	{
		String msg ="Account created successfully with account Number :";
		return msg;
	}
	
	public String[] getHeaderNames()
	{
		String headings[]= {"First Name", "Last Name", "Post Code", "Account Number", "Delete Customer"};
		return headings;
	}
	
	
	
	}
	
	

