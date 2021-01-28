package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	public void testNumber() {
		BankEntity aBank = new BankEntity("Bank2");
		
	}

	@Test
	public void testNewLine() {
		BankEntity aBank = new BankEntity("Drop Database Bank");
		
	}
	
	@Test
	public void testHashCode() {
		String string1 = "My Bank";
		String string2 = "Their Bank";
		BankEntity bank1 = new BankEntity("My Bank");
		BankEntity bank2 = new BankEntity("Their Bank");
		
		assertEquals(string1.hashCode(), bank1.hashCode()); 
		assertEquals(string2.hashCode(), bank2.hashCode());
		assertFalse(bank1.hashCode() == bank2.hashCode());
		
	}
}
