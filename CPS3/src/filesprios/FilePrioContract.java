package filesprios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import filesprios.exc.InvariantError;
import filesprios.exc.PostConditionError;
import filesprios.exc.PreConditionError;

public class FilePrioContract<T> extends FilePrioDecorator<T> {

	protected FilePrioContract(IFilesPrio<T> d) {
		super(d);
	}

	public void checkInvariant() throws InvariantError {
		// \inv : (min) size() == somme des elements de activePrios()
		int s = 0;
		for (Integer e : activePrios()) {
			s += e.intValue();
		}
		if (!(size() == s)) {
			throw new InvariantError("1");
		}

		// \inv : (min) empty() : (size() == 0)
		if (!(empty()) && size() == 0) {
			throw new InvariantError("2");
		}

		// \inv : (min) isActivePrio(i) == \exists i \in activePrios()
		for (int i = 0; i < maxPrio(); i++) {
			if (isActivePrio(i) != activePrios().contains(i)) {
				throw new InvariantError("3");
			}
		}

		// \inv : (min) maxPrio() == max(activePrios()) \with max(E) := \exists
		// x \in E \\union {0} -> \forall y \in E, x >= y
		if (maxPrio() != Collections.max(activePrios())) {
			throw new InvariantError("4");
		}

		// \inv : (min) getPrio(i) == getElem(i, 1)
		for (int i = 0; i < maxPrio(); i++) {
			if (getPrio(i) != getElem(i, 1)) {
				throw new InvariantError("5");
			}
		}

		// \inv : (min) get() == getPrio(maxPrio())
		if (get() != getPrio(maxPrio())) {
			throw new InvariantError("6");
		}

		// \inv : (utile) \forall i \in activePrios(), sizePrio(i) > 0
		for (Integer i : activePrios()) {
			if (sizePrio(i.intValue()) <= 0) {
				throw new InvariantError("7");
			}
		}

		// \inv : (utile) \forall i \not \in activePrios(), sizePrio(i) == 0
		List<Integer> l = new ArrayList<>();
		for (int n = 0; n < maxPrio(); n++) {
			l.add(n);
		}
		for (Integer i : activePrios()) {
			l.remove(i);
		}
		for (Integer i : l) {
			if (sizePrio(i.intValue()) != 0) {
				throw new InvariantError("8");
			}
		}

		// \inv : (utile) \forall i \in activePrios(), \forall k \in
		// [1..sizePrio(i)], getElem(i, k) != null
		for (Integer i : activePrios()) {
			for (int k = 1; k < sizePrio(i); k++) {
				if (getElem(i, k) == null) {
					throw new InvariantError("9");
				}
			}
		}
	}

	@Override
	public void init() {
		checkInvariant();
		super.init();
		checkInvariant();
		// \post : size() == 0
		if (!(size() == 0)) {
			throw new PostConditionError("init");
		}
	}

	@Override
	public void putPrio(int i, T e) {
		// \pre : i >= 0 && e != null
		if (i < 0 || e == null) {
			throw new PreConditionError("putPrio");
		}

		checkInvariant();

		boolean b = isActivePrio(i);
		int size = size();

		Set<Integer> tmpSet = activePrios();
		List<Integer> listSizePrio = new ArrayList<>();
		for (Integer j : tmpSet) {
			listSizePrio.add(sizePrio(j));
		}

		super.putPrio(i, e);
		checkInvariant();

		// \post : isActivePrio(i) => activePrios() == activePrios()@pre
		if (b && !isActivePrio(i)) {
			throw new PostConditionError("putPrio 1");
		}

		// \post : \not isActivePrio(i) => activePrio() == activePrio()@pre
		// \\union {i}
		if (!b && isActivePrio(i)) {
			throw new PostConditionError("putPrio 2");
		}

		// \post : sizePrio(i) == sizePrio(i)@pre + 1
		if (size != size()) {
			throw new PostConditionError("putPrio 3");
		}

		// \post : \forall j \in activePrios() \{i}, sizePrio(j) ==
		// sizePrio(j)@pre
		int jtmp = 0;
		for (Integer j : tmpSet) {
			if (sizePrio(j) != listSizePrio.get(jtmp)) {
				throw new PostConditionError("putPrio 4");
			}
			jtmp++;
		}

		// \post : getPrio(i) == e
		if (e != getPrio(i)) {
			throw new PostConditionError("putPrio 5");
		}

		// \post : \forall k \in [2..sizePrio(i)@pre + 1], getElem(i, k) ==
		// getElem(i, k-1)@pre
		// TODO

		// \post : \forall j \in activePrios()@pre \{i}, \forall k \in
		// [1..sizePrio(j)@pre], getElem(j, k) == getElem(j, k)@pre
		// TODO
	}

	@Override
	public void put(T e) {
		// \pre : e != null
		if (e == null) {
			throw new PreConditionError("put");
		}

		checkInvariant();
		super.put(e);
		checkInvariant();

		// \def : put(e) == putPrio(e, maxPrio())
		// TODO
	}

	@Override
	public void removePrio(int i) {
		// \pre : sizePrio(i) > 0
		if (sizePrio(i) < 0) {
			throw new PreConditionError("removePrio");
		}

		int size = sizePrio(i);
		Set<Integer> set = activePrios();

		checkInvariant();
		super.removePrio(i);
		checkInvariant();

		// \post : sizePrio(i)@pre > 1 => activePrios() = activePrios()@pre
		// TODO

		// \post : sizePrio(i)@pre = 1 => activePrios() = activePrios()@pre \{i}
		if (size == 1 && set.size() == activePrios().size() - 1) {
			throw new PostConditionError("removePrio 2");
		}

		// \post : sizePrio(i) = sizePrio(i)@pre - 1
		if (size - 1 != sizePrio(i)) {
			throw new PostConditionError("removePrio 3");
		}

		// \post : \forall j \in activePrios()@pre \{i}, sizePrio(j) =
		// sizePrio(j)@pre
		// TODO

		// \post : \forall k \in [1..sizePrio(i)@pre -1], getElem(i,k) =
		// getElem(i,k)@pre
		// TODO

		// \post : \forall i \in activePrios()@pre \{i}, \forall k \in
		// [1..sizePrio(j)@pre], getElem(j,k) = getElem(j,k)@pre
		// TODO
	}

	public void remove() {
		// \pre : size(i) > 0
		if (size() <= 0) {
			throw new PreConditionError("remove");
		}

		checkInvariant();
		super.remove();
		checkInvariant();

		// \def : remove() == removePrio(maxPrio())
		// TODO
	}
}
