package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BankEnityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		BankEntity aBank = new BankEntity("snsBank");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNull() {
		BankEntity aBank = new BankEntity(null);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmpty() {
		BankEntity aBank = new BankEntity("");
		
	}
	
	@Test
	public void testCorrect() {
		BankEntity aBank = new BankEntity("SNSBank");
		
	}
	
	@Test//(expected = IllegalArgumentException.class)
	public void testTab() {
		BankEntity aBank = new BankEntity("Bank\t");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNameWithNumber() {
		BankEntity aBank = new BankEntity("Bank2");
		
	}

	@Test
	public void testNewLine() {
		BankEntity aBank = new BankEntity("Drop Database Bank");
		
	}
	
	@Test
	public void testHashCodeFalse() {

		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("Their Bank");
		
		assertFalse(bank1.hashCode() == bank2.hashCode());
		
		
	}
	
	@Test
	public void testHashCodeTrue() {
		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("My Bank");
		assertTrue(bank1.hashCode() == bank2.hashCode());
	}
	
	@Test
	public void testEqualsTrue() {
		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("My Bank");
		bank1.openRekeningCourant("piet", 10.0);
		bank2.openRekeningCourant("piet", 10.0);
		assertTrue(bank1.equals(bank2));
		}
	
	@Test
	public void testEqualsFalse() {
		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("My Bank");
		BankEntity bank3 = new BankEntity("My Bank");

		bank1.openRekeningCourant("piet", 10.0);
		bank2.openSpaarRekening("piet", 10.0);
		assertFalse(bank1.equals(bank2));
		
	}
	
	@Test
	public void testEqualsNull() {
		BankEntity bank1 = new BankEntity("My Bank");
		assertFalse(bank1.equals(null));
	}
	
	@Test
	public void testEqualsObject() {
		BankEntity bank1 = new BankEntity("My Bank");
		Object object1 = new Object();
		assertFalse(bank1.equals(object1));

	}
	
	@Test
	public void testCompareToTrue() {
		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("Their Bank");
		int actual = bank1.compareTo(bank2);
		int expected = -1;
		assertEquals(expected, actual);		

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToNull() {
		BankEntity bank1 = new BankEntity("My Bank");
		bank1.compareTo(null);

	}
	
	@Test 
	public void testGetNummer() {
		BankEntity bank1 = new BankEntity("My Bank");
		bank1.openRekeningCourant("piet", 10.0);
		assertEquals(bank1.getRekeningNr("piet"), 1001);

	}
	
	@Test 
	public void testOverschrijvingTrue() throws Exception {
		BankEntity bank1 = new BankEntity("My Bank");
		bank1.openRekeningCourant("piet", 10.0);
		bank1.openRekeningCourant("henk", 10.0);
		bank1.overschrijving(1001, 1002, 5.0);
		assertTrue(bank1.getSaldo(1002) == 15.0);
	}
	
	@Test 
	public void testOpheffenRekening() {
		BankEntity bank1 = new BankEntity("My Bank");
		bank1.openRekeningCourant("piet", 10.0);
		assertEquals(1001, bank1.getRekeningNr("piet"));
		bank1.opheffenRekening(1001);
		assertEquals(-1, bank1.getRekeningNr("piet"));
	}



}
