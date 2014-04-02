package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.BlocService;
import contracts.base.PreconditionError;
import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

public abstract class AbstractTestBloc {
	private BlocService bloc;

	/**
	 * @return the bloc
	 */
	public final BlocService getBloc() {
		return bloc;
	}

	/**
	 * @param bloc
	 *            the bloc to set
	 */
	public final void setBloc(BlocService bloc) {
		this.bloc = bloc;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		bloc = null;
	}

	// ///////////////////////////
	// ///////// PRE /////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.RIEN);
		Assert.assertTrue(true);
	}

	@Test
	public void testInit2() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.DIXDOLLAR);
		Assert.assertTrue(true);
	}

	//
	//
	//
	// Ramasser
	@Test
	public void testRamasser() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.DIXDOLLAR);
		bloc.ramasserTresor();
		Assert.assertTrue(true);
	}

	@Test
	public void testRamasserFail() {
		try {
			bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.RIEN);
			bloc.ramasserTresor();
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	// Deposer
	@Test
	public void testDeposer() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.RIEN);
		bloc.deposerTresor(TYPE_Tresor.DIXDOLLAR);
		Assert.assertTrue(true);
	}

	@Test
	public void testDeposerFail() {
		try {
			bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.DIXDOLLAR);
			bloc.deposerTresor(TYPE_Tresor.CHAINEVELO);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// ///////////////////////////
	// ///////// POST ////////////
	public boolean checkInvariant() {
		if (bloc.aTresor())
			return bloc.typeTresor() != TYPE_Tresor.RIEN;
		return bloc.typeTresor() == TYPE_Tresor.RIEN;
	}

	@Test
	public void testPostInit() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.RIEN);
		Assert.assertTrue(checkInvariant() && bloc.typeBloc() == TYPE_Bloc.VIDE
				&& !bloc.aTresor());
	}

	@Test
	public void testPostInit2() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.DIXDOLLAR);
		Assert.assertTrue(checkInvariant() && bloc.typeBloc() == TYPE_Bloc.VIDE
				&& bloc.aTresor());
	}

	@Test
	public void testPostRamasser() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.DIXDOLLAR);
		bloc.ramasserTresor();
		Assert.assertTrue(checkInvariant() && !bloc.aTresor());
	}

	@Test
	public void testPostDeposer() {
		bloc.init(TYPE_Bloc.VIDE, TYPE_Tresor.RIEN);
		bloc.deposerTresor(TYPE_Tresor.DIXDOLLAR);
		Assert.assertTrue(checkInvariant() && bloc.aTresor());
	}

}
