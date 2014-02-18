package filesprios;

import java.util.Set;

public abstract class FilePrioDecorator<T> implements IFilesPrio<T> {

	private IFilesPrio<T> delegate;

	protected FilePrioDecorator(IFilesPrio<T> d) {
		delegate = d;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return delegate.size();
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return delegate.empty();
	}

	@Override
	public Set<Integer> activePrios() {
		// TODO Auto-generated method stub
		return delegate.activePrios();
	}

	@Override
	public boolean isActivePrio(int n) {
		// TODO Auto-generated method stub
		return delegate.isActivePrio(n);
	}

	@Override
	public int maxPrio() {
		// TODO Auto-generated method stub
		return delegate.maxPrio();
	}

	@Override
	public int sizePrio(int n) {
		// TODO Auto-generated method stub
		return delegate.sizePrio(n);
	}

	@Override
	public T getPrio(int n) {
		// TODO Auto-generated method stub
		return delegate.getPrio(n);
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return delegate.get();
	}

	@Override
	public T getElem(int i, int k) {
		// TODO Auto-generated method stub
		return delegate.getElem(i, k);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		delegate.init();
	}

	@Override
	public void putPrio(int i, T e) {
		// TODO Auto-generated method stub
		delegate.putPrio(i, e);
	}

	@Override
	public void put(T e) {
		// TODO Auto-generated method stub
		delegate.put(e);
	}

	@Override
	public void removePrio(int i) {
		// TODO Auto-generated method stub
		delegate.removePrio(i);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		delegate.remove();
	}

}
