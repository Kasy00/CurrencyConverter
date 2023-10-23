package collections;

public interface ICurrencyCollection<T> {
void add(T item);
T get(int index);
int size();
}
