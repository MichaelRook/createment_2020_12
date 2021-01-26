package nl.prolector.cursus.java.io.streams.opdrachten.bank.toa;

import java.util.List;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.AbstractBankrekeningEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.Bank;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.BankRekening;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.ObjectFactory;

public class BankEntity2BankWS implements TransferableObjectAssembler<BankEntity,Bank>{


	@Override
	public Bank convert(BankEntity a) {
		
		ObjectFactory bankFactory = new ObjectFactory();
		Bank aBank = bankFactory.createBank();
		aBank.setName(a.getNaam());
		List<BankRekening> rekeningen = aBank.getSpaarRekeningOrRekeningCourant();
		
		
		for (AbstractBankrekeningEntity<?> bankrekening : a) {
			
		}
		
		return aBank;
	}

	
}
