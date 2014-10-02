package hello;

import org.hibernate.validator.constraints.NotBlank;

public class Web 
{
	
	private String login_id;
	private static int webID_counter = 0;
	
	@NotBlank(message ="Empty URL")
	private String url;
	
	@NotBlank(message ="Empty Login")
	private String login;
	
	@NotBlank(message ="Empty Password")
	private String password;

	
	public static int getId() 
	{
		return webID_counter;
	}

	public static void setId(int webID_counter) 
	{
		Web.webID_counter = webID_counter;
	}

	public Web(String url, String login, String password) 
	{
		super();
		webID_counter++;
		
		this.url = url;
		this.login = login;
		this.password = password;
	}
	
	public Web(){}
	
	public String getLogin_id() 
	{
		return login_id;
	}
	
	public void setLogin_id(String login_id) 
	{
		this.login_id = login_id;
	}
	
	public String getUrl() 
	{
		return url;
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}	

}
