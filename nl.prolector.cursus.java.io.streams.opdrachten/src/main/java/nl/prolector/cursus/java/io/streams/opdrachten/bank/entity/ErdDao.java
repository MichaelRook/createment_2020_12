package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import java.io.Serializable;

public interface ErdDao<E, OID extends Comparable<OID> & Serializable> extends DAO<E> {
	E find(OID pk);

}
