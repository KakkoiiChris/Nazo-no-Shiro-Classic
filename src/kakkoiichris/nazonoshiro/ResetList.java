// Christian Alexander, 12/3/2022
package kakkoiichris.nazonoshiro;

import java.util.*;

public class ResetList<X> implements List<X>, Resettable {
    private final List<X> current = new ArrayList<>();
    private final List<X> previous = new ArrayList<>();
    
    private boolean changed;
    
    public boolean hasChanged() {
        return changed;
    }
    
    @Override
    public int size() {
        return current.size();
    }
    
    @Override
    public boolean isEmpty() {
        return current.isEmpty();
    }
    
    @Override
    public boolean contains(Object o) {
        return current.contains(o);
    }
    
    @Override
    public Iterator<X> iterator() {
        return current.iterator();
    }
    
    @Override
    public Object[] toArray() {
        return current.toArray();
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        return current.toArray(a);
    }
    
    @Override
    public boolean add(X x) {
        changed = true;
        
        return current.add(x);
    }
    
    @Override
    public boolean remove(Object o) {
        changed = true;
        
        return current.remove(o);
    }
    
    @SuppressWarnings("SlowListContainsAll")
    @Override
    public boolean containsAll(Collection<?> c) {
        return current.containsAll(c);
    }
    
    @Override
    public boolean addAll(Collection<? extends X> c) {
        changed = true;
        
        return current.addAll(c);
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends X> c) {
        changed = true;
        
        return current.addAll(index, c);
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        changed = true;
        
        return current.removeAll(c);
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        changed = true;
        
        return current.retainAll(c);
    }
    
    @Override
    public void clear() {
        changed = true;
        
        current.clear();
    }
    
    @Override
    public X get(int index) {
        return current.get(index);
    }
    
    @Override
    public X set(int index, X element) {
        changed = true;
        
        return current.set(index, element);
    }
    
    @Override
    public void add(int index, X element) {
        changed = true;
        
        current.add(index, element);
    }
    
    @Override
    public X remove(int index) {
        changed = true;
        
        return current.remove(index);
    }
    
    @Override
    public int indexOf(Object o) {
        return current.indexOf(o);
    }
    
    @Override
    public int lastIndexOf(Object o) {
        return current.lastIndexOf(o);
    }
    
    @Override
    public ListIterator<X> listIterator() {
        return current.listIterator();
    }
    
    @Override
    public ListIterator<X> listIterator(int index) {
        return current.listIterator(index);
    }
    
    @Override
    public List<X> subList(int fromIndex, int toIndex) {
        return current.subList(fromIndex, toIndex);
    }
    
    @Override
    public void storeState() {
        previous.clear();
        previous.addAll(current);
    }
    
    @Override
    public void resetState() {
        current.clear();
        current.addAll(previous);
    }
}
