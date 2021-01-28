package daotests;

import java.io.File;
import java.io.IOException;

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

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFileIT {

	@Test
	public void testharry() {
		assertValid("Harry.xml","Harry is not a valid XML File");
	}
	
	
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
