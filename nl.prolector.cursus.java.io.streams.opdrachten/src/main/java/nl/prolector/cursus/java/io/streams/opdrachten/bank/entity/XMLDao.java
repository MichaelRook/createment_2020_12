package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
		

		BankEntity bankEntity;
		
		try {
			
			String pathName = this.getXMLFilename(aBankNaam);			
			File source = new File( pathName );
			DocumentBuilderFactory aFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder aBuilder = aFactory.newDocumentBuilder();
			Document doc = aBuilder.parse(source);
			
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("*");
			System.out.println(nList.getLength());
			System.out.println("============================");
			
			String bankName = doc.getElementsByTagName("name").item(0).getTextContent();
			
			// creeer bank
			bankEntity = new BankEntity(bankName);
			int nummer;
			String houder;
			String soort;
			double saldo;
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
			 Node node = nList.item(temp);
			 if (node.getNodeName() == "spaarRekening" | node.getNodeName() == "rekeningCourant")
			 {
			    //Print each rekening's detail
			    Element eElement = (Element) node;
			    String nummerString = eElement.getElementsByTagName("nummer").item(0).getTextContent();
			    nummer = Integer.parseInt(nummerString);
				houder = eElement.getElementsByTagName("houder").item(0).getTextContent();
				soort = node.getNodeName();
				String saldoString = eElement.getElementsByTagName("saldo").item(0).getTextContent();
				saldo = Double.parseDouble(saldoString);
//				
//			    System.out.println("bank soort : "    + node.getNodeName());
//			    System.out.println("nummer : "  + eElement.getElementsByTagName("nummer").item(0).getTextContent());
//			    System.out.println("houder : "  + eElement.getElementsByTagName("houder").item(0).getTextContent());
//			    System.out.println("saldo : "    + eElement.getElementsByTagName("saldo").item(0).getTextContent());
//			 
			    AbstractBankrekeningEntity<?> aBankRekening;
				switch (soort) {
				case "spaarRekening":
					aBankRekening = new SpaarrekeningEntity(houder, saldo, nummer);
					break;
				case "rekeningCourant":
					aBankRekening = new RekeningCourantEntity(houder, saldo, nummer);
					break;

				default:
					throw new AssertionError("No Valid RekeningType");
				}

				bankEntity.voegRekeningToe(aBankRekening);
			 
			 
			 }
			}
			

			
		} catch (Exception e) {
		
			bankEntity = null;
			// e.printStackTrace();	
		}
		
		return Optional.ofNullable(bankEntity);


	}

	
		
		
		
		
	

}
