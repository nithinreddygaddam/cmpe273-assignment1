package hello;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExceptionHandlers
{
	private List<String> errors;
	 
    public ExceptionHandlers() {}
 
    public ExceptionHandlers(List<String> errors) 
    {
        this.errors = errors;
    }
 
    public ExceptionHandlers(String error) 
    {
        this(Collections.singletonList(error));
    }
 
    public ExceptionHandlers(String ... errors) 
    {
        this(Arrays.asList(errors));
    }
 
    public List<String> getErrors() 
    {
        return errors;
    }
 
    public void setErrors(List<String> errors) 
    {
        this.errors = errors;
    }
}