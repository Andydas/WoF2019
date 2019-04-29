/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author drzik2
 */
public class List<T> implements Iterable<T> {
    
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void add(T value) {
        if (head == null) {
            this.head = new Node<T>(null, null, value);
            this.tail = this.head;
        } else if (head != null) {
            Node <T> newTail = new Node<T>(this.tail, null, value);
            this.tail.setRight(newTail);
            this.tail = newTail;
        }
        ++this.size; 
    }
    
    public void addStart(T value) {
        if (head == null) {
            this.head = new Node<T>(null, null, value);
            this.tail = this.head;
        } else if (head != null) {
            Node <T> newStart = new Node<T>(null, this.head, value);
            this.head.setRight(newStart);
            this.head = newStart;
        }
        ++this.size; 
    }
    
    public boolean add(T value, int index) {
        return false;
    }
    
    public T remove(int index) {
        Node<T> node = this.get(index);
        if (node == null) {
            return null;
        }
        this.remove(node);
        return node.getValue();
    }
    
    public boolean remove(T value) {
        Node<T> temp = this.head;
        while (temp != null) {
            if (temp.getValue().equals(value))
                break;
            temp = temp.getRight();
            
        }
        if (temp == null)
        return false;
        
        this.remove(temp);
        return true;
    }
    
    public boolean remove(Node<T> node) {
        //je chvost
        if (node == this.tail) {
            Node<T> left = node.getLeft();
            if (left == null) {
                this.head = null;
                this.tail = null;
            } else {
                left.setRight(null);
                this.tail = left;
            }
        //je hlava
        } else if (node == this.head) {
            Node<T> right = node.getRight();
            //vymaze sa hlava
            right.setLeft(null);
            //nastavi sa nova 
            this.head = right;            
        //je v stede
        } else {
            Node<T> left = node.getLeft();
            Node<T> right = node.getRight();
            left.setRight(right);
            right.setLeft(left);
        }
        --this.size;
        return true;
    }
    
    public Node<T> get(int index) {
        if (index >= this.size) {
            return null;
        }
        Node<T> temp = this.head;
        int i = 0;
        while (i < index) {
            temp = temp.getRight();
            ++i;
        }
        return temp;
    }
    
    public T getValues(int index) {
        Node<T> node = this.get(index);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }
    
    public void set(T value, int index) {
        Node<T> node = this.get(index);
        if (node == null) {
            return;
        }
        node.setValue(value);
    }

    public int size() {
        return size;
    }
    
    public void clear() {
        Node<T> temp = this.head;
        while (temp!= null) {
            temp.setLeft(null);
            temp = temp.getRight();
        }
        this.head = null;
        this.tail = null;
    }
    
    public static void main(String[] args) {
        List<Integer> zoznamCisel = new List<Integer>();
        zoznamCisel.add(5);
        zoznamCisel.add(10);
        for (Integer integer : zoznamCisel) {
            System.out.print(integer + " ");
        }
        //?/?????????
        //for (Iterator<T> iter = zoznamCisel.iterator(); iter.hasNext();) {
        //    Integer value = iter.next();
        //}
        assert zoznamCisel.size() == 2;
        zoznamCisel.remove(5);
        zoznamCisel.remove(new Integer(10)); //toto ak chcem vymazat podla value
        assert zoznamCisel.size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(this.head, this);
        
    }
    
    class ListIterator implements Iterator<T> {

        private Node<T> current;
        private List<T> parent;

        public ListIterator(Node<T> current, List<T> parent) {
            this.current = current;
            this.parent = parent;
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = this.current.getValue();
            this.current = this.current.getRight();
            return value;
        }

        @Override
        public void remove() {
            this.parent.remove(current);
        }
        
    }
}
