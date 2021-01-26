package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.util.Optional;

public interface BankDAO extends DAO<BankEntity> {

	public Optional<BankEntity> read(String aBankNaam);
}
