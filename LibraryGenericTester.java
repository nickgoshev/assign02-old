package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryGenericTester {
	
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	
	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");		
	}
	
	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	
	@Test
	public void testHolderString() {
		LibraryBookGeneric<String> book = new LibraryBookGeneric<String>(12, "Author Name", "Title");
		
		book.checkOut("james", new GregorianCalendar(06,06,06));
		assertTrue(book.getHolder() instanceof String);
	}
	
	@Test
	public void testHolderPhoneNumber() {
		LibraryBookGeneric<PhoneNumber> book = new LibraryBookGeneric<PhoneNumber>(12, "Author Name", "Title");
		
		book.checkOut(new PhoneNumber("8017388377"), new GregorianCalendar(06,06,06));
		assertTrue(book.getHolder() instanceof PhoneNumber);
	}
	
	@Test
	public void testIsbnSort()
	{
		ArrayList<LibraryBookGeneric<String>> result = nameLib.getInventoryList();
		ArrayList<LibraryBookGeneric<String>> expected = new ArrayList<LibraryBookGeneric<String>>();
		
		expected.add(new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild"));
		expected.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		expected.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testDueDateSort()
	{	
		ArrayList<LibraryBookGeneric<String>> libraryList = nameLib.getInventoryList();
		
		libraryList.get(0).checkOut("Steve", new GregorianCalendar(2022, 8, 6));
		libraryList.get(1).checkOut("Frank", new GregorianCalendar(2022, 8, 8));
		libraryList.get(2).checkOut("Alexa", new GregorianCalendar(2022, 8, 7));
		
		ArrayList<LibraryBookGeneric<String>> result = nameLib.getOverdueList(8, 6, 2022);
		
		ArrayList<LibraryBookGeneric<String>> expected = new ArrayList<LibraryBookGeneric<String>>();
		
		expected.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
		expected.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		
		
		assertEquals(expected, result);
	}
}
