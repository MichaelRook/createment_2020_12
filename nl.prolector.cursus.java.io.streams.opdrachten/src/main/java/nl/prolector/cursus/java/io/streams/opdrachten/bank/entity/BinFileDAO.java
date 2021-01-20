package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

public class BinFileDAO implements BankDAO {

	@Override
	public boolean add(Bank obj) {
		boolean successqfual = true;
		String fileName = BinFileDAO.convertToFileName(obj.getNaam());
		try(ObjectOutputStream newOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));){
			newOutputStream.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
			successqfual = false;

		}
		return successqfual;
	}

	@Override
	public boolean modify(Bank obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Bank obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Bank> read(String aBankNaam) {
		String fileName = BinFileDAO.convertToFileName(aBankNaam);
		Bank aBank;
		try(ObjectInputStream newInputStream = new ObjectInputStream(new FileInputStream(fileName));){
			aBank = (Bank) newInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			aBank = null;

		}
		return Optional.ofNullable(aBank);
	}
	
	private static String convertToFileName(String bankNaam) {
		StringBuilder fileName = new StringBuilder(bankNaam);
		fileName.append(".bin");
		return fileName.toString();
	}

}
