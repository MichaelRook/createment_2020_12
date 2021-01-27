package nl.prolector.cursus.java.io.streams.opdrachten.bank.toa;

import java.util.List;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.AbstractBankrekeningEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankDAO;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.XMLDao;
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
		
		BankRekening bankrekeningToAdd;
		BankRekeningFactory2BankRekeningWS br2br = new BankRekeningFactory2BankRekeningWS();
		for (AbstractBankrekeningEntity<?> bankrekening : a) {
			
			bankrekeningToAdd = br2br.convert(bankrekening);
			rekeningen.add(bankrekeningToAdd);
		}
		
		return aBank;
	}

	public static void main(String[] args) {
		BankDAO aDao = new XMLDao();
		BankEntity abnAmro = new BankEntity("Triodos");
		abnAmro.openRekeningCourant("sam", 10);
		abnAmro.openRekeningCourant("clover", 10.29);
		abnAmro.openSpaarRekening("steve", 29.0d);
		
		BankEntity2BankWS b2b = new BankEntity2BankWS();
		Bank webBank = b2b.convert(abnAmro);
		
		for (BankRekening rek: webBank.getSpaarRekeningOrRekeningCourant()) {
			System.out.println(rek.getHouder());
		}
		

	}
}
