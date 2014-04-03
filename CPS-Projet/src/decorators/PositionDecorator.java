package decorators;

import services.PositionService;

public abstract class PositionDecorator implements PositionService {
	private PositionService pos;

	public PositionDecorator(PositionService pos) {
		super();
		this.pos = pos;
	}

	/**
	 * @param p
	 * @return
	 * @see services.PositionService#collision(services.PositionService)
	 */
	public boolean collision(PositionService p) {
		return pos.collision(p);
	}

	/**
	 * @param pos
	 * @see services.PositionService#set(services.PositionService)
	 */
	public void set(PositionService pos2) {
		pos.set(pos2);
	}

	/**
	 * @return
	 * @see services.PositionService#x()
	 */
	public int x() {
		return pos.x();
	}

	/**
	 * @return
	 * @see services.PositionService#y()
	 */
	public int y() {
		return pos.y();
	}

	/**
	 * @return
	 * @see services.PositionService#z()
	 */
	public int z() {
		return pos.z();
	}

	/**
	 * @return
	 * @see services.PositionService#dirG()
	 */
	public boolean dirG() {
		return pos.dirG();
	}

	/**
	 * @param p
	 * @return
	 * @see services.PositionService#equals(services.PositionService)
	 */
	public boolean equals(PositionService p) {
		return pos.equals(p);
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 * @see services.PositionService#equals(int, int, int)
	 */
	public boolean equals(int i, int j, int k) {
		return pos.equals(i, j, k);
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @param d
	 * @see services.PositionService#init(int, int, int, boolean)
	 */
	public void init(int i, int j, int k, boolean d) {
		pos.init(i, j, k, d);
	}

	/**
	 * @param n
	 * @see services.PositionService#setX(int)
	 */
	public void setX(int n) {
		pos.setX(n);
	}

	/**
	 * @param n
	 * @see services.PositionService#setY(int)
	 */
	public void setY(int n) {
		pos.setY(n);
	}

	/**
	 * @param n
	 * @see services.PositionService#setZ(int)
	 */
	public void setZ(int n) {
		pos.setZ(n);
	}

	/**
	 * @param d
	 * @see services.PositionService#setDir(boolean)
	 */
	public void setDir(boolean d) {
		pos.setDir(d);
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @see services.PositionService#set(int, int, int)
	 */
	public void set(int i, int j, int k) {
		pos.set(i, j, k);
	}

	/**
	 * @return
	 * @see services.PositionService#toString()
	 */
	public String toString() {
		return pos.toString();
	}

}
