package hello;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller 
{
	
	List<User> userList = new ArrayList<User>();
	
	Map<String,List<IDCard>> IDCard_map = new HashMap<String, List<IDCard>>();
	Map<String,List<Account>> account_map = new HashMap<String,List<Account>>();
	Map<String,List<Web>> web_map = new HashMap<String,List<Web>>();
	Date date = new Date();
	
	public List<User> getUserList() 
	{
		return userList;
	}

	public void setUserList(List<User> userList) 
	{
		this.userList = userList;
	}
	
//POST User
	@RequestMapping(value = "api/v1/users", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) 
	{
	
		User new_User = new User(user.getEmail(),user.getPassword(),date.toString());
    	
    		new_User.setUserID("u-" + String.valueOf(User.getId()) );
    		userList.add(new_User);
    		return new_User;
    }
	
//GET User
	@RequestMapping(value = "api/v1/users/{user_id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
    public User viewUser(@PathVariable(value = "user_id") String user_id) 
	{
		ListIterator<User> node = userList.listIterator();
		User user = null;
		while(node.hasNext())
		{
			user = (User) node.next();
			
			if(user.getUserID().equals(user_id))
			{
				break;
			}
			else
				user = null;
			
		}
		return user;
	 }
		
//PUT User
	@RequestMapping(value = "api/v1/users/{user_id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public User updateUser(@Valid @RequestBody User user,@PathVariable(value = "user_id") String user_id) 
	{
		ListIterator<User> iter = userList.listIterator();
		User current_user = null;
		while(iter.hasNext())
		{
			current_user = (User) iter.next();
			if(current_user.getUserID().equals(user_id))
			{
				current_user.setPassword(user.getPassword());
				current_user.setEmail(user.getEmail());
				current_user.setUpdatedAt(date.toString());
				break;
			}
			else
				current_user = null;
		}
		return current_user;
    }

// POST ID card	
	@RequestMapping(value = "api/v1/users/{user_id}/idcards", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public IDCard createIDCard(@Valid @RequestBody IDCard id,@PathVariable(value = "user_id") String user_id) 
	{
		List<IDCard> idList = new ArrayList<IDCard>();
		IDCard newID = new IDCard(id.getCard_name(),id.getCard_number(),id.getExpiration_date());
		newID.setCard_id("c-" + String.valueOf(IDCard.getId()));
		if(IDCard_map.containsKey(user_id))
		{
			idList = IDCard_map.get(user_id);
			idList.add(newID);
			IDCard_map.put(user_id,idList);
		}
		else
		{
			idList.add(newID);
			IDCard_map.put(user_id,idList);
		}
		return newID;
		
	}
	
//GET ID card	
	@RequestMapping(value = "api/v1/users/{user_id}/idcards", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<IDCard> viewIDCards(@PathVariable(value = "user_id") String user_id) 
	{
		return IDCard_map.get(user_id);
	}
	
//DELETE ID Card	
	@RequestMapping(value = "api/v1/users/{user_id}/idcards/{card_id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteIDCard(@PathVariable(value = "user_id") String user_id,@PathVariable(value = "card_id") String card_id) 
	{
		List<IDCard> idList = IDCard_map.get(user_id);
		ListIterator<IDCard> iter = idList.listIterator();
		
		while(iter.hasNext())
		{
			IDCard removeID = iter.next();
			if(removeID.getCard_id().equals(card_id))
			{
				iter.remove();
			}
		}
	}
	
//POST Web Login
	@RequestMapping(value = "api/v1/users/{user_id}/weblogins", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Web createWebID(@Valid @RequestBody Web id,@PathVariable(value = "user_id") String user_id) 
	{
		List<Web> WebList = new ArrayList<Web>();
		Web newWeb = new Web(id.getUrl(),id.getLogin(),id.getPassword());
		newWeb.setLogin_id("l-" + String.valueOf(Web.getId()));
		if(web_map.containsKey(user_id))
		{
			WebList = web_map.get(user_id);
			WebList.add(newWeb);
			web_map.put(user_id,WebList);
		}
		else
		{
			WebList.add(newWeb);
			web_map.put(user_id,WebList);
		}
		return newWeb;
	}
	
//GET Web Login	
	@RequestMapping(value = "api/v1/users/{user_id}/weblogins", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<Web> viewWebs(@PathVariable(value = "user_id") String user_id) 
	{
		return web_map.get(user_id);
	}
	
//DELETE Web Login	
	@RequestMapping(value = "api/v1/users/{user_id}/weblogins/{login_id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteWeb(@PathVariable(value = "user_id") String user_id,@PathVariable(value = "login_id") String login_id) 
	{
		List<Web> WebList = web_map.get(user_id);
		ListIterator<Web> iter = WebList.listIterator();
		
		while(iter.hasNext())
		{
			Web removeWeb = iter.next();
			if(removeWeb.getLogin_id().equals(login_id))
			{
				iter.remove();
			}
		}
	}
	
		
//POST Bank Account	
	@RequestMapping(value = "api/v1/users/{user_id}/bankaccounts", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody Account bank,@PathVariable(value = "user_id") String user_id) 
	{
		List<Account> AccountList = new ArrayList<Account>();
		Account newAccount = new Account(bank.getAccount_name(),bank.getAccount_number(),bank.getRouting_number());
		newAccount.setAccount_id("b-" + String.valueOf(Account.getId()));
		if(account_map.containsKey(user_id))
		{
			AccountList = account_map.get(user_id);
			AccountList.add(newAccount);
			account_map.put(user_id,AccountList);
		}
		else
		{
			AccountList.add(newAccount);
			account_map.put(user_id,AccountList);
		}
		return newAccount;
	}

//GET Bank Account
	@RequestMapping(value = "api/v1/users/{user_id}/bankaccounts", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<Account> viewAccounts(@PathVariable(value="user_id") String user_id) 
	{
		return account_map.get(user_id);
	} 

//DELETE Bank Account	
	@RequestMapping(value = "api/v1/users/{user_id}/bankaccounts/{ba_id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(value = "user_id") String user_id,@PathVariable(value = "ba_id") String ba_id) 
	{
		List<Account> AccountList=account_map.get(user_id);
		ListIterator<Account> iter = AccountList.listIterator();
		while(iter.hasNext())
		{
			Account removeAccount = iter.next();
			if(removeAccount.getAccount_id().equals(ba_id))
			{
				iter.remove();
			}
		}
	}
	
//Exception Handler
	@ExceptionHandler
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    ExceptionHandlers handleException(Exception ex) {
		System.out.println("in exception handler");
        List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size()+globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        return new ExceptionHandlers(errors);
    }
	}
