package hello;

import org.hibernate.validator.constraints.NotBlank;

public class User 
{
	private String created_at;
	private String updated_at;
	private static int userID_counter = 0;
	private String user_id;
	
	@NotBlank(message="Email is empty")
	private String email;
	
	@NotBlank(message="Password is empty")
	private String password;
	
	public User(){}
	
	public User(String email, String password,String created_at) 
	{
		super();
		userID_counter++;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
	}
	
	public static int getId() 
	{
		return userID_counter;
	}

	public static void setId(int userID_counter) 
	{
		User.userID_counter = userID_counter;
	}

	public String getUserID() 
	{
		return user_id;
	}
	
	public void setUserID(String user_id) 
	{
		this.user_id = user_id;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getCreatedAt() 
	{
		return created_at;
	}
	
	public void setCreatedAt(String created_at) 
	{
		this.created_at = created_at;
	}
	
	public String getUpdatedAt() 
	{
		return updated_at;
	}
	
	public void setUpdatedAt(String updated_at) 
	{
		this.updated_at = updated_at;
	}
	
}
