package implem;

import services.PositionService;

public class Position implements PositionService {
	private int x, y, z;
	private boolean dirG;

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public int z() {
		return z;
	}

	@Override
	public boolean dirG() {
		return dirG;
	}

	@Override
	public boolean equals(PositionService p) {
		return x == p.x() && y == p.y() && z == p.z();
	}

	@Override
	public boolean equals(int i, int j, int k) {
		return x == i && y == j && z == k;
	}

	@Override
	public void init(int i, int j, int k, boolean d) {
		x = i;
		y = j;
		z = k;
		dirG = d;
	}

	@Override
	public void setX(int n) {
		x = n;
	}

	@Override
	public void setY(int n) {
		y = n;
	}

	@Override
	public void setZ(int n) {
		z = n;
	}

	@Override
	public void setDir(boolean d) {
		dirG = d;
	}

	@Override
	public void set(int i, int j, int k) {
		setX(i);
		setY(j);
		setZ(k);
	}

	@Override
	public void set(PositionService pos) {
		set(pos.x(), pos.y(), pos.z());
	}

	public String toString() {
		return "{ " + x + " ; " + y + "; " + z + " }";
	}
}
