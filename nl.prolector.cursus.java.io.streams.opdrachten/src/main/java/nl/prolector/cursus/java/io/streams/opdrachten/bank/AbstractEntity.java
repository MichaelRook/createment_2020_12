package nl.prolector.cursus.java.io.streams.opdrachten.bank;

import java.io.Serializable;

/**
 * 
 *
 * @param <E> Het type van de entiteit (parent is AbstractEntity)
 * @param <OID>
 */
public abstract class AbstractEntity<E extends AbstractEntity<E, OID>, OID extends Comparable<OID>> 
		implements Entity<E,OID>, Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private OID oid;
	/**
	 * Usage in DAO.
	 * @param oid
	 */
	protected AbstractEntity(OID oid) {
		if(oid == null) {
			throw new IllegalArgumentException("oid is void");
		}
		this.oid = oid;
	}
	/**
	 * Usage in controller.
	 */
	protected AbstractEntity() {
	
	}
	/**
	 * 
	 */
	protected AbstractEntity(E entity) {
		this.oid = entity.getOid();
	}
	public OID getOid() {
		return oid;
	}
	public boolean isIdentical(E that) {
		boolean isIdentical = false;
		if (this.isPersistent()) {
			if (that != null) {
				if (this.getOid().equals(that.getOid())) {
					isIdentical = true;
				}
			}
		}
		return isIdentical;
	}

	public boolean isPersistent() {
		return this.getOid() != null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public E clone() {
		E clone;
		try {
			clone = (E) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("Is not possible",e);
		}
		return clone;
	}

}