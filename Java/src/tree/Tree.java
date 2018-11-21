package tree;

import common.Callback;
import stack.Stack;

import java.util.Objects;

public class Tree<T> implements TreeInterface<T> {

    protected Node<T> root;
    
    class Node<ST> implements TreeInterface.Node {
        
        private ST data;
        private Node<ST> firstChild, nextSibling;
    
        private Node(ST data) {
            
            this.data = data;
        }
        
        // getter & setter
        public ST getData() {
        
            return data;
        }
        
        public Node<ST> getFirstChild() {
            
            return firstChild;
        }
    
        public Node<ST> getNextSibling() {
        
            return nextSibling;
        }
    
        public void setData(ST data) {
        
            this.data = data;
        }
    
        public void setFirstChild(ST data) {
        
            this.firstChild = new Node<>(data);
        }
    
        public void setNextSibling(ST data) {
        
            this.nextSibling = new Node<>(data);
        }
    }
    
    public Tree(T data) {
        
        this.root = new Node<>(data);
    }
    
    /**
     * 先序遍历算法
     * @param task 对内部执行的回调函数
     */
    @Override // 表示方法重写
    public void preTraversal(Callback task) {
        
        Node<T> node = root;
        Stack<Node<T>> stack = new Stack<>();
        
        // 手动栈
        task.solve(node);
        stack.push(node);
        while (stack.getSize() > 0) {
    
            // 若有子节点则遍历并入栈
            while (!Objects.isNull(node.getFirstChild())) {
        
                node = node.getFirstChild();
                task.solve(node);
                stack.push(node);
            }
            // ，遍历完子节点后，若有兄弟节点则遍历并入栈，否则不断出栈到找到兄弟节点或者全部出栈完成
            if (!Objects.isNull(node.getNextSibling())) {
        
                node = node.getNextSibling();
                task.solve(node);
                stack.pop();
                stack.push(node);
            } else {
    
                while (stack.getSize() > 0) {
        
                    if (!Objects.isNull(node.getNextSibling())) {
    
                        node = node.getNextSibling();
                        stack.pop();
                        stack.push(node);
                        break;
                    } else {
    
                        stack.pop();
                        node = stack.view();
                    }
                }
            }
        }
    }
    
    @Override
    public void postTraversal(Callback task) {
    
    }
}
