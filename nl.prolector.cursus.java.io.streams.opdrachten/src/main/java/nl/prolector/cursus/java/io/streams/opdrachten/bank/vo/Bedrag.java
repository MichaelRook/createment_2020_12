package nl.prolector.cursus.java.io.streams.opdrachten.bank.vo;

import java.util.Optional;

public final class Bedrag extends Number implements Comparable<Bedrag> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double value;
	public static final Bedrag MIN = new Bedrag(0.01);
	public static final Bedrag MAX = new Bedrag(1_000_000);
	public static final Bedrag Zero = new Bedrag(0.00);

	/**
	 * Creatie van een niew bedrag object
	 * 
	 * @param value
	 *            the Couble value to add
	 * @return gecreerd object
	 * @throws NullPointerException
	 *             if null is presented as input
	 * @htrows NumberFormatException als de waarde geen positieve double is.
	 */ 

	public Bedrag(double value) {

		if (value < 0) {
			throw new IllegalArgumentException("Bedrag cannot be negative");
		}

		this.value = value;
	}

	@Override
	public int compareTo(Bedrag anderBedrag) {

		return Double.compare(this.doubleValue(), anderBedrag.doubleValue());
	}

	@Override
	public boolean equals(Object o) {
		boolean gelijk = false;

		if (o instanceof Bedrag) {
			Bedrag b = (Bedrag) o;

			gelijk = this.compareTo(b) == 0;
		}

		return gelijk;
	}

	public Optional<Bedrag> dividedBy(double Divisor) {
		Bedrag result;
		if (Divisor != 0) {
			result = new Bedrag((Double) this.doubleValue() / Divisor);
		} else {
			result = null;
		}
		return Optional.ofNullable(result);

	}

	/**
	 * If divisor bedrag is equal to 0 results returns an empty optional.
	 * 
	 * @param that
	 * @return
	 */
	public Optional<Bedrag> dividedBy(Bedrag that) {
		Bedrag result;
		if (that.doubleValue() != 0) {
			result = new Bedrag((Double) this.doubleValue() / that.doubleValue());
		} else {
			result = null;
		}
		return Optional.ofNullable(result);
	}

	public Bedrag mulitplyBy(Double Divisor) {
		return new Bedrag((Double) this.doubleValue() * Divisor);
	}

	public Bedrag multiplyBy(Bedrag that) {
		return new Bedrag((Double) this.doubleValue() * that.doubleValue());
	}
	
	
	public double doubleValue() {
		return this.value;
	}

	public Bedrag minus(Bedrag that) {
		return new Bedrag(this.doubleValue() - that.doubleValue());
	}

	public Bedrag plus(Bedrag that) {
		return new Bedrag(this.doubleValue() + that.doubleValue());
	}


	
	public boolean greaterThan(Bedrag that) {
		this.notNull(that);
		return this.doubleValue() > that.doubleValue();
	}
	
	public boolean lessThan(Bedrag that) {
		this.notNull(that);
		return this.doubleValue() < that.doubleValue();
	}
	
	
	
	private void notNull(Object that) {
		if(that==null) {
			throw new IllegalArgumentException("Compared to object is NULL");
		}
	}
	
	
	@Override
	public int intValue() {
		return (int) this.value;
	}

	@Override
	public long longValue() {
		return (long) this.value;
	}

	@Override
	public float floatValue() {
		return (float) this.value;
	}
	
	@Override
	public int hashCode() {
		return Double.hashCode(this.value);
	}

}
