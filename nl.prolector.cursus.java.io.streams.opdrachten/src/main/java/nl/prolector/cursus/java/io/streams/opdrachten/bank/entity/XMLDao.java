package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Optional;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.toa.BankEntity2BankWS;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.webservices.Bank;

public class XMLDao implements BankDAO {

	
	private String getXMLFilename(String fileName) {
		
		StringBuilder aBuilder = new StringBuilder("target/");
		aBuilder.append(fileName);
		aBuilder.append(".xml");
		return aBuilder.toString();
	}
	
	
	@Override
	public boolean add(BankEntity obj) {
		boolean isAdded;
		
		try {
			String pathName = this.getXMLFilename(obj.getNaam());
			File destination = new File( pathName );
			
			
			JAXBContext aContext = JAXBContext.newInstance(Bank.class);
			
			
			//Create Marshaller
	        Marshaller jaxbMarshaller;
			jaxbMarshaller = aContext.createMarshaller();
			
	        //Required formatting??
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        
	        BankEntity2BankWS b2b = new BankEntity2BankWS();
			Bank webBank = b2b.convert(obj);
	        
			jaxbMarshaller.marshal( webBank, destination );
		
			
			
			
			isAdded = true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAdded = false;
		}
		
		// TODO Auto-generated method stub
		return isAdded;
	}

	@Override
	public boolean modify(BankEntity obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(BankEntity obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<BankEntity> read(String aBankNaam) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankDAO aDao = new XMLDao();
		BankEntity abnAmro = new BankEntity("Triodos");
		abnAmro.openRekeningCourant("sam", 10);
		abnAmro.openRekeningCourant("clover", 10.29);
		abnAmro.openSpaarRekening("steve", 29.0d);

		System.out.println(aDao.add(abnAmro));
		
		
		
	}

}
