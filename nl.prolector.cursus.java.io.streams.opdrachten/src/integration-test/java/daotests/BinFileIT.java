package daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BinFileDAO;

public class BinFileIT {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testWriteBankJson() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		BinFileDAO aDao = new BinFileDAO();
		aDao.add(aBank);
		
		boolean isPresent = new File("target/", "TestBank.bin").exists();
		assertTrue("The file TestBank was not correctly written to target/.",isPresent);
		
	}


	
	@Test
	public void testReadBankJson() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		BinFileDAO aDao = new BinFileDAO();
		Optional<BankEntity> recoveredBank = aDao.read("TestBank");

		assertTrue("The stored bank is not present.",recoveredBank.isPresent());
		BankEntity bBank = recoveredBank.get();
		assertEquals(aBank,bBank);
		
		
	}
	
	@Test
	public void testReadNonExistingBankJson() {
		
		
		BinFileDAO aDao = new BinFileDAO();
		Optional<BankEntity> recoveredBank = aDao.read("BloedBank");
		assertFalse(recoveredBank.isPresent());
		
		
	}

}
