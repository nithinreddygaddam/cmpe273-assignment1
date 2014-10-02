package hello;

import org.hibernate.validator.constraints.NotBlank;

public class Account 
{
	
	private String account_id;
	private String account_name;
	private static int accountNumber_counter = 0;
	
	@NotBlank(message = "Account number is empty")
	private String account_number;
	
	@NotBlank(message = "Routing number is empty")
	private String routing_number;
	
	public static int getId() 
	{
		return accountNumber_counter;
	}

	public static void setId(int accountNumber_counter) 
	{
		Account.accountNumber_counter = accountNumber_counter;
	}

	private Account(){}
	
	public Account(String account_name,String account_number, String routing_number) 
	{
		super();
		accountNumber_counter++;
		
		this.account_name = account_name;
		this.account_number = account_number;
		this.routing_number = routing_number;
	}
		
	public String getAccount_id() 
	{
		return account_id;
	}
	
	public void setAccount_id(String account_id) 
	{
		this.account_id = account_id;
	}
	
	public String getAccount_name() 
	{
		return account_name;
	}
	
	public void setAccountName(String account_name) 
	{
		this.account_name = account_name;
	}
	
	public String getAccount_number() 
	{
		return account_number;
	}
	
	public void setAccount_number(String account_number) 
	{
		this.account_number = account_number;
	}
	
	public String getRouting_number() 
	{
		return routing_number;
	}
	
	public void setRouting_number(String routing_number) 
	{
		this.routing_number = routing_number;
	}
	
}
