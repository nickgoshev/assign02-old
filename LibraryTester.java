package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();
		
		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	
	//Medium Library Tests
	
	@Test
	public void testMediumLibraryLookupISBN() {
		assertNull(mediumLib.lookup(9781843190363L));
	}
	
	@Test
	public void testMediumLibraryLookupHolder() {
		mediumLib.checkout(9781843190073L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843190073L, "Jen Alexander", "The Coming of the Third").getIsbn(), booksCheckedOut.get(0).getIsbn());
		assertEquals(new Book(9781843190073L, "Jen Alexander", "The Coming of the Third").getAuthor(), booksCheckedOut.get(0).getAuthor());
		assertEquals(new Book(9781843190073L, "Jen Alexander", "The Coming of the Third").getTitle(), booksCheckedOut.get(0).getTitle());
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testMediumLibraryCheckout() {
		
		assertTrue(mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testMediumLibraryCheckinISBN() {
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);
		assertTrue(mediumLib.checkin(9781843190363L));
	}

	@Test
	public void testMediumLibraryCheckinHolder() {
		assertFalse(mediumLib.checkin("Jane Doe"));
	}
	
	//Book Tests
	
	@Test
	public void testNotBookEquals()
	{
		Object o = new Object();
		Book book = new Book(12, "Hello", "String");
		
		assertFalse(book.equals(o));
	}
	
	@Test
	public void testBookIsbnUnequal()
	{
		Book book1 = new Book(12, "Hello", "String");
		Book book2 = new Book(11, "Hello", "String");
		
		assertFalse(book1.equals(book2));
	}
	
	@Test
	public void testBookAuthorUnequal()
	{
		Book book1 = new Book(12, "Hello", "String");
		Book book2 = new Book(12, "Goodbye", "String");
		
		assertFalse(book1.equals(book2));
	}
	
	@Test
	public void testBookTitleUnequal()
	{
		Book book1 = new Book(12, "Hello", "String");
		Book book2 = new Book(12, "Hello", "S");
		
		assertFalse(book1.equals(book2));
	}
	
	@Test
	public void testBookEqual()
	{
		Book book1 = new Book(12, "Hello", "String");
		Book book2 = new Book(12, "Hello", "String");
		
		assertTrue(book1.equals(book2));
	}
	
	@Test
	public void testLibraryBookCheckIn()
	{
		LibraryBook book = new LibraryBook(12, "Author Name", "Title");
		
		book.checkOut("Holder Name", new GregorianCalendar(2022, 9, 7));
		
		assertEquals("Holder Name", book.getHolder());
		assertEquals(new GregorianCalendar(2022, 9, 7), book.getDueDate());
	}
	
	@Test
	public void testLibraryBookCheckOut()
	{
		LibraryBook book = new LibraryBook(12, "Author Name", "Title");
		
		book.checkOut("Holder Name", new GregorianCalendar(2022, 9, 7));
		book.checkIn();
		
		assertNull(book.getHolder());
		assertNull(book.getDueDate());
	}
}