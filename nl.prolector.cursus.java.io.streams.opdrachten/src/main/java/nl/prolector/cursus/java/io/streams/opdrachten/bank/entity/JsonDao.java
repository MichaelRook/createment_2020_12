package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.RekeningenType;

public class JsonDao implements BankDAO {

	@Override
	public boolean add(BankEntity obj) {
		// TODO Auto-generated method stub
		boolean isAdded;

		JsonObjectBuilder theBank = Json.createObjectBuilder();
		theBank.add("naam", obj.getNaam());

		JsonArrayBuilder bankRekeningen = Json.createArrayBuilder();
		
		for (AbstractBankrekeningEntity<?> rec : obj) {

			BankRekeningMemento aMemento = rec.getState();

			JsonObjectBuilder bankRekeningDetails = Json.createObjectBuilder();
			
			bankRekeningDetails.add("Nummer", aMemento.getRekeningnummer());
			bankRekeningDetails.add("Soort", RekeningenType.fromType(aMemento.getClass()).toString());
			bankRekeningDetails.add("Houder", aMemento.getHouder());
			bankRekeningDetails.add("Saldo", aMemento.getSaldo());

			String rekeningNummerString = Integer.toString(aMemento.getRekeningnummer());

			bankRekeningen.add(bankRekeningDetails);

		}
		
		theBank.add("BankRekeningen", bankRekeningen);
		JsonObject jsonOutput = theBank.build();

		String fileName = JsonDao.convertToFileName(obj.getNaam());
		try (JsonWriter aWriter = Json.createWriter(new PrintWriter(fileName));) {
			aWriter.write(jsonOutput);
			isAdded = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAdded = false;
		}

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
		BankEntity aBank;
		
		
		String pathNaam = JsonDao.convertToFileName(aBankNaam);
		
		Path p = Paths.get(pathNaam);
		 try ( JsonReader aReader = Json.createReader(Files.newBufferedReader(p));) {
			 JsonStructure aStructure = aReader.read();
			
			 JsonObject input = aStructure.asJsonObject();
			 
			 String bankNaam = input.getString("naam");
			 aBank = new BankEntity(bankNaam);
			 
			 JsonArray bankRekeningen = input.getJsonArray("BankRekeningen");
			 
			 
			 int maxRekeningNummer = BankEntity.EERSTE_REKENINGNR;
			 
			 for(JsonValue entry : bankRekeningen) {
			
				JsonObject bankRekeningDetails = entry.asJsonObject();
				int rekeningNummer = bankRekeningDetails.getInt("Nummer"); 
				String soort = bankRekeningDetails.getString("Soort"); 
				String houder = bankRekeningDetails.getString("Houder");
				JsonNumber saldoJson = bankRekeningDetails.getJsonNumber("Saldo");
				Double saldo = saldoJson.doubleValue();
				
				AbstractBankrekeningEntity<?> aBankRekening;
				switch (RekeningenType.valueOf(soort)) {
				case Spaar:
					aBankRekening = new SpaarrekeningEntity(houder, saldo, rekeningNummer);
					break;
				case Courant:
					aBankRekening = new RekeningCourantEntity(houder, saldo, rekeningNummer);
					break;

				default:
					throw new AssertionError("No Valid RekeningType");
				}

				aBank.voegRekeningToe(aBankRekening);
				if (aBankRekening.getRekeningnummer() > maxRekeningNummer) {
					maxRekeningNummer = aBankRekening.getRekeningnummer();
				}
				
				 
			 };
			 
			 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aBank = null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			aBank = null;
		}
		
		
		
		return Optional.ofNullable(aBank);
	}

	private static String convertToFileName(String bankNaam) {
		StringBuilder fileName = new StringBuilder("target/");
		fileName.append(bankNaam);
		fileName.append(".json");
		return fileName.toString();
	}

	

}
