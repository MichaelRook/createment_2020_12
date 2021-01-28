package nl.prolector.cursus.java.io.streams.opdrachten.bank.toa;

import java.math.BigDecimal;
import java.math.BigInteger;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.AbstractBankrekeningEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.BankRekening;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.ObjectFactory;

public class BankRekeningFactory2BankRekeningWS implements TransferableObjectAssembler<AbstractBankrekeningEntity<?>,BankRekening> {

	@Override
	public BankRekening convert(AbstractBankrekeningEntity<?> a) {
		
		BankRekening aBankRekening;
		ObjectFactory bankRekeningFactory = new ObjectFactory();
		
		switch (a.getClass().getSimpleName()) {
		case "RekeningCourantEntity":
			
			aBankRekening = bankRekeningFactory.createRekeningCourant();
		
			break;

		case "SpaarrekeningEntity":
			
			aBankRekening = bankRekeningFactory.createSpaarRekening();
			break;
			
		default:
			aBankRekening = null;
		}
		
		aBankRekening.setNummer(BigInteger.valueOf(a.getRekeningnummer()));
		aBankRekening.setHouder(a.getHouder());
		aBankRekening.setSaldo(BigDecimal.valueOf(a.getSaldo()));
		
		return aBankRekening;
	}
	
	


}
