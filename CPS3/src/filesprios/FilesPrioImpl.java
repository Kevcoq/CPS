package filesprios;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FilesPrioImpl<T> implements IFilesPrio<T> {

	private Set<Integer> actives;
	private Map<Integer, T> map;

	@Override
	public int size() {
		return actives.size();
	}

	@Override
	public boolean empty() {
		return actives.isEmpty();
	}

	@Override
	public Set<Integer> activePrios() {
		return actives;
	}

	@Override
	public boolean isActivePrio(int n) {
		return actives.contains(n);
	}

	@Override
	public int maxPrio() {
		return Collections.max(actives);
	}

	@Override
	// TODO
	public int sizePrio(int n) {
		return 0;
	}

	@Override
	public T getPrio(int n) {
		return map.get(n);
	}

	@Override
	public T get() {
		return map.get(maxPrio());
	}

	@Override
	public T getElem(int i, int k) {
		T elt = map.get(i);
		if (sizePrio(i) > k && k > 0) {
			return elt;
		}
		return null;
	}

	@Override
	public void init() {
		map = new HashMap<>();
		actives = new HashSet<>();
	}

	@Override
	public void putPrio(int i, T e) {
		actives.add(i);
		map.put(i, e);
	}

	@Override
	public void put(T e) {
		putPrio(maxPrio(), e);
	}

	@Override
	public void removePrio(int i) {
		actives.remove(i);
		map.remove(i);
	}

	@Override
	public void remove() {
		removePrio(maxPrio());
	}

}
