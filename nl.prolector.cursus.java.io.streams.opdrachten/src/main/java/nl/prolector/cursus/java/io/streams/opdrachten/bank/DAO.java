package nl.prolector.cursus.java.io.streams.opdrachten.bank;

import java.io.Serializable;

/**
 * Doel, wat gaan we doen?
 * 
 *  Objecten die gaan we transleren naar entiteiten *in ERD) en die stoppen we in een database.
 *  
 *  UML, ClassDiagram --> Class heeft instance fields -> instances (Objecten dynamisch)
 *
 *  ERD
 *  Entity Relational Diagram  --> Tabel heeft kolommen
 *
 *                      UML ClassDiagram                          ERD
 *                     Object                  Comparable<T>
 *                  ------------              --------------
 *                  ------------              --------------
 *                  equals(Object) boolean;   comparable(T):int
 *                  hashCode();
 *                  ------------       / ->   --------------
 *                       ^            .
 *                       |           /
 *                  BankRekening                                BankRekening   
 *                  ------------                                ------------
 *                           									pk  oid:serializable 
 *                  nummer:int                                  ak1 nummer:decimal(10,0)  not nullable
 *                  houder:string                               houder:varchar(50)    not nullable
 *                  saldo:double                                saldo:decimal(17,2)   not nullable
 *                  											bankrekeningsoort_oid not nullable (discriminator)
 *                                                              ak1 bank_oid              not nullable
 *                  ------------                                ------------ 
 *                  ^          ^ 
 *                  |          |                 <=>         
 *       SpaarRekening        RekeningCourant     |
 *       										  |
 *                                                *--> DAO Objecten persisteren    
 *
 * Unieke bankrekening in sql => oid   en in Java == (reference)
 * 												--Java extra gedrag toekennen -> isIdentical -> equals==true
 * 
 * 103 => nieuwe leeg object en dan vullen (info->state->memento)
 * 
 */



/*
 * 
 * SQL :-(
 * Create: In sql een row toevoegen aan een tabel. Entity persisteert dmv insert.
 * 
 * Read //finders
 * update
 * Delete
 */


public interface DAO<E> {
	boolean add(E obj);
	boolean modify(E obj);
	boolean remove(E obj);
/*
 * find op basis van technische sleutel => OID
 * find op basis van functionele sleutel -> equals
 */
}
