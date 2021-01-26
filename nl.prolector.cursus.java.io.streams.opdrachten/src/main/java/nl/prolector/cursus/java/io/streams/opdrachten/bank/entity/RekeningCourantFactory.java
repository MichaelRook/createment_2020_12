package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.CourantRekeningMemento;

public class RekeningCourantFactory extends AbstractBankrekeningEntity<CourantRekeningMemento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RekeningCourantFactory() {
	}

	public RekeningCourantFactory(String houder, double saldo, int rekeningnummer) {
		super(houder, saldo, rekeningnummer);
	}

	CourantRekeningMemento getState() {
		CourantRekeningMemento aCourantMement = new CourantRekeningMemento(this.getHouder(), this.getSaldo(),
				this.getRekeningnummer());
		return aCourantMement;
	}

}
