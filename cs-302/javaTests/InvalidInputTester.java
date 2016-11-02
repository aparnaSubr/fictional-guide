import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InvalidInputTester extends SecurityManager
{
	Class<?> c = null;
	Author author = null;
	@SuppressWarnings("rawtypes")
    Constructor authorConstructor = null;
	
	Class<?> b = null;
	Book1 book = null;
	@SuppressWarnings("rawtypes")
    Constructor bookConstructor = null;
	
    public void testInvalidInputs() 
    {
        try
        {
            c = Class.forName("Author");
        }
        catch(Exception e)
        {
            System.out.println("Author class not found.");
        }
        if(c != null)
        {
            try
            {
                authorConstructor = c.getDeclaredConstructor(String.class, String.class, char.class);
            }
            catch(Exception e)
            {
                System.out.println("Could not find Author constructor.");
            }
            System.out.println("Test 14: Checking Author constructor...");
            if(authorConstructor != null)
            {
                MySecurityManager secManager = new MySecurityManager();
                System.setSecurityManager(secManager);
                try 
                {
                    //authorConstructor.setAccessible(true);
                    author = (Author)authorConstructor.newInstance("hello", "@hello", 'M');
                }
                catch(InstantiationException e)
                {
                    System.out.println("Email validated.");
                }
                catch(SecurityException e)
                {
                    System.out.println("Email validated SecurityException.");
                }
                catch(IllegalAccessException e)
                {
                    System.out.println("Email validated.");
                }
                catch(InvocationTargetException e)
                {
                    System.out.println("Email validated.");
                }
                if(author != null)
                {
                	System.out.println("FAILED validating invalid email");
                	author = null;
                }
            }
        }

        try
        {
            b = Class.forName("Book");
        }
        catch(Exception e)
        {
            System.out.println("Book class not found.");
        }

        if(b != null)
        {
            try
            {
                bookConstructor = b.getDeclaredConstructor(String.class, Author[].class, long.class, double.class);
            }
            catch(Exception e)
            {
                System.out.println("Could not find Book constructor.");
            }
            System.out.println("Test 15: Checking Book constructor - invalid price...");
            if(bookConstructor != null)
            {
                MySecurityManager secManager = new MySecurityManager();
                System.setSecurityManager(secManager);

                try 
                {
                    //bookConstructor.setAccessible(true);
                    book = (Book1)bookConstructor.newInstance("hello", null, 1919191919191L, 0);
                }
                catch(InstantiationException e)
                {
                    System.out.println("Price validated.");
                }
                catch(SecurityException e)
                {
                    System.out.println("Price validated SE.");
                }
                catch(IllegalAccessException e)
                {
                    System.out.println("Price validated.");
                }
                catch(InvocationTargetException e)
                {
                    System.out.println("Price validated.");
                }
                if(book != null)
                {
                	System.out.println("FAILED validating invalid price");
                	book = null;
                }

                System.out.println("Test 16: Checking Book constructor - invalid ISBN...");
                try 
                {
                    //bookConstructor.setAccessible(true);
                    book = (Book1)bookConstructor.newInstance("hello", null, 191919L, 99.99);
                }
                catch(InstantiationException e)
                {
                    System.out.println("ISBN validated.");
                }
                catch(SecurityException e)
                {
                    System.out.println("ISBN validated SE.");
                }
                catch(IllegalAccessException e)
                {
                    System.out.println("ISBN validated.");
                }
                catch(InvocationTargetException e)
                {
                    System.out.println("ISBN validated.");
                }
                if(book != null)
                {
                	System.out.println("FAILED validating invalid ISBN");
                	book = null;
                }
            }
        }
        
    }
}



