package GUI;

import javax.swing.tree.DefaultMutableTreeNode;
import core.simulation.Point;
/**
 * Gui
 * 
 * the class MyNode extends DefaultMutableTreeNode 
 * add the nodeName attribute to the name class and override 
 * the toString method to give the correct name in the GUI.
 * 
 * 
 * @author m.cimino
 */


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
