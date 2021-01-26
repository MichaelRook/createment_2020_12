package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

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
}
