package stack;

import java.util.Objects;

/**
 * 栈
 * 先进后出
 * 采用流接口(fluent interface)模式设计
 */
public class Stack<T> {

    private class Node<ST> {

        private ST data;
        private Node<ST> previous;

        private Node(ST data) {

            this.data = data;
            this.previous = null;
        }

        private void setData(ST data) {

            this.data = data;
        }

        private ST getData() {

            return data;
        }

        private Node<ST> getPrevious() {

            return previous;
        }

        private void setPrevious(Node<ST> previous) {

            this.previous = previous;
        }
    }

    private int size;
    private Node<T> top;

    public Stack() {

        this.size = 0;
        this.top = null;
    }

    public Stack(T[] dataList) {

        for (T i : dataList) {
    
            Node<T> newNode = new Node<>(i);
            newNode.setPrevious(this.top);
            this.top = newNode;
            this.size++;
        }
    }

    public Stack push(T data) {

        Node<T> newNode = new Node<>(data);
        newNode.setPrevious(this.top);
        this.top = newNode;
        this.size++;

        return this;
    }

    public T pop() {

        Node<T> oldTop = this.top;
        this.top = this.top.previous;
        this.size--;

        return oldTop.getData();
    }

    public T view() {

        if (Objects.isNull(this.top)) {

            return null;
        }
        return this.top.getData();
    }

    public Stack reverse() {

        Node<T> n1 = this.top;
        Node<T> n2 = n1.getPrevious();
        Node<T> n3 = n2.getPrevious();

        while (true) {

            n2.setPrevious(n1);
            n1 = n2;
            if (Objects.isNull(n2 = n3)) {

                break;
            }
            n3 = n3.getPrevious();
        }
        this.top = n1;
        return this;
    }

    public void destroy() throws IllegalArgumentException {

        while (this.size > 0) {

            if (this.top == null) {

                throw new IllegalArgumentException("out of bound");
            }
            this.top = this.top.getPrevious();
            this.size--;
        }
    }

    public int getSize() {

        return size;
    }
}
