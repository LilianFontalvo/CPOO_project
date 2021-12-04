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
public class Gui extends JFrame {
    
    private JTree tree;
    private HashMap<String, MyNode> nameNodes = new HashMap<String,MyNode>();
    private HashMap<String, Integer> nameNum = new HashMap<String, Integer>();
    private Integer clusterNum = 0;

    public Gui(Simulation simulation)
    {
        MyNode root = new MyNode("Simulation City", "Simulation City");
        int cluster_index = 1;

        for(Cluster c : simulation.getClusters() ){
            MyNode clusterNode = new MyNode(c, "Cluster_" + cluster_index);
            cluster_index += 1;
            clusterNode = this.getSubTree(clusterNode, c);
            root.add(clusterNode);
            
        }
        
        tree = new JTree(root);
        add(tree);
        add(new JScrollPane(tree));

        
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                MyNode selectedNode = (MyNode) tree.getLastSelectedPathComponent();

                if(null == selectedNode){
                    return;
                }
                Object o = selectedNode.getUserObject();
                ArrayList <PointSingulier> pointList = new ArrayList <PointSingulier> ();


                if (o.getClass().toString().equals("class java.lang.String")){
                    Enumeration<TreeNode> children = selectedNode.children();
                        while (children.hasMoreElements()) {
                            MyNode child = (MyNode) children.nextElement();
                            Object childObj = child.getUserObject();
                            pointList.addAll(((Point) childObj).getAllPoints());
                        }

                } else {
                    pointList = ((Point) o).getAllPoints();
                }

                Plot plot = new Plot();
                JFrame frame = new JFrame("Production/Consumption over a day");

                for(int i=0; i < 1440; i++) {
                    Double energyConsums = 0.0;
                    Double energyProduces = 0.0;

                    for (PointSingulier p : pointList) {
                        Model model = p.getModel();
                        energyConsums += model.getPowerConsMin(i, 1);
                        energyProduces += model.getPowerProdMin(i, 1);
                    }

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




    //funzione ricorsiva: chiama se stessa e continua ad aggiungere figli finchè ne ha
    public MyNode getSubTree(MyNode treeNode, Point p2){
        MyNode node;

        if(!p2.isComplexe()){
            String allClass = ((PointSingulier)p2).getModel().getClass().toString();
            String className = allClass.substring(allClass.lastIndexOf('.') + 1);
            
            if(!this.nameNodes.keySet().contains(className)) {
                MyNode nodeType = new MyNode(className, className);
                treeNode.add(nodeType);
                this.nameNodes.put(className, nodeType);
            }

            if(!this.nameNum.keySet().contains(className)) {
                this.nameNum.put(className, 1);
            } else {
                this.nameNum.replace(className, this.nameNum.get(className) + 1);
            }

            node = new MyNode(p2, className + "_" + this.nameNum.get(className));
            treeNode.add(node);

            this.nameNodes.get(className).add(node);
            return treeNode;

        }else{
            //prendi p2 che è un punto e trasformalo in complesso (è sicuramente complesso
            //perchè altrimenti entrerebbe nell'if di prima
            this.nameNodes.clear();
            node = new MyNode(p2, "Cluster_" + this.clusterNum);
            this.clusterNum += 1;
            for(Point p : ((PointComplexe)p2).getPoints()){
                node = this.getSubTree(node, p);
            }
            treeNode.add(node);
            return treeNode;
        }

    }
}
