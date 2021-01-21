package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

public class jsonDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 JsonObject value = Json.createObjectBuilder()
			     .add("firstName", "John")
			     .add("lastName", "Smith")
			     .add("age", 25)
			     .add("address", Json.createObjectBuilder()
			         .add("streetAddress", "21 2nd Street")
			         .add("city", "New York")
			         .add("state", "NY")
			         .add("postalCode", "10021"))
			     .add("phoneNumber", Json.createArrayBuilder()
			         .add(Json.createObjectBuilder()
			             .add("type", "home")
			             .add("number", "212 555-1234"))
			         .add(Json.createObjectBuilder()
			             .add("type", "fax")
			             .add("number", "646 555-4567")))
			     .build();
		 

		 try (JsonWriter aWriter = Json.createWriter(new PrintWriter("target/demo.json"));) {
		 aWriter.write(value);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
		
		 Path p = Paths.get("target/demo.json");
		 try ( JsonReader aReader = Json.createReader(Files.newBufferedReader(p));) {
			 JsonStructure aStructure = aReader.read();
			 System.out.println("Output");
			 System.out.println(aStructure.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 //		
//		 try (PrintWriter aPrintWrit = new PrintWriter("demo.json");) {
//			 JsonWriter aWriter = Json.createWriter(aPrintWrit);
//			aWriter.writeObject(value);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		 
		
	}

}
