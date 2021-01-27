package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.SpaarRekeningMemento;

public class SpaarRekeningEntityTest {


	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.0, 1003);
	}
	
	@Test
	public void testGetHouder() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.0, 1003);
		String expected = "Harry";
		String actual = aSpaar.getHouder();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testGetSaldo() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		Double expected = 10.51;
		Double actual = aSpaar.getSaldo();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testRekeningNummer() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		Integer expected = 1003;
		Integer actual = aSpaar.getRekeningnummer();
		
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testStortBedrag() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		aSpaar.stort(2.0);
		
		Double actual = aSpaar.getSaldo();
		Double expected = 10.51 + 2.0 + 2.0*SpaarrekeningEntity.CREDIT_RENTE/100;
		
		
		assertEquals(expected,actual);
		
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testStortBedragNegatief() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		aSpaar.stort(-2.0);
	}
	
	@Test
	public void testNeemOp() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		try {
			aSpaar.neemOp(5.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Failed to subtract 5.00 + boetebedrag from 10.51");
		}
		
		Double actual = aSpaar.getSaldo();
		Double expected = 10.51 - (5.00 + SpaarrekeningEntity.BOETE_RENTE * 5.0 / 100.0);
		
		assertEquals(expected,actual,0.000001);
		
	}
	
	@Test (expected = Exception.class)
	public void testNeemOpTeveel() throws Exception {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		try {
			aSpaar.neemOp(20.0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	@Test (expected = Exception.class)
	public void testNeemOpNegatief() throws Exception {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		aSpaar.neemOp(-5.0);
		
	}
	
	
	@Test
	public void testgetState() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry", 10.51, 1003);
		BankRekeningMemento aMement = aSpaar.getState();
		
		assertTrue(aMement!=null);
		
		assertEquals(aMement.getHouder(), "Harry");
		
	}
	
	@Test
	public void testsetState() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity();
		SpaarRekeningMemento aMement = new SpaarRekeningMemento("Harry",10.51,1003);
		
		aSpaar.setState(aMement);
		
		assertEquals(aSpaar.getHouder(), "Harry");
		assertEquals(Double.valueOf(aSpaar.getSaldo()), new Double(10.51));
		assertEquals(aSpaar.getRekeningnummer(), 1003);
		
	}
	
	
	@Test
	public void testPrintBoeteCreditPrecentages() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry",10.51,1003);
		aSpaar.print();
	}
	
	
	@Test
	public void testToStringMethod() {
		SpaarrekeningEntity aSpaar = new SpaarrekeningEntity("Harry",10.51,1003);
		String actual = aSpaar.toString();
		String expected = "Bankrekening [houder=Harry, saldo=10.51, rekeningnummer=1003]";
		assertEquals(expected,actual);
				
	}

	
		
		


}
