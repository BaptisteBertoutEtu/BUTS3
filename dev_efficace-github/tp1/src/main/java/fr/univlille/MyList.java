package main.java.fr.univlille;

import java.util.Collection;

public class MyList<E> {
    E data;
    MyList<E> next;

    private MyList(E data, MyList<E> next){
        this.data=data;
        this.next = next;
    }

    public MyList(){
        this(null,null);
    }

    public MyList(Collection<? extends E> c){
        MyList<E> current = this;
        for (E e : c) {
            current.data = e;
            current.next = new MyList<E>();
            current = current.next;
        }
    }

    public boolean isEmpty(){
        return this.next == null;
    }

    public void clear(){
        while (!this.isEmpty()) {
            removeFirst();
        }
    }

    public int size(){
        int i = 0;
        while (!this.isEmpty()) {
            i++;
            removeFirst();
        }
        return i;
    }

    public void removeFirst(){
        this.data=next.data;
        this.next=next.next;
    }

    public String toString(){
        if(this.next.isEmpty()) return this.data.toString();
        return this.data + this.next.toString();
    }

    public E get(int index){
        if (this.isEmpty())
            throw new IndexOutOfBoundsException();
        if(index == 0)
            return this.data;
        return this.next.get(index-1);
    }

    public int indexOf(Object o){
        if(this.equals(o)) return 0;
        return this.next.indexOf(o);
    }

    public boolean contains(Object o){
        if(this.equals(o)) return true;
        return this.next.contains(o);
    }

    public int lastIndexOf(Object o){
        
    }
}