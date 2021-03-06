package linkedList;

import java.util.Objects;

/**
 * 单链表
 * @author DX
 */
public class LinkedList<T> {

    private int size;
    protected Node<T> head, tail;

    /**
     * 内部节点类
     * data 数据
     * next 后继节点
     */
    public class Node<ST> {

        private ST data;
        /**
         * 指向下一个节点对象的引用
         */
        public Node<ST> next;

        public Node (ST data) {

            this.data = data;
            next = null;
        }
    
        public void setData(ST data) {
        
            this.data = data;
        }
    
        public ST getData() {

            return data;
        }
    }

    /**
     * 链表构造函数
     * 设置初始值
     */
    public LinkedList() {

        this.size = 0;
        this.head = null;
    }

    /**
     * 链表添加元素
     * @param data 添加元素的数据
     */
    public void add(T data) {

        Node<T> newNode = new Node<>(data);
        if (this.size == 0) {

            this.head = newNode;
        } else {

            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    /**
     * 在指定结点后插入新结点
     * @param node 指定结点
     * @param data 要插入的数据
     */
    public void insNext(Node<T>node, T data) {

        Node<T> newNode = new Node<>(data);
        /* 当指定结点为null时设置新结点为头结点 */
        if (Objects.isNull(node)) {

            newNode.next = this.head;
            this.head = newNode;
        } else {

            newNode.next = node.next;
            node.next = newNode;
            /* 指定结点的next为null时说明为尾结点, 此时新结点应被设置为尾结点 */
            if (Objects.isNull(newNode.next)) {

                this.tail = newNode;
            }
        }
        this.size++;
    }

    /**
     * 适用性强的remNext方法
     * @param node 指定结点
     * @return data 导出数据
     * @throws IllegalArgumentException 避免删除null结点
     */
    public T remNext(Node<T> node) throws IllegalArgumentException {

        T data;
        // 链表长度检测
        if (this.size == 0) {

            throw new IllegalArgumentException("Empty linked list");
        }
        if (Objects.isNull(node)) {

            data = this.head.data;
            this.head = this.head.next;
            if (this.size == 1) {

                this.tail = null;
            }

        } else {

            // 尾结点检测
            if (Objects.isNull(node.next)) {

                throw new IllegalArgumentException("Cannot remove the next Node<T> of tail node");
            }
            data = node.next.data;
            node.next = node.next.next;
        }
        return data;
    }

    /**
     * 检测是否为空
     * @return 判断是否为空的结果
     */
    public boolean isEmpty() {

        return Objects.isNull(this.head);
    }

    /**
     * 寻找元素在链表中的位置
     * @param node 要寻找的元素
     * @return int类型 元素位置
     */
    public int find(Node<T>node) {

        Node<T> currentNode = this.head;
        int i = 0;
        while (!Objects.isNull(currentNode) && !currentNode.equals(node)) {

            currentNode = currentNode.next;
            i++;
        }
        if (Objects.isNull(currentNode)) {

            return -1;
        } else {

            return i;
        }
    }

    /**
     * 链表反转
     * @return 反转后链表(原链表)
     */
    public LinkedList reverse() {

        Node<T> node1 = this.head;
        Node<T> node2 = node1.next;
        Node<T> node3 = node2.next;
        this.head = this.tail;
        this.tail = node1;
        while (true) {

            node2.next = node1;
            node1 = node2;
            if (Objects.isNull(node2 = node3)) {

                break;
            }
            node3 = node3.next;
        }
        
        return this;
    }

    /**
     * 获取指定位置元素
     * @param index 节点位置索引
     * @return Node<T> 指定位置的结点
     */
    public Node<T> getNode(int index) throws IllegalArgumentException {

        Node<T> currentNode = this.head;
        if (index == -1) {

            return this.tail;
        }
        for (int i = 0; i < index && !Objects.isNull(currentNode); i++) {


            currentNode = currentNode.next;
        }
        if (Objects.isNull(currentNode)) {

            throw new IllegalArgumentException("Over the bound of list");
        }
        return currentNode;
    }

    public int getSize() {

        return size;
    }
    
    public Node<T> getHead() {
        
        return head;
    }
    
    public Node<T> getTail() {
        
        return tail;
    }
}
