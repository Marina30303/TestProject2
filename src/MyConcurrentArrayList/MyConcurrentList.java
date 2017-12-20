package MyConcurrentArrayList;



import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by Марiна on 07.04.2017.
 */
public class MyConcurrentList<T> implements List<T> {



   private T[] arrayElements = (T[])new Object[10000000];



    @Override
    public int size() {
        synchronized (arrayElements) {

            int sizeOfArr = 0;
            for (Object e : arrayElements) {
                if (e != null)
                    sizeOfArr++;
            }
            return sizeOfArr;
        }
    }

    @Override
    public boolean isEmpty() {

        synchronized (arrayElements) {
            return size() == 0;
        }
    }
    @Override
    public boolean add(T t) {
        synchronized (arrayElements) {

            if (size() == arrayElements.length) {
                T[] arrayElementsBigger =
                        (T[]) new Object[arrayElements.length * 2];
                for (int i = 0; i < size(); i++) {
                    arrayElementsBigger[i] = arrayElements[i];
                }
                arrayElements = arrayElementsBigger;
            }

            arrayElements[size()] = t;
            return true;
        }

    }

    @Override
    public T get(int index) {
        synchronized (arrayElements) {

            if (index > arrayElements.length - 1) throw new IndexOutOfBoundsException("size = " + size());
            return arrayElements[index];
        }
    }

    @Override
    public T set(int index, T element) {
        synchronized (arrayElements) {
            if (index > arrayElements.length - 1) throw new IndexOutOfBoundsException("size = " + size());
            return arrayElements[index] = element;
        }
    }
    @Override
    public T remove(int index) {
        synchronized (arrayElements) {
            if (index >= size() || index < 0) throw new IndexOutOfBoundsException("size = " + size());
            T t = arrayElements[index];
            for (int i = index; i <= size(); )
                arrayElements[i] = arrayElements[++i];
            arrayElements[size()] = null;
            return t;
        }
    }
    @Override
    public boolean contains(Object o) {
        synchronized (arrayElements) {
            for (T e : arrayElements
                    ) {
                if (o.equals(e))
                    return true;
            }
            return false;
        }
    }



    @Override
    public boolean remove(Object o) {
        synchronized (arrayElements) {
            if (!contains(o))
                throw new NullPointerException("Null");
            T t = arrayElements[indexOf(o)];
            for (int i = indexOf(o); i <= size(); )
                arrayElements[i] = arrayElements[++i];
            arrayElements[size()] = null;
            System.out.println("df"  );
            return true;
        }

    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        synchronized (arrayElements) {
            Objects.requireNonNull(c);

            if (arrayElements.length - size() < c.size()) {
                T[] arrayElementsBigger = (T[]) new Object[10000000 + c.size()];
                for (int i = 0; i < size(); i++) {
                    arrayElementsBigger[i] = arrayElements[i];
                }
                arrayElements = arrayElementsBigger;
            }

            Object[] objects = c.toArray();
            int ourSize = size();
            for (int i = 0; i < c.size(); i++)
                arrayElements[ourSize++] = (T) objects[i];
            return true;
        }
    }


    @Override
    public boolean addAll(@NotNull int index, Collection<? extends T> c) {
        synchronized (arrayElements) {
            if (index > size() || index < 0) throw new IndexOutOfBoundsException();
            Objects.requireNonNull(c);

            if (arrayElements.length - size() - index < c.size()) {
                T[] arrayElementsBigger = (T[]) new Object[10000000 + c.size()];
                for (int i = 0; i < size(); i++) {
                    arrayElementsBigger[i] = arrayElements[i];
                }
                arrayElements = arrayElementsBigger;
            }
            Object[] objects = c.toArray();
            int ourSize = size();
            List<T> mySubList = subList(index, size());
            int newArraySize = objects.length;
            for (int i = 0; i < objects.length; i++)
                arrayElements[index++] = (T) objects[i];
            addAll(mySubList);
            return true;
        }
    }

    @Override
    public void clear() {
        synchronized (arrayElements) {
            for (T e : arrayElements
                    ) {
                e = null;
            }
        }
    }


    @Override
    public void add(int index, T element) {
        synchronized (arrayElements) {

            if (index > size() || index < 0) throw new IndexOutOfBoundsException();
            for (int i = size() + 1; i > index; )
                arrayElements[i] = arrayElements[--i];
            arrayElements[index] = element;
        }
    }


