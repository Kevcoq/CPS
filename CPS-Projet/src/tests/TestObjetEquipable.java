package tests;

import implem.ObjetEquipable;
import tests.abstrait.AbstractTestObjetEquipable;
import contracts.ObjetEquipableContract;

public class TestObjetEquipable extends AbstractTestObjetEquipable {

	@Override
	public void beforeTests() {
		setObjet(new ObjetEquipableContract(new ObjetEquipable()));
		setChose(new ObjetEquipableContract(new ObjetEquipable()));
	}

}
