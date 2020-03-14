package interfaces;

public interface ReversedList<E> extends Iterable<E> {
  void Add(E element);
  int Size();
  int Capacity();
  E Get(E index);
  void RemoveAt(int index);
}
