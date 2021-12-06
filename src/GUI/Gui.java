package GUI;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import core.models.Model;
import core.simulation.Cluster;
import core.simulation.Point;
import core.simulation.PointComplexe;
import core.simulation.PointSingulier;
import core.simulation.Simulation;
import ptolemy.plot.Plot;

/**
 * Gui
 * 
 * this class allows you to create the graphical interface for displaying daily power trends.
 * 
 * @author m.cimino
 */
public class Gui extends JFrame {
    
    private JTree tree; //attribut pour l'interface graphique
    private HashMap<String, MyNode> nameNodes = new HashMap<String,MyNode>();
    private HashMap<String, Integer> nameNum = new HashMap<String, Integer>();
    private Integer clusterNum = 0;

    public Gui(Simulation simulation)
    {
        MyNode root = new MyNode("Simulation City", "Simulation City"); 
        //root node : top-most parent for all nodes in the tree
        
        int cluster_index = 1;
        for(Cluster c : simulation.getClusters() ){
            /*for each cluster in the simulation, it creates a node and adds it to the root */
            MyNode clusterNode = new MyNode(c, c.getName()+"_"+ cluster_index);
            cluster_index += 1;
            clusterNode = this.getSubTree(clusterNode, c);
            /**
             * calls the getSubTree method which explores all points in the cluster and builds the tree
             */
            root.add(clusterNode);
            
        }
        
        tree = new JTree(root);
        add(tree);
        add(new JScrollPane(tree)); 

        /*TreeSelectionListener ensures that the tree is always listening 
        and executes the method on every click. */ 
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                MyNode selectedNode = (MyNode) tree.getLastSelectedPathComponent();

                if(null == selectedNode){
                    return;
                }
                Object o = selectedNode.getUserObject(); //takes the object contained in the node
                ArrayList <PointSingulier> pointList = new ArrayList <PointSingulier> ();
                //pointList contains the list of all points (both producers and consumers)


                if (o.getClass().toString().equals("class java.lang.String")){
                    /**
                     * if the object contained in the node is a string it will have under other nodes
                     *  which we explore with the children method which provides an iterator 
                     * (allows us to explore all nodes with the getAllPoints)
                     */
                    Enumeration<TreeNode> children = selectedNode.children();
                        while (children.hasMoreElements()) {
                            MyNode child = (MyNode) children.nextElement();
                            Object childObj = child.getUserObject();
                            pointList.addAll(((Point) childObj).getAllPoints());
                        }

                } else {
                    /**
                     * Otherwise, if the node contains a point, I explore all the sub-nodes with the getAllPoints()
                     */
                    pointList = ((Point) o).getAllPoints();
                }
                //graph construction
                Plot plot = new Plot();
                JFrame frame = new JFrame("Production/Consumption over a day");

                for(int i=0; i < 1440; i++) {
                    /**for each minute in the day we scroll through all the points 
                     * and calculate the production and power consumption of the point in that specific minute*/
                    Double energyConsums = 0.0;
                    Double energyProduces = 0.0;

                    for (PointSingulier p : pointList) {
                        Model model = p.getModel();
                        energyConsums += model.getPowerConsMin(i, 1);
                        energyProduces += model.getPowerProdMin(i, 1);
                    }
                    //add the point to the plot
                    plot.addPoint(0, i, energyConsums, true);
                    plot.addPoint(1, i, energyProduces, true);
                }

                frame.add(plot);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        ImageIcon imageIcon = new ImageIcon(Gui.class.getResource("../img/energetic1.png"));
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(imageIcon);

        tree.setCellRenderer(renderer);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");       
        this.pack();
        this.setVisible(true);
        this.setSize(450, 700);
        
    }




    //the method is used to, given a point, hierarchically order all the points below it in the tree
    public MyNode getSubTree(MyNode treeNode, Point p2){
        MyNode node;

        if(!p2.isComplexe()){
            //if the point is not complex it is simple (the point is the leaf of the tree in this case)
            String allClass = ((PointSingulier)p2).getModel().getClass().toString();
            String className = allClass.substring(allClass.lastIndexOf('.') + 1);
            
            if(!this.nameNodes.keySet().contains(className)) {
                /**
                 * if there is no className in nameNodes creates 
                 * a node that contains the string with the name, adds 
                 * it to the tree and inserts the name and type in the HashMap nameNodes.
                 */
                MyNode nodeType = new MyNode(className, className);
                treeNode.add(nodeType);
                this.nameNodes.put(className, nodeType);
            }

            if(!this.nameNum.keySet().contains(className)) {
                this.nameNum.put(className, 1);
            } else {
                this.nameNum.replace(className, this.nameNum.get(className) + 1);
            }
            //creates the node and adds it to the tree
            node = new MyNode(p2, className + "_" + this.nameNum.get(className));
            treeNode.add(node);

            this.nameNodes.get(className).add(node);
            return treeNode;

        }else{
            
            this.nameNodes.clear();
            //creates the cluster node
            node = new MyNode(p2, p2.getName()+"_"+ this.clusterNum);
            this.clusterNum += 1;
            for(Point p : ((PointComplexe)p2).getPoints()){
                /**
                 * explores all complex points in the cluster by calling up the method getSubTree
                 */
                node = this.getSubTree(node, p);
                treeNode.add(node);
            }
            
            return treeNode;
        }

    }
}
