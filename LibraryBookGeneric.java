package assign02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book
{
	private Type holder;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title)
	{
		super(isbn, author, title);
		holder = null;
		dueDate = null;
	}
	
	public Type getHolder()
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
	
	public void checkOut(Type name, GregorianCalendar date)
	{
		holder = name;
		dueDate = date;
	}
	
	
}
