package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.IOException;
import java.util.Optional;

public class Harry {

	public Harry() {
		// TODO Auto-generated constructor stub
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
				
		BankEntity rabobank = new BankEntity("Rabobank");
		rabobank.openRekeningCourant("Maarten", 0.01);
		rabobank.openRekeningCourant("Michael", 10000.0);
		rabobank.openSpaarRekening("Roel", 10000000000.0);
		
		BankDAO dao = new BinFileDAO();
		dao.add(rabobank);
		Optional<BankEntity> abc = dao.read("Rabobank");
		if (abc.isPresent()) {
			System.out.println("approved");
		} else {
			System.out.println("sorry");
		}
		
		
		
		
		
		
		
		
		
		
	}
	

}
