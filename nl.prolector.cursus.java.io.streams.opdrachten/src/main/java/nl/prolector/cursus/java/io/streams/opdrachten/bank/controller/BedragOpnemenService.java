package nl.prolector.cursus.java.io.streams.opdrachten.bank.controller;

import java.util.Optional;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.Bedrag;
import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.Pinpas;

public interface BedragOpnemenService {

	
	//add a valueObject
	
		Optional<Bedrag> neemOp(Pinpas aPas, double bedrag);
}
