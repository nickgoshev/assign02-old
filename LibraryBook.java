package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book
{
	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title)
	{
		super(isbn, author, title);
		holder = null;
		dueDate = null;
	}
	
	public String getHolder()
	{
		return holder;
	}
	
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	public void checkIn()
	{
		holder = null;
		dueDate = null;
	}
	
	public void checkOut(String name, GregorianCalendar date)
	{
		holder = name;
		dueDate = date;
	}
	
	
}
