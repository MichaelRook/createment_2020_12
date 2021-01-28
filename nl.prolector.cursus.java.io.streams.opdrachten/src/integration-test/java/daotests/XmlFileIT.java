package daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.BankEntity;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.JsonDao;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.TextFileDAO;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.XMLDao;

public class XmlFileIT {

	@Test
	public void testValidatingXsdBank() {
		assertValid("Harry.xml","Harry is not a valid XML File");
	}
	
	@Test
	public void testValidatingEmptyBank() {
		assertValid("EmptyBank.xml","An Empty bank is invalidly rejected");
	}
	
	@Test
	public void testValidatingOnlyRekeningCourantBank() {
		assertValid("RekeningCourantOnlyBank.xml","Bank with only courant rekeningen is invalidly rejected");
	}
	
	@Test
	public void testValidatingOnlySpaarRekeningBank() {
		assertValid("SpaarRekeningOnlyBank.xml","Bank with only courant rekeningen is invalidly rejected");
	}
	
	
	
	@Test
	public void testWriteValidBank() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		XMLDao aDao = new XMLDao();
		aDao.add(aBank);
		
		boolean isPresent = new File("target/", "TestBank.xml").exists();
		assertTrue("The file TestBank was not correctly written to target/.",isPresent);
		
		
	}
	
	
	@Before
	public void setup() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		XMLDao aDao = new XMLDao();
		aDao.add(aBank);
	}
	
	
	@Test
	public void testReadBankXML() {
		BankEntity aBank = new BankEntity("TestBank");
		aBank.openRekeningCourant("Timothy", 0.01);
		aBank.openSpaarRekening("Mushu", 200.10);
		aBank.openRekeningCourant("Simba", 10.50);
		aBank.openSpaarRekening("Dagobert", 200_000_000.10);
		aBank.openRekeningCourant("Nala", 10.30);
		
		XMLDao aDao = new XMLDao();
		Optional<BankEntity> recoveredBank = aDao.read("TestBank");

		assertTrue("The stored bank is not present.",recoveredBank.isPresent());
		BankEntity bBank = recoveredBank.get();
		assertEquals(aBank,bBank);
	}
	
	@Test
	public void testReadNonExistingBankTxt() {
		
		
		XMLDao aDao = new XMLDao();
		Optional<BankEntity> recoveredBank = aDao.read("BloedBank");
		assertFalse(recoveredBank.isPresent());
		
		
	}
	
	

	
	
	/**
	 * Asserts whether the provided bestand contains xml data which are conform the xsd provided in src/main/xsd/Bank.xsd
	 * @param bestand an xml file to be tested
	 * @param errorMessage Custom error message
	 */
	public static void assertValid(String bestand, String errorMessage) {
		
		File fXmlFile = new File("src/integration-test/resources/xml/" + bestand);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    dbFactory.setNamespaceAware(true);
	    DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.normalize();
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		    // load a WXS schema, represented by a Schema instance
		    Source schemaFile = new StreamSource(new File("src/main/xsd/Bank.xsd"));
		    Schema schema = factory.newSchema(schemaFile);

		    // create a Validator instance, which can be used to validate an instance document
		    Validator validator = schema.newValidator();
		    DOMSource aDomSource = new DOMSource(doc);
		    validator.validate(aDomSource);
		    
		    
			
		} catch (Exception e) {
			
			throw new AssertionError(errorMessage, e);
		} 
	    
	            
	    
	}

}
