package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.CourantRekeningMemento;

public class RekeningCourantFactoryTest {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.0, 1003);
	}
	
	@Test
	public void testGetHouder() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.0, 1003);
		String expected = "Harry";
		String actual = aCourant.getHouder();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testGetSaldo() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		Double expected = 10.51;
		Double actual = aCourant.getSaldo();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testRekeningNummer() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		Integer expected = 1003;
		Integer actual = aCourant.getRekeningnummer();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testStortBedrag() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		aCourant.stort(2.0);
		
		Double actual = aCourant.getSaldo();
		Double expected = 12.51;
		
		
		assertEquals(expected,actual);
		
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testStortBedragNegatief() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		aCourant.stort(-2.0);
	}
	
	@Test
	public void testNeemOp() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
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
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		try {
			aCourant.neemOp(20.0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	@Test (expected = IllegalArgumentException.class)
	public void testNeemOpNegatief() throws Exception {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		aCourant.neemOp(-5.0);
		
	}
	
	
	@Test
	public void testgetState() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory("Harry", 10.51, 1003);
		BankRekeningMemento aMement = aCourant.getState();
		
		assertTrue(aMement!=null);
		
		assertEquals(aMement.getHouder(), "Harry");
		
	}
	
	@Test
	public void testsetState() {
		RekeningCourantFactory aCourant = new RekeningCourantFactory();
		CourantRekeningMemento aMement = new CourantRekeningMemento("Harry",10.51,1003);
		
		aCourant.setState(aMement);
		
		assertEquals(aCourant.getHouder(), "Harry");
		assertEquals(Double.valueOf(aCourant.getSaldo()), new Double(10.51));
		assertEquals(aCourant.getRekeningnummer(), 1003);
		
	}
	

	
		
		

}

	
	

	
	
	

