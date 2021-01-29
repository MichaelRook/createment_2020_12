package daotests;

import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.SqlDao;

public class SqlDaoIT {

//	@Test
//	public void testWriteBankTxt() {
//		BankEntity aBank = new BankEntity("TestBank");
//		aBank.openRekeningCourant("Timothy", 0.01);
//		aBank.openSpaarRekening("Mushu", 200.10);
//		aBank.openRekeningCourant("Simba", 10.50);
//		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
//		aBank.openRekeningCourant("Nala", 10.30);
//		
//		SqlDao aDao = new SqlDao();
//		aDao.add(aBank);
//		
//	
//		
//	}
//	
//	
//	
//	@Test
//	public void testReadBankTxt() {
//		BankEntity aBank = new BankEntity("TestBank");
//		aBank.openRekeningCourant("Timothy", 0.01);
//		aBank.openSpaarRekening("Mushu", 200.10);
//		aBank.openRekeningCourant("Simba", 10.50);
//		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
//		aBank.openRekeningCourant("Nala", 10.30);
//		
//		SqlDao aDao = new SqlDao();
//		Optional<BankEntity> recoveredBank = aDao.read("TestBank");
//
//		assertTrue("The stored bank is not present.",recoveredBank.isPresent());
//		BankEntity bBank = recoveredBank.get();
//		assertEquals(aBank,bBank);
//		
//		
//	}
/*	
	
	@Test
	public void testReadNonExistingBankTxt() {
		
		
		SqlDao aDao = new SqlDao();
		Optional<BankEntity> recoveredBank = aDao.read("BloedBank");
		assertFalse(recoveredBank.isPresent());
		
		
	}*/
	
	

}
