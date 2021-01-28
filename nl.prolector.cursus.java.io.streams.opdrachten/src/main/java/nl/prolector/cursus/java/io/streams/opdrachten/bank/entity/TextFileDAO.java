package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Optional;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.RekeningenType;

public class TextFileDAO implements BankDAO {

	@Override
	public boolean add(BankEntity obj) {
		boolean succesfull = true;
		try (PrintWriter aPrintWrit = new PrintWriter(TextFileDAO.convertToFileName(obj.getNaam()));) {

			for (AbstractBankrekeningEntity<?> rec : obj) {

				BankRekeningMemento aMemento = rec.getState();

				aPrintWrit.print(RekeningenType.fromType(aMemento.getClass()).ordinal());
				aPrintWrit.print('\t');
				aPrintWrit.print(aMemento.getHouder());
				aPrintWrit.print('\t');
				aPrintWrit.print(aMemento.getRekeningnummer());
				aPrintWrit.print('\t');
				aPrintWrit.print(aMemento.getSaldo());
				aPrintWrit.println();
			}

		} catch (FileNotFoundException e) {
			succesfull = false;
			e.printStackTrace();
		}
		return succesfull;
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
	
	public Optional<BankEntity> read(String aBankNaam) {

		BankEntity aBank;
		String fileName = TextFileDAO.convertToFileName(aBankNaam);
		try (LineNumberReader aLineNumberReader = new LineNumberReader(new FileReader(fileName));) {
			String bankNaam = aBankNaam;
			aBank = new BankEntity(bankNaam);
			String nextLine;
			int maxRekeningNummer = BankEntity.EERSTE_REKENINGNR;

			while ((nextLine = aLineNumberReader.readLine()) != null) {
				String[] elementen = nextLine.split("\t");
				int type = Integer.parseInt(elementen[0]);
				RekeningenType rekeningType = RekeningenType.values()[type];
				String houder = elementen[1];
				int rekeningNummer = Integer.parseInt(elementen[2]);
				double saldo = Double.parseDouble(elementen[3]);

				AbstractBankrekeningEntity<?> aBankRekening;
				switch (rekeningType) {
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

			}
			aBank.setLaatsteRekeningNr(maxRekeningNummer);

		} catch (Exception e) {
			aBank = null;
			System.out.println("readen ging fout");
		}

		return Optional.ofNullable(aBank);

	}
	
	private static String convertToFileName(String bankNaam) {
		StringBuilder fileName = new StringBuilder("target/");
		fileName.append(bankNaam);
		fileName.append(".txt");
		return fileName.toString();
	}

}
