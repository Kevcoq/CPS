package tests;

import implem.ObjetEquipable;
import contracts.ObjetEquipableContract;
import tests.abstrait.AbstractTestObjetEquipable;

public class TestObjetEquipable extends AbstractTestObjetEquipable {

	@Override
	public void beforeTests() {
		setObjet(new ObjetEquipableContract(new ObjetEquipable()));
		setChose(new ObjetEquipableContract(new ObjetEquipable()));
	}

}
