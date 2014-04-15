package services;

/**
 * 
 * @author Kevin & Quentin
 * 
 */
public interface PositionService {

	/* Observators */
	public int x();

	public int y();

	public int z();

	public boolean dirG();

	public boolean equals(PositionService p);

	public boolean equals(int i, int j, int k);

	public boolean collision(PositionService p);

	/* Constructor */
	public void init(int i, int j, int k, boolean d);

	/* Operators */
	public void setX(int n);

	public void setY(int n);

	public void setZ(int n);

	public void setDir(boolean d);

	public void set(int i, int j, int k);

	public void set(PositionService pos);

	public String toString();

	public PositionService clone();
	
	
	
	/* Observations */

	// *** [invariant]
	// **** equals(P, p2) =def (x(P) = x(p2) ∧ y(P) = y(p2) ∧ z(P) = z(p2))
	// **** equals(P, i, j, k) =def (x(P) = i ∧ y(P) = j ∧ z(P) = k)
	// **** collsion(P, p2) =def equals(P, p2) ∨ equals(P, x(p2) - 1, y(p2), z(p2))
	// **** set(P, p2) =def set(P, x(p2), y(p2), z(p2))
	// 
	// *** [init]
	// **** x(init(i, j, k, d)) = i
	// **** y(init(i, j, k, d)) = j
	// **** z(init(i, j, k, d)) = k
	// **** dirG(init(i, j, k, d)) = d
	//
	// *** [setX]
	// **** x(setX(P, n)) = n
	// **** y(setX(P, n)) = y(P)
	// **** z(setX(P, n)) = z(P)
	// **** dirG(setX(P, n)) = dirG(P)
	//
	// *** [setY]
	// **** x(setY(P, n)) = x(P)
	// **** y(setY(P, n)) = n
	// **** z(setY(P, n)) = z(P)
	// **** dirG(setY(P, n)) = dirG(P)
	//
	// *** [setZ]
	// **** x(setZ(P, n)) = x(P)
	// **** y(setZ(P, n)) = y(P)
	// **** z(setZ(P, n)) = n
	// **** dirG(setZ(P, n)) = dirG(P)
	//
	// *** [setDir]
	// **** x(setDir(P, n)) = x(P)
	// **** y(setDir(P, n)) = y(P)
	// **** z(setDir(P, n)) = z(P)
	// **** dirG(setDir(P, n)) = n
	//
	// *** [set]
	// **** x(set(P, i, j, k)) = i
	// **** y(set(P, i, j, k)) = j
	// **** z(set(P, i, j, k)) = k
	// **** dirG(set(P, i, j, k)) = dirG(P)
	
}
