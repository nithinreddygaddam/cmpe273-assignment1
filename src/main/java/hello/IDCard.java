package hello;

import org.hibernate.validator.constraints.NotBlank;

public class IDCard
{

	private String card_id;
	private String expiration_date;
	private static int idCard_counter = 0;
	
	@NotBlank(message = "No Card number")
	private String card_number;
	
	@NotBlank(message = "No Card name")
	private String card_name;
		
	public static int getId() 
	{
		return idCard_counter;
	}

	public static void setId(int idCard_counter) 
	{
		IDCard.idCard_counter = idCard_counter;
	}

	public IDCard(){}
	
	public IDCard(String card_name, String card_number,String expiration_date) 
	{
		super();
		idCard_counter++;
		this.card_number = card_number;
		this.card_name = card_name;
		this.expiration_date = expiration_date;
	}
	
	public String getCard_id() 
	{
		return card_id;
	}
	
	public void setCard_id(String card_id) 
	{
		this.card_id = card_id;
	}
	
	public String getCard_number() 
	{
		return card_number;
	}
	
	public void setCard_number(String card_number) 
	{
		this.card_number = card_number;
	}
	
	public String getCard_name() 
	{
		return card_name;
	}
	
	public void setCard_name(String card_name) 
	{
		this.card_name = card_name;
	}
	
	public String getExpiration_date() 
	{
		return expiration_date;
	}
	
	public void setExpiration_date(String expiration_date) 
	{
		this.expiration_date = expiration_date;
	}
	
	
}
