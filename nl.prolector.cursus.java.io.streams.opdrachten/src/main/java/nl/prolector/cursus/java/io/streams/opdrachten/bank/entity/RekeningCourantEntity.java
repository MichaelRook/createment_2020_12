package nl.prolector.cursus.java.io.streams.opdrachten.bank.entity;

import nl.prolector.cursus.java.io.streams.opdrachten.bank.vo.CourantRekeningMemento;

public class RekeningCourantEntity extends AbstractBankrekeningEntity<CourantRekeningMemento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RekeningCourantEntity() {
	}

	public RekeningCourantEntity(String houder, double saldo, int rekeningnummer) {
		super(houder, saldo, rekeningnummer);
	}

	CourantRekeningMemento getState() {
		CourantRekeningMemento aCourantMement = new CourantRekeningMemento(this.getHouder(), this.getSaldo(),
				this.getRekeningnummer());
		return aCourantMement;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && obj instanceof RekeningCourantEntity;
	}


}
