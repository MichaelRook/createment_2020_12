package nl.prolector.cursus.java.io.streams.opdrachten.bank;

import java.io.Serializable;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.entity.DAO;

public interface ErdDao<E, OID extends Comparable<OID> & Serializable> extends DAO<E> {
	E find(OID pk);

}
