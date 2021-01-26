package nl.prolector.cursus.java.io.streams.opdrachten.bank.controller;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.Pinpas;

public interface OpenRekeningCourantService {

	
	Pinpas openRekeningCourant( String banknaam,String houder, double saldo);
}
