package stack;

import java.util.Objects;

/**
 * 栈
 * 先进后出
 * 采用流接口(fluent interface)模式设计
 */
public class Stack {

    private class Node {

        private Object data;
        private Node previous;

        private Node(Object data) {

            this.data = data;
            this.previous = null;
        }

        private void setData(Object data) {

            this.data = data;
        }

        private Object getData() {

            return data;
        }

        private Node getPrevious() {

            return previous;
        }

        private void setPrevious(Node previous) {

            this.previous = previous;
        }
    }

    private int size;
    private Node top;

    public Stack() {

        this.size = 0;
        this.top = null;
    }

    public Stack(Object[] dataList) {

        for (int i = 0; i < dataList.length; i++) {

            Node newNode = new Node(dataList[i]);
            newNode.setPrevious(this.top);
            this.top = newNode;
            this.size++;
        }
    }

    public Stack push(Object data) {

        Node newNode = new Node(data);
        newNode.setPrevious(this.top);
        this.top = newNode;
        this.size++;

        return this;
    }

    public Object pop() {

        Node oldTop = this.top;
        this.top = this.top.previous;
        this.size--;

        return oldTop.getData();
    }

    public Object view() {

        if (Objects.isNull(this.top)) {

            return null;
        }
        return this.top.getData();
    }

    public Stack reverse() {

        Node n1 = this.top;
        Node n2 = n1.getPrevious();
        Node n3 = n2.getPrevious();

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
