package nl.prolector.cursus.java.io.streams.opdrachten.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;

@RestController
public class BankRestController {
//    @Autowired
//    private EntityManager em;

	
	

    @RequestMapping(value = "/api/bank", method = RequestMethod.GET)
    public BankEntity showall() {
		BankEntity bank = new BankEntity("My Bank");
		bank.openRekeningCourant("piet", 10.0);
		return bank;
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

