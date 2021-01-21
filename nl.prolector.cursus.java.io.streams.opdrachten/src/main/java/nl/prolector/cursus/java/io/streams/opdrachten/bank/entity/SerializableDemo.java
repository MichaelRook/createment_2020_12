package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableDemo {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Bank triodos = new Bank("Triodos");
		triodos.openRekeningCourant("Marten", 10);
		triodos.openRekeningCourant("Michal", 5000.0);
		triodos.openSpaarRekening("Rol", 10000.0);
		
		
		try(ObjectOutputStream newOutputStream = new ObjectOutputStream(new FileOutputStream("triodos.bin"));){
			newOutputStream.writeObject(triodos);
		}
		
		try(ObjectInputStream newInputStream = new ObjectInputStream(new FileInputStream("triodos.bin"));){
			Bank aBank = (Bank) newInputStream.readObject();
			System.out.println(aBank.getNaam());
			System.out.println(aBank.openRekeningCourant("jos", 1.0));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
