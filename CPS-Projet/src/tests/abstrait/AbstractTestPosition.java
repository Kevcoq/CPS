package tests.abstrait;

import implem.Position;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.PositionService;

public abstract class AbstractTestPosition {
	private PositionService position;

	protected AbstractTestPosition() {
		position = null;
	}

	protected final PositionService getPosition() {
		return position;
	}

	protected final void setPosition(PositionService Position) {
		this.position = Position;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		position = null;
	}

	// //////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		position.init(1, 2, 3, true);
		Assert.assertTrue(true);
	}

	// //////////////////////////////////////
	// /////////////// POST /////////////////
	@Test
	public void testPostInit() {
		position.init(1, 2, 3, true);
		Assert.assertTrue(position.x() == 1 && position.y() == 2
				&& position.z() == 3 && position.dirG());
	}

	@Test
	public void testPostSetX() {
		position.init(1, 2, 3, true);
		position.setX(5);
		Assert.assertTrue(position.x() == 5 && position.y() == 2
				&& position.z() == 3 && position.dirG());
	}

	@Test
	public void testPostSetY() {
		position.init(1, 2, 3, true);
		position.setY(5);
		Assert.assertTrue(position.x() == 1 && position.y() == 5
				&& position.z() == 3 && position.dirG());
	}

	@Test
	public void testPostSetZ() {
		position.init(1, 2, 3, true);
		position.setZ(5);
		Assert.assertTrue(position.x() == 1 && position.y() == 2
				&& position.z() == 5 && position.dirG());
	}

	@Test
	public void testPostSetDir() {
		position.init(1, 2, 3, true);
		position.setDir(false);
		Assert.assertTrue(position.x() == 1 && position.y() == 2
				&& position.z() == 3 && !position.dirG());
	}

	@Test
	public void testPostSet() {
		position.init(1, 2, 3, true);
		position.set(5, 6, 7);
		Assert.assertTrue(position.x() == 5 && position.y() == 6
				&& position.z() == 7 && position.dirG());
	}

	@Test
	public void testPostSet2() {
		position.init(1, 2, 3, true);
		PositionService pos2 = new Position();
		pos2.init(5, 6, 7, false);
		position.set(pos2);
		Assert.assertTrue(position.x() == 5 && position.y() == 6
				&& position.z() == 7 && position.dirG());
	}

	@Test
	public void testPostEqualsTrue() {
		position.init(1, 2, 3, true);
		Assert.assertTrue(position.equals(1, 2, 3));
	}

	@Test
	public void testPostEqualsFalse() {
		position.init(1, 2, 3, true);
		Assert.assertTrue(!position.equals(4, 5, 6));
	}

	@Test
	public void testPostEqualsTrue2() {
		position.init(1, 2, 3, true);
		PositionService pos2 = new Position();
		pos2.init(1, 2, 3, false);
		Assert.assertTrue(position.equals(pos2));
	}

	@Test
	public void testPostEqualsFalse2() {
		position.init(1, 2, 3, true);
		PositionService pos2 = new Position();
		pos2.init(4, 5, 6, false);
		Assert.assertTrue(!position.equals(pos2));
	}
}
