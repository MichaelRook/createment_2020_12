package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.CourantRekeningMemento;

public class RekeningCourant extends Bankrekening<CourantRekeningMemento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RekeningCourant() {
	}

	public RekeningCourant(String houder, double saldo, int rekeningnummer) {
		super(houder, saldo, rekeningnummer);
	}

	CourantRekeningMemento getState() {
		CourantRekeningMemento aCourantMement = new CourantRekeningMemento(this.getHouder(), this.getSaldo(),
				this.getRekeningnummer());
		return aCourantMement;
	}

}
