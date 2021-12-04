package GUI;

import javax.swing.tree.DefaultMutableTreeNode;

import core.simulation.Point;

public class MyNode extends DefaultMutableTreeNode{
    
    private String nodeName;

    public MyNode(Point p2, String nodeName) {
        super(p2);
        this.nodeName = nodeName;
    }

    public MyNode(String className, String nodeName) {
        super(className);
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return this.nodeName;
    }
    
}
