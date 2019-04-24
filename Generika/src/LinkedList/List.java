/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

import java.util.HashSet;

/**
 *
 * @author drzik2
 */
public class List<T> {
    
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
        T value = node.getValue();
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
        return value;
    }
    
    public boolean remove(T value) {
        Node<T> temp = this.head;
        while (temp != null) {
            if (temp.getValue() == value)
                break;
            temp = temp.getRight();
            
        }
        return false;
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

    public int getSize() {
        return size;
    }
    
    
}
