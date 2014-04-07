package contracts;

import services.PositionService;
import contracts.base.PostconditionError;
import decorators.PositionDecorator;

public class PositionContract extends PositionDecorator {

	public PositionContract(PositionService pos) {
		super(pos);
	}

	public void init(int i, int j, int k, boolean d) {
		super.init(i, j, k, d);

		if (!(x() == i && y() == j && z() == k && dirG() == d))
			throw new PostconditionError("Position -> init");
	}

	public void setX(int n) {
		int y_atpre = y(), z_atpre = z();
		boolean d_atpre = dirG();
		super.setX(n);

		if (!(x() == n && y() == y_atpre && z() == z_atpre && dirG() == d_atpre))
			throw new PostconditionError("Position -> setX");
	}

	public void setY(int n) {
		int x_atpre = x(), z_atpre = z();
		boolean d_atpre = dirG();
		super.setY(n);

		if (!(x() == x_atpre && y() == n && z() == z_atpre && dirG() == d_atpre))
			throw new PostconditionError("Position -> setY");
	}

	public void setZ(int n) {
		int x_atpre = x(), y_atpre = y();
		boolean d_atpre = dirG();
		super.setZ(n);

		if (!(x() == x_atpre && y() == y_atpre && z() == n && dirG() == d_atpre))
			throw new PostconditionError("Position -> setZ");
	}

	public void setDir(boolean d) {
		int x_atpre = x(), y_atpre = y(), z_atpre = z();
		super.setDir(d);

		if (!(x() == x_atpre && y() == y_atpre && z() == z_atpre && dirG() == d))
			throw new PostconditionError("Position -> setDir");
	}

	public void set(int i, int j, int k) {
		boolean d_atpre = dirG();
		super.set(i, j, k);

		if (!(x() == i && y() == j && z() == k && dirG() == d_atpre))
			throw new PostconditionError("Position -> set");
	}

	public void set(PositionService pos2) {
		boolean d_atpre = dirG();
		super.set(pos2);
		if (!(x() == pos2.x() && y() == pos2.y() && z() == pos2.z() && dirG() == d_atpre))
			throw new PostconditionError("Position -> set");
	}
}
