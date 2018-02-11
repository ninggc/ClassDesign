package progress.info.operation;

import java.util.ArrayList;

public interface IOperation<T> {
	public ArrayList<T> selectAll();
	
	public T searchById(String id);
	
	public boolean add(T t);
}
