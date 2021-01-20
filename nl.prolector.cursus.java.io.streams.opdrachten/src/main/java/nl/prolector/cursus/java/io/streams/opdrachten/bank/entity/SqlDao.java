package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.BankRekeningMemento;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.RekeningenType;

public class SqlDao implements BankDAO {
	
	private Connection create() throws SQLException {
		Properties props = SqlDao.getProperties();
		String url = props.getProperty("url");
		Connection resultConnection = DriverManager.getConnection(url,props);
		resultConnection.setAutoCommit(false);
		return resultConnection;
	};

	@Override
	public boolean add(Bank obj) {

		boolean isAdded;

		
		try (Connection conn = this.create()) {
			try {
				String sqlBank = "SELECT Naam from Bank";

				

				PreparedStatement setBank = conn.prepareStatement("INSERT INTO Bank (NAAM) Values (?);");
				setBank.setString(1, obj.getNaam());
				setBank.execute();

				for (Bankrekening<?> aRekening : obj) {
					BankRekeningMemento aMemento = aRekening.getState();

					String type = RekeningenType.fromType(aMemento.getClass()).toString();
					int rekeningNummer = aMemento.getRekeningnummer();
					double saldo = aMemento.getSaldo();
					String houder = aMemento.getHouder();

					StringBuilder aSqlString = new StringBuilder("");
					aSqlString.append("INSERT INTO BankRekening (BANK_OID, SOORT_OID,NUMMER,SALDO,HOUDER) ")
							.append("Values ((SELECT OID ").append("FROM Bank ").append("WHERE NAAM = ?),")
							.append("(SELECT OID ").append("FROM BankRekeningSoort ").append("WHERE NAAM = ?)")
							.append(",?,?,?)");
					System.out.println(aSqlString);
					PreparedStatement setBankRek = conn.prepareStatement(aSqlString.toString());

					setBankRek.setString(1, obj.getNaam());
					setBankRek.setString(2, type);
					setBankRek.setInt(3, rekeningNummer);
					setBankRek.setDouble(4, saldo);
					setBankRek.setString(5, houder);

					setBankRek.execute();
				}

				conn.commit();

				isAdded = true;
			} catch (SQLException e) {
				e.printStackTrace();
				isAdded = false;
				conn.rollback();
			}
		} catch (SQLException e) {

			e.printStackTrace();

			isAdded = false;
		}

		return isAdded;
	}

	@Override
	public boolean modify(Bank obj) {
		return false;
	}

	@Override
	public boolean remove(Bank obj) {
		return false;
	}

	@Override
	public Optional<Bank> read(String aBankNaam) {
	
		try (Connection conn = this.create()) {
			String sqlBank = "SELECT Naam from Bank";
			Statement getBank = conn.createStatement();
			ResultSet result = getBank.executeQuery(sqlBank);
			while (result.next()) {
				String bankNaam = result.getString(1);
				System.out.println(bankNaam);
			}
			;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	private static Properties getProperties() {
		Properties props = new Properties();
		try (InputStream in = SqlDao.class.getClassLoader()
				.getResourceAsStream("nl/prolector/cursus/java/io/streams/opdrachten/bestand.properties");) {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static void main(String[] args) {
		BankDAO aDao = new SqlDao();
		Bank abnAmro = new Bank("Harry7");
		abnAmro.openRekeningCourant("sam", 10);
		abnAmro.openRekeningCourant("clover", 10.0);
		abnAmro.openSpaarRekening("steve", 29.0d);

		System.out.println(aDao.add(abnAmro));

	}

}
