package services;

public interface PositionService {
	public int x();

	public int y();

	public int z();

	public boolean dirG();

	public boolean equals(PositionService p);

	public boolean equals(int i, int j, int k);

	public boolean collision(PositionService p);

	public void init(int i, int j, int k, boolean d);

	public void setX(int n);

	public void setY(int n);

	public void setZ(int n);

	public void setDir(boolean d);

	public void set(int i, int j, int k);

	public void set(PositionService pos);

	public String toString();

	public PositionService clone();
}
