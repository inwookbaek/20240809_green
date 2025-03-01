package ex06_inheritance;

public class StrorageImpl<T> implements Storage<T> {

	private T[] array;
	
	public StrorageImpl(int capacity) {
		this.array = (T[]) new Object[capacity];
	}
	
	@Override
	public void add(T item, int index) {
		array[index] = item;
	}

	@Override
	public T get(int index) {
		return array[index];
	}

}
