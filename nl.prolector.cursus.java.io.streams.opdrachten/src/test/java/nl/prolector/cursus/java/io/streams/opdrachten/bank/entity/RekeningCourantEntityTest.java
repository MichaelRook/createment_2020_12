package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.CourantRekeningMemento;

public class RekeningCourantEntityTest {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.0, 1003);
	}
	
	@Test
	public void testGetHouder() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.0, 1003);
		String expected = "Harry";
		String actual = aCourant.getHouder();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testGetSaldo() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		Double expected = 10.51;
		Double actual = aCourant.getSaldo();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testRekeningNummer() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		Integer expected = 1003;
		Integer actual = aCourant.getRekeningnummer();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testStortBedrag() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		aCourant.stort(2.0);
		
		Double actual = aCourant.getSaldo();
		Double expected = 12.51;
		
		
		assertEquals(expected,actual);
		
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testStortBedragNegatief() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		aCourant.stort(-2.0);
	}
	
	@Test
	public void testNeemOp() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		try {
			aCourant.neemOp(10.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Failed to subtract 10.00 from 10.51");
		}
		
		Double actual = aCourant.getSaldo();
		Double expected = 0.51;
		
		assertEquals(expected,actual,0.000001);
		
	}
	
	@Test (expected = Exception.class)
	public void testNeemOpTeveel() throws Exception {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		try {
			aCourant.neemOp(20.0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	@Test (expected = IllegalArgumentException.class)
	public void testNeemOpNegatief() throws Exception {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		aCourant.neemOp(-5.0);
		
	}
	
	
	@Test
	public void testgetState() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity("Harry", 10.51, 1003);
		BankRekeningMemento aMement = aCourant.getState();
		
		assertTrue(aMement!=null);
		
		assertEquals(aMement.getHouder(), "Harry");
		
	}
	
	@Test
	public void testsetState() {
		RekeningCourantEntity aCourant = new RekeningCourantEntity();
		CourantRekeningMemento aMement = new CourantRekeningMemento("Harry",10.51,1003);
		
		aCourant.setState(aMement);
		
		assertEquals(aCourant.getHouder(), "Harry");
		assertEquals(Double.valueOf(aCourant.getSaldo()), new Double(10.51));
		assertEquals(aCourant.getRekeningnummer(), 1003);
		
	}
	
	@Test
	public void testHashCodeTrue() {
		String naam1 = "Harry";
		
		RekeningCourantEntity Rekening1 = new RekeningCourantEntity("Harry", 10.0, 1001);
		RekeningCourantEntity Rekening2 = new RekeningCourantEntity("Harry", 10.0, 1001);
		int expected = naam1.hashCode() * 1001 * 59;
		assertEquals(expected, Rekening1.hashCode());
		assertEquals(Rekening1.hashCode(), Rekening2.hashCode());

	}
	
	@Test 
	public void testHashCodeFalse() {
		RekeningCourantEntity Rekening1 = new RekeningCourantEntity("Harry", 10.0, 1001);
		RekeningCourantEntity Rekening2 = new RekeningCourantEntity("Sammy", 10.0, 1002);
		assertFalse(Rekening1.hashCode() == Rekening2.hashCode());

	}


	
		
		

}

	
	

	
	
	

