package daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.TextFileDAO;

public class TxtFileIT {


	{
		System.out.println("Oya lele");
	}
	
	@Test
	public void testWriteBankTxt() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		TextFileDAO aDao = new TextFileDAO();
		aDao.add(aBank);
		
		boolean isPresent = new File("target/", "TestBank.txt").exists();
		assertTrue("The file TestBank was not correctly written to target/.",isPresent);
		
	}
	
	
	@Before
	public void setup() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		TextFileDAO aDao = new TextFileDAO();
		aDao.add(aBank);
	}
	
	@Test
	public void testReadBankTxt() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		TextFileDAO aDao = new TextFileDAO();
		Optional<BankEntity> recoveredBank = aDao.read("TestBank");

		assertTrue("The stored bank is not present.",recoveredBank.isPresent());
		BankEntity bBank = recoveredBank.get();
		assertEquals(aBank,bBank);
		
		
	}
	
	
	@Test
	public void testReadNonExistingBankTxt() {
		
		
		TextFileDAO aDao = new TextFileDAO();
		Optional<BankEntity> recoveredBank = aDao.read("BloedBank");
		assertFalse(recoveredBank.isPresent());
		
		
	}
	
	
	
	
	
	

}
