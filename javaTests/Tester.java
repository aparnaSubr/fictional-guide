import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Tester
{
	private static int totalPoints = 0;

	public static void main(String[] args)
	{
		if (args.length != 0) 
		{
			System.out.println("USAGE: java Tester");
			System.exit(0);
		}

		Class<?> c = null;
		try
		{
			c = Class.forName("Author");
		}
		catch(Exception e)
		{
			System.out.println("Author class not found.");
		}  

		@SuppressWarnings("rawtypes")
		Constructor authorConstructor = null;

		if(c != null)
		{
			Author author = null;
			Method authorGetName = null;
			Method authorGetEmail = null;
			Method authorGetGender = null;
			Method authorToString = null;
			// Test 1: Author constructor.
			try 
			{
				// Get the declared Author constructor.

				//authorConstructor = Author.class.getDeclaredConstructor(String.class, String.class, char.class);
				authorConstructor = c.getDeclaredConstructor(String.class, String.class, char.class);

				// Check if instance of Author class can be made.
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Test 1: Checking Author constructor...");
				if(authorConstructor != null)
				{
					authorConstructor.setAccessible(true);
					try
					{
						author = (Author)authorConstructor.newInstance("Stuart Russell", "sr@email.com", 'M');
					}
					catch(Exception e)
					{
						System.out.println("Could not create new Author instance.");
					}
					if(author == null)
					{
						System.out.println("Author instance was not created.");
					}
					else
					{
						System.out.println("PASSED.");
						totalPoints = totalPoints + 2;
					}
				}
				else
				{
					System.out.println("Could not find Author constructor.");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			// Test 2: Other methods in Author class
			try
			{
				// Checking Author getName(), getEmail(), getGender().
				/*authorGetName = Author.class.getMethod("getName");
				authorGetEmail = Author.class.getMethod("getEmail");
				authorGetGender = Author.class.getMethod("getGender");*/

				authorGetName = c.getMethod("getName");
				authorGetEmail = c.getMethod("getEmail");
				authorGetGender = c.getMethod("getGender");

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Test 2: Checking Author getName()...");
				if(authorGetName != null)
				{
					Object retval = authorGetName.invoke(author, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Author.getName()");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("Stuart Russell".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: Stuart Russell");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Author getName().");
				}

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Test 3: Checking Author getEmail()...");
				if(authorGetEmail != null)
				{
					Object retval = authorGetEmail.invoke(author, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Author.getEmail()");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("sr@email.com".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: sr@email.com");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Author getEmail().");
				}

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Test 4: Checking Author getGender()...");
				if(authorGetEmail != null)
				{
					Object retval = authorGetGender.invoke(author, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Author.getGender()");
					}
					else
					{
						if(retval instanceof Character)
						{
							Character value = (Character)retval;
							if(value.equals('M') || value.equals('m'))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: M");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Author getEmail().");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			// Test 5: Author.toString()
			try
			{
				System.out.println("Test 5: Checking Author toString()...");

				//authorToString = Author.class.getMethod("toString");
				authorToString = c.getMethod("toString");
				if(authorToString != null)
				{
					Object retval = authorToString.invoke(author, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Author.toString()");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("Author[name = Stuart Russell, email = sr@email.com, gender = M]".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 2;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: Author[name = Stuart Russell, email = sr@email.com, gender = M]");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Author toString().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
		else
		{
			System.out.println("Author class tests FAILED.");
		}


		// TESTING THE BOOK CLASS NOW!!
		Class<?> b = null;
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
			@SuppressWarnings("rawtypes")
			Constructor bookConstructor = null;
			Book1 book = null;
			Method bookGetName = null;
			Method bookGetAuthors = null;
			Method bookGetISBN = null;
			Method bookGetPrice = null;
			Method bookSetPrice = null;
			Method bookGetAuthorNames = null;
			Method bookToString = null;
			// Test 1: Author constructor.
			try 
			{
				// Get the declared Author constructor.

				//authorConstructor = Author.class.getDeclaredConstructor(String.class, String.class, char.class);
				bookConstructor = b.getDeclaredConstructor(String.class, Author[].class, long.class, double.class);

				// Check if instance of Author class can be made.
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Test 6: Checking Book constructor...");
				if(bookConstructor != null)
				{
					bookConstructor.setAccessible(true);
					try
					{
						Author[] authors = new Author[2];
						authors[0] = (Author)authorConstructor.newInstance("Stuart Russell", "sr@email.com", 'M');
						authors[1] = (Author)authorConstructor.newInstance("Peter Norvig", "norvig@email.com", 'M');
						book = (Book1)bookConstructor.newInstance("Artificial Intelligence: A Modern Approach", authors, 1919191919191L, 29.99);
					}
					catch(Exception e)
					{
						System.out.println("Could not create new Book instance.");
					}
					if(book == null)
					{
						System.out.println("Book instance was not created.");
					}
					else
					{
						System.out.println("PASSED.");
						totalPoints = totalPoints + 3;
					}
				}
				else
				{
					System.out.println("Could not find Book constructor.");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			// OTHER METHODS IN BOOK CLASS
			try
			{
				bookGetName = b.getMethod("getName");
				bookGetAuthors = b.getMethod("getAuthors");
				bookGetISBN = b.getMethod("getISBN");
				bookGetPrice = b.getMethod("getPrice");
				bookSetPrice = b.getMethod("setPrice", double.class);
				bookGetAuthorNames = b.getMethod("getAuthorNames");
				bookToString = b.getMethod("toString");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			try
			{
				System.out.println("Test 7: Checking Book getName()...");

				if(bookGetName != null)
				{
					Object retval = bookGetName.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getName()");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("Artificial Intelligence: A Modern Approach".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: Artificial Intelligence: A Modern Approach");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book getName().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				System.out.println("Test 8: Checking Book getISBN()...");

				if(bookGetISBN != null)
				{
					Object retval = bookGetISBN.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getISBN()");
					}
					else
					{
						if(retval instanceof Long)
						{
							Long value = (Long)retval;
							if(value.longValue() == 1919191919191L)
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: 1919191919191");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book getISBN().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				System.out.println("Test 9: Checking Book getPrice()...");

				if(bookGetPrice != null)
				{
					Object retval = bookGetPrice.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getPrice()");
					}
					else
					{
						if(retval instanceof Double)
						{
							Double value = (Double)retval;
							if(value.doubleValue() == 29.99)
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: 29.99");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book getPrice().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				System.out.println("Test 10: Checking Book setPrice()...");

				if(bookGetPrice != null)
				{
					bookSetPrice.invoke(book, 299.99);
					Object retval = bookGetPrice.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getPrice() after setting the price.");
					}
					else
					{
						if(retval instanceof Double)
						{
							Double value = (Double)retval;
							if(value.doubleValue() == 299.99)
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: 299.99");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book setPrice().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				System.out.println("Test 11: Checking Book getAuthorNames()...");

				if(bookGetAuthorNames != null)
				{
					Object retval = bookGetAuthorNames.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getAuthorNames().");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("Stuart Russell".toLowerCase()) && value.toLowerCase().contains("Peter Norvig".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: Stuart Russell, Peter Norvig");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book getAuthorNames().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			try
			{
				System.out.println("Test 12: Checking Book getAuthors()...");

				if(bookGetAuthors != null)
				{
					Object retval = bookGetAuthorNames.invoke(book, (Object[])null);
					if(retval == null)
					{
						System.out.println("No value returned for Book.getAuthorNames().");
					}
					else
					{
						if(retval instanceof String)
						{
							String value = (String)retval;
							if(value.toLowerCase().contains("Stuart Russell".toLowerCase()) && value.toLowerCase().contains("Peter Norvig".toLowerCase()))
							{
								System.out.println("PASSED.");
								totalPoints = totalPoints + 1;
							}
							else
							{
								System.out.println("FAILED.");
								System.out.println("Expected return value: Stuart Russell, Peter Norvig");
								System.out.println("Actual return value: " + value);
							}
						}
						else
						{
							System.out.println("FAILED.");
							System.out.println("Actual return type does not match expected return type.");
						}
					}
				}
				else
				{
					System.out.println("Could not find Book getAuthorNames().");
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Book class tests FAILED.");
		}
	}
}
