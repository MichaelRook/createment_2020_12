package nl.prolector.cursus.java.io.streams.opdrachten.bank.vo;

import static org.junit.Assert.*;

import java.util.Optional;


import org.junit.Test;

public class BedragTest {

	private Bedrag Amount10= new Bedrag(10.0);
	private Bedrag Amount5= new Bedrag(5.0);
	private Bedrag Amount0 = new Bedrag (0.0);


	@Test(expected = IllegalArgumentException.class)
	public void testNegativeBedrag() {
		Bedrag actual = new Bedrag(-10.0);
	}


	@Test
	public void testPlus() {
		Bedrag actual = Amount10.plus(Amount5);
		Bedrag expected = new Bedrag(15.0);
		assertEquals(expected, actual);
	}

	@Test
	public void testMinus() {
		Bedrag actual = Amount10.minus(Amount5);
		Bedrag expected = new Bedrag(5.0);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMinusNegative() {
		Bedrag actual = Amount5.minus(Amount10);	
	}

	
	@Test
	public void testMultiplyBedrag() {
		Bedrag actual = Amount5.multiplyBy(Amount10);	
		Bedrag expected = new Bedrag(50.0);
		assertEquals(expected,actual);
	}
	
	@Test
	public void testMultiplyDouble() {
		Bedrag actual = Amount5.mulitplyBy(10.0);	
		Bedrag expected = new Bedrag(50.0);
		assertEquals(expected,actual);	
	}
	
	
	@Test
	public void testDivideBedrag() {
		Optional<Bedrag> actualOptional = Amount10.dividedBy(Amount5);	
		assertTrue(actualOptional.isPresent());
		
		Bedrag actual = actualOptional.get();
		Bedrag expected = new Bedrag(2.0);
		assertEquals(expected,actual);	
	}
	
	

	@Test
	public void testDivideBedragZero() {
		Optional<Bedrag> actualOptional = Amount10.dividedBy(Amount0);	
		assertFalse(actualOptional.isPresent());
		
	}
	
	@Test
	public void testDivideDouble() {
		Optional<Bedrag> actualOptional = Amount10.dividedBy(5.0);	
		assertTrue(actualOptional.isPresent());
		
		Bedrag actual = actualOptional.get();
		Bedrag expected = new Bedrag(2.0);
		assertEquals(expected,actual);	
	}
	
	

	@Test
	public void testDivideDoubleZero() {
		Optional<Bedrag> actualOptional = Amount10.dividedBy(0.0);	
		assertFalse(actualOptional.isPresent());
		
	}
	
	@Test
	public void testEquals() {
		Bedrag AmountNew10 = new Bedrag(10.0);
		assertTrue(AmountNew10.equals(Amount10));
		
	}
	
	@Test
	public void testEqualsReverse() {
		Bedrag AmountNew10 = new Bedrag(10.0);
		assertTrue(Amount10.equals(AmountNew10));
		
	}
	
	
	@Test
	public void testUnEquals() {
		assertFalse(Amount10.equals(Amount5));
		
	}
	
	@Test
	public void testUnEqualsReverse() {
		assertFalse(Amount5.equals(Amount10));
		
	}
	
	@Test
	public void testEqualsFalse() {
		Bedrag AmountNew10 = null;
		assertFalse(Amount10.equals(AmountNew10));
		
	}
	
	
	public void testEqualsFalseFloat() {
		Float AmountNew10 = new Float(10.0);
		assertFalse(Amount10.equals(AmountNew10));
		
	}
	
	
	
	@Test
	public void compareToGreater() {
		int actual = Amount10.compareTo(Amount5);
		int expected = 1; 
		assertEquals(expected,actual);
	}
	
	@Test
	public void compareToLess() {
		int actual = Amount5.compareTo(Amount10);
		int expected = -1; 
		assertEquals(expected,actual);
	}
	

	@Test
	public void compareToEqual() {
		Bedrag AmountNew10 = new Bedrag(10.0);
		int actual = AmountNew10.compareTo(Amount10);
		int expected = 0; 
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void lessThan() {
		
		boolean actual = Amount5.lessThan(Amount10);
		assertTrue(actual);
	}
	
	@Test
	public void lessThanFalse() {
		
		boolean actual = Amount10.lessThan(Amount5);
		assertFalse(actual);
	}
	
	
	@Test
	public void greaterThanFalse() {
		boolean actual = Amount10.lessThan(Amount5);
		assertFalse(actual);
	}
	
	@Test
	public void greaterThan() {
		boolean actual = Amount10.greaterThan(Amount5);
		assertTrue(actual);
	}
	
	
	@Test(expected =IllegalArgumentException.class)
	public void greaterThanNull() {
		Bedrag nullBedrag = null;
		boolean actual = Amount10.greaterThan(nullBedrag);
		assertTrue(actual);
	}
	
	

	@Test(expected =IllegalArgumentException.class)
	public void lessThanNull() {
		Bedrag nullBedrag = null;
		boolean actual = Amount10.lessThan(nullBedrag);
		assertTrue(actual);
	}
	
	
	
}
