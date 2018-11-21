package tree;

import common.Callback;

public interface TreeInterface<T> {
    
    public interface Node<ST> {
        
        ST getData();
    }
    
    
    public void preTraversal(Callback task);
    
    public void postTraversal(Callback task);
}
