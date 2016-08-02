import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ValidInputTester {

	// Data field for total score
	private int totalPoints = 0;

	// Data fields to test Author class
	static Class<?> c = null;
	@SuppressWarnings("rawtypes")
	Constructor authorConstructor = null;
	Author author = null;
	Method authorGetName = null;
	Method authorGetEmail = null;
	Method authorGetGender = null;
	Method authorToString = null;

	// Data fields to test the Book class
	static Class<?> b = null;
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

	public static void main(String[] args)
	{
		ValidInputTester v = new ValidInputTester();
		try
		{
			c = Class.forName("Author");
		}
		catch(Exception e)
		{
			System.out.println("Author class not found.");
		}  

		if(c != null) 
			v.testAuthorMethods();
		else 
			System.out.println("Author class tests FAILED.");

		try
		{
			b = Class.forName("Book");
		}
		catch(Exception e)
		{
			System.out.println("Book class not found.");
		}  

		if(b !=null)
			v.testBookMethods();
		else
			System.out.println("Book class tests FAILED.");

		System.out.println("\n\nTOTAL SCORE: " + v.totalPoints + "\n\n");
		
		InvalidInputTester inv = new InvalidInputTester();
		inv.testInvalidInputs();
	}

	public void testAuthorMethods() {
		this.testAuthorConstructor();
		this.testAuthorGetName();
		this.testAuthorGetEmail();
		this.testAuthorGetGender();
		this.testAuthorToString();
	}

	public void testBookMethods() {
		this.testBookConstructor();
		this.testBookGetName();
		this.testBookGetISBN();
		this.testBookGetPrice();
		this.testBookSetPrice();
		this.testBookGetAuthorNames();
		this.testBookGetAuthors();
		this.testBookToString();
	}

	public void testAuthorConstructor() {
		try 
		{
			System.out.println("Test 1: Checking Author constructor...");
			//authorConstructor = Author.class.getDeclaredConstructor(String.class, String.class, char.class);
			authorConstructor = c.getDeclaredConstructor(String.class, String.class, char.class);
		}
		catch(Exception e)
		{
			System.out.println("Could not find Author(String name, String email, char gender)");
			return;
		}	
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
				System.out.println("Author instance was not created.");
			else
			{
				System.out.println("PASSED.");
				totalPoints = totalPoints + 1;
			}
		}
		else
			System.out.println("Could not test Author constructor.");

	}

	public void testAuthorGetName() {
		System.out.println("Test 2: Checking Author getName()...");
		try
		{
			authorGetName = c.getMethod("getName");
		}
		catch(Exception e)
		{
			System.out.println("Could not find Author.getName()");
			return;
		}

		if(authorGetName != null && author != null)
		{
			Object retval = null;
			try {
				retval = authorGetName.invoke(author, (Object[])null);
			} catch(Exception e) {
				e.printStackTrace();
			}
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
				}else
					System.out.println("FAILED.\nActual return type does not match expected return type.");
			}
		}
		else
			System.out.println("Could not test Author getName().");
	}

	public void testAuthorGetEmail() {
		System.out.println("Test 3: Checking Author getEmail()...");
		try
		{
			authorGetEmail = c.getMethod("getEmail");
		}
		catch(Exception e)
		{
			System.out.println("Could not find Author.getEmail()");
			return;
		}

		if(authorGetEmail != null && author != null)
		{
			Object retval = null;
			try {
				retval = authorGetEmail.invoke(author, (Object[])null);
			} catch(Exception e) {
				e.printStackTrace();
			}
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
			System.out.println("Could not test Author getEmail().");
		}
	}

	public void testAuthorGetGender() {
		System.out.println("Test 4: Checking Author getGender()...");
		try { 
			authorGetGender = c.getMethod("getGender");
		}catch(Exception e){
			System.out.println("Could not find Author.getGender()");
			return;
		}
		if(authorGetGender != null && author != null)
		{
			Object retval = null;
			try {
				retval = authorGetGender.invoke(author, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Author getGender().");
		}
	}

	public void testAuthorToString() {
		System.out.println("Test 5: Checking Author toString()...");
		//authorToString = Author.class.getMethod("toString");
		try {
			authorToString = c.getMethod("toString");
		}catch(Exception e){
			System.out.println("Could not find Author.toString()");
			return;
		}
		if(authorToString != null && author != null)
		{
			Object retval = null;
			try {
				retval = authorToString.invoke(author, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Author toString().");
		}
	}

	public void testBookConstructor() {
		System.out.println("Test 6: Checking Book constructor...");
		try 
		{
			bookConstructor = b.getDeclaredConstructor(String.class, Author[].class, long.class, double.class);
		}catch(Exception e){
			System.out.println("Could not find Book(String name, Author[] authors, long ISBN, double price)");
			return;
		}

		if(bookConstructor != null && authorConstructor != null){
			bookConstructor.setAccessible(true);
			try
			{
				Author[] authors = new Author[2];
				authors[0] = (Author)authorConstructor.newInstance("Stuart Russell", "sr@email.com", 'M');
				authors[1] = (Author)authorConstructor.newInstance("Peter Norvig", "norvig@email.com", 'M');
				book = (Book1)bookConstructor.newInstance("Artificial Intelligence: A Modern Approach", authors, 1919191919191L, 29.99);
			}catch(Exception e)
			{
				System.out.println("Could not create new Book instance.");
			}
			if(book == null)
				System.out.println("Book instance was not created.");
			else
			{
				System.out.println("PASSED.");
				totalPoints = totalPoints + 1;
			}
		}
		else
		{
			System.out.println("Could not test Book constructor.");
		}	
	}

	public void testBookGetName() {
		System.out.println("Test 7: Checking Book getName()...");
		try
		{
			bookGetName = b.getMethod("getName");
		}catch(Exception e){
			System.out.println("Could not find Book.getName()");
			return;
		}
		if(bookGetName != null && book != null)
		{
			Object retval = null;
			try{
				retval = bookGetName.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Book getName().");
		}
	}

	public void testBookGetISBN() {
		System.out.println("Test 8: Checking Book getISBN()...");
		try{
			bookGetISBN = b.getMethod("getISBN");
		}catch(Exception e){
			System.out.println("Could not find Book.getISBN()");
			return;
		}
		if(bookGetISBN != null && book != null)
		{
			Object retval = null;
			try{
				retval = bookGetISBN.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Book getISBN().");
		}
	}

	public void testBookGetPrice() {
		System.out.println("Test 9: Checking Book getPrice()...");
		try{
			bookGetPrice = b.getMethod("getPrice");
		}catch(Exception e){
			System.out.println("Could not find Book.getPrice()");
			return;
		}
		if(bookGetPrice != null && book != null)
		{
			Object retval = null;
			try{
				retval = bookGetPrice.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Book getPrice().");
		}
	}

	public void testBookSetPrice() {
		System.out.println("Test 10: Checking Book setPrice()...");
		try{
			bookSetPrice = b.getMethod("setPrice", double.class);
		}catch(Exception e){
			System.out.println("Could not find Book.getPrice()");
			return;
		}
		if(bookGetPrice != null && book != null)
		{
			Object retval = null;
			try{
				bookSetPrice.invoke(book, 299.99);
				retval = bookGetPrice.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Book setPrice().");
		}
	}

	public void testBookGetAuthorNames() {
		System.out.println("Test 11: Checking Book getAuthorNames()...");
		try{
			bookGetAuthorNames = b.getMethod("getAuthorNames");
		}catch(Exception e){
			System.out.println("Could not find Book.getAuthorNames()");
			return;
		}
		if(bookGetAuthorNames != null && book != null)
		{
			Object retval = null;
			try{
				retval = bookGetAuthorNames.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not find test getAuthorNames().");
		}
	}

	public void testBookGetAuthors() {
		System.out.println("Test 12: Checking Book getAuthors()...");
		try{
			bookGetAuthors = b.getMethod("getAuthors");
		}catch(Exception e){
			System.out.println("Could not find Book.getAuthors()");
			return;
		}
		if(bookGetAuthors != null && book != null)
		{
			Object retval = null;
			try{
				retval =bookGetAuthorNames.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
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
			System.out.println("Could not test Book getAuthorNames().");
		}

	}

	public void testBookToString() {
		System.out.println("Test 13: Checking Book toString()...");
		try{
			bookToString = b.getMethod("toString");
		}catch(Exception e){
			System.out.println("Could not find Book.toString()");
			return;
		}
		if(bookToString != null && book != null)
		{
			Object retval = null;
			try{
				retval = bookToString.invoke(book, (Object[])null);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(retval == null)
			{
				System.out.println("No value returned for Book.toString().");
			}
			else
			{
				if(retval instanceof String)
				{
					String value = (String)retval;
					String expected1 = "Book[name = Artificial Intelligence: A Modern Approach, authors = {Author[name = Stuart Russell, email = sr@email.com, gender = M]";
					expected1 += ", Author[name = Peter Norvig, email = norvig@email.com, gender = M]}, isbn = 1919191919191, price = 299.99]";

					String expected2 = "Book[name = Artificial Intelligence: A Modern Approach, authors = {Author[name = Stuart Russell, email = sr@email.com, gender = M]";
					expected2 += ", Author[name = Peter Norvig, email = norvig@email.com, gender = M]}, isbn = 1919191919191, price = 299.99]";
					if(value.toLowerCase().contains(expected1.toLowerCase()) || value.toLowerCase().contains(expected2.toLowerCase()))
					{
						System.out.println("PASSED.");
						totalPoints = totalPoints + 2;
					}
					else
					{
						System.out.println("FAILED.");
						System.out.println("Expected return value: " + expected1);
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
			System.out.println("Could not test Book toString().");
		}

	}	
}