    @Override
    public int indexOf(Object o) {
        synchronized (arrayElements) {

            for (int i = 0; i < arrayElements.length; i++) {
                if (o.equals(arrayElements[i]))
                  return i;
            }
            return 0;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        synchronized (arrayElements) {

            for (int i = size(); i >= 0; i--) {
                if (o.equals(arrayElements[i]))
                    return i;
            }
            return -1;
        }
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        synchronized (arrayElements) {
            List<T> myList = new ArrayList<T>();
            if (fromIndex < 0 || fromIndex > size() || toIndex < 0 || toIndex > size() || toIndex - fromIndex < 0)
                throw new IndexOutOfBoundsException();
            for (int i = fromIndex; i < toIndex; i++)
                myList.add(arrayElements[i]);
            return myList;
        }
    }

    @Override
    public boolean retainAll(Collection c) {
        synchronized (arrayElements) {

            boolean isPresent;
            for(int i = 0; i<size();i++) {
                isPresent=false;
                for (Object e : c) {
                    if (e.equals(arrayElements[i]))
                    { isPresent = true;
                    System.out.println("equals " + e);}
                }
            if (isPresent==false) {
                    remove(arrayElements[i]);
                    System.out.println("removed " +arrayElements[i]);};
            }
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection c) {
        synchronized (arrayElements) {
            for (Object e : c)
                if (contains(e))
                    remove(e);

            return true;
        }
    }

    @Override
    public boolean containsAll(Collection c) {
        synchronized (arrayElements) {
            for (Object e : c)
                if (!contains(e))
                    return false;
            return true;
        }

    }

    @Override
    public <T> T[] toArray(T[] a) {

        synchronized (arrayElements) {

            if (a.length < size())
                return (T[]) Arrays.copyOf(arrayElements, size(), a.getClass());

            for (int i = 0; i < size(); i++) a[i] = (T) arrayElements[i];
            return a;
        }


    }
    @Override
    public Object[] toArray() {
        synchronized (arrayElements) {
            Object[] arr = new Object[size()];
            for (int i = 0; i < size(); i++)
                arr[i] = arrayElements[i];
            return arr;
        }

    }
    //----------------------------------------------------------
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++   Iterator   ++++++++++++++++++++++++++
    //--------------------------------------------------------


    @Override
    public Iterator iterator() {
        synchronized (arrayElements) {
            return new MyIterator();
        }
        }

    @Override
    public ListIterator listIterator() {
        synchronized (arrayElements) {
            return new MyListIterator();
        }
    }

    @Override
    public ListIterator listIterator(int index) {
        synchronized (arrayElements) {

            return new MyListIterator(index);
        }
    }

    //===========================================================================================
    //Iteratot implementation

    public class MyIterator implements Iterator<T> {
        protected volatile int cursor = -1;

        @Override
        public boolean hasNext() {
            synchronized (arrayElements) {
                return arrayElements[cursor + 1] != null;
            }
        }

        @Override
        public T next() {
            synchronized (arrayElements) {
                if (!hasNext()) throw new IndexOutOfBoundsException("Exception");
                return (T) arrayElements[++cursor];
            }
        }

        public void remove() {
            synchronized (arrayElements) {
                for (int i = cursor; i < arrayElements.length - 1; )
                    arrayElements[i] = arrayElements[++i];
            }
        }
    }

    public class MyListIterator extends MyIterator implements ListIterator<T>
    {
        public MyListIterator()
        {}

        public MyListIterator(int index)
        {

            if (index<0 || index>size())
                throw new IndexOutOfBoundsException("size is = " + size());
            super.cursor = index;
        }
        @Override
        public boolean hasPrevious() {

            synchronized (arrayElements) {
                return cursor > 0;
            }
        }

        @Override
        public T previous() {
            synchronized (arrayElements) {
                return hasPrevious() ? arrayElements[--cursor] : null;
            }
        }

        @Override
        public int nextIndex() {
            synchronized (arrayElements) {
                return hasNext() ? ++super.cursor : 0;
            }
        }

        @Override
        public int previousIndex() {
            synchronized (arrayElements) {
                return hasPrevious() ? --super.cursor : 0;
            }
        }

        @Override
        public void set(T t) {
            synchronized (arrayElements) {
                arrayElements[super.cursor] = t;
            }

        }

        @Override
        public void add(T t) {
            synchronized (arrayElements) {
                arrayElements[super.cursor] = t;
            }

        }
    }


}

