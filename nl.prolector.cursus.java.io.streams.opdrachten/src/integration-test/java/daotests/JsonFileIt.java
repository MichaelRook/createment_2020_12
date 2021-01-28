package daotests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.JsonDao;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.TextFileDAO;

public class JsonFileIt {


	

	@Test
	public void testWriteBankJson() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		JsonDao aDao = new JsonDao();
		aDao.add(aBank);
		
		boolean isPresent = new File("target/", "TestBank.json").exists();
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
		
		JsonDao aDao = new JsonDao();
		Optional<BankEntity> recoveredBank = aDao.read("TestBank");

		assertTrue("The stored bank is not present.",recoveredBank.isPresent());
		BankEntity bBank = recoveredBank.get();
		assertEquals(aBank,bBank);
		
		
	}
	
	@Test
	public void testReadNonExistingBankJson() {
		
		
		JsonDao aDao = new JsonDao();
		Optional<BankEntity> recoveredBank = aDao.read("BloedBank");
		assertFalse(recoveredBank.isPresent());
		
		
	}
	
	

}
