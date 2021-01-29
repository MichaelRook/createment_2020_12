package nl.prolector.cursus.java.io.streams.opdrachten.bank.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankDAO;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.SqlDao;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.toa.BankEntity2BankWS;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.Bank;

@RestController
public class BankRestController {
//    @Autowired
//    private EntityManager em;

	
	

    @RequestMapping(value = "/api/bank/", method = RequestMethod.GET)
    public Bank showall() {
		BankEntity bank = new BankEntity("My Bank");
		bank.openRekeningCourant("piet", 10.0);
		BankEntity2BankWS toaBank =  new BankEntity2BankWS();
		Bank annotatedBank = toaBank.convert(bank);
		return annotatedBank;
    }
    
    
    @RequestMapping(value = "/api/addbank/{banknaam}/{houder}", method = RequestMethod.GET)
    public boolean addBank(@PathVariable("houder") String houder, @PathVariable("banknaam") String banknaam) {
		boolean isAdded = false;
    	BankEntity bank = new BankEntity(banknaam);
		bank.openRekeningCourant(houder, 10.0);
		BankDAO aDao = new SqlDao();
		isAdded = aDao.add(bank);
		
		return isAdded;
    }
    
    
    
    
//    @Transactional
//    @RequestMapping(value = "/api/Klant/maarten", method = RequestMethod.GET)
//    public Klant addMaarten() {
//        Klant maarten = new Klant("mtstorm");
//        em.persist(maarten);
//        return maarten;
//        
//    }    
}

