package nl.prolector.cursus.java.io.streams.opdrachten.bank.controller;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.Pinpas;

public interface OpenSpaarRekeningService {

	
	Pinpas openSpaarRekening( String banknaam,String houder, double saldo);
}
