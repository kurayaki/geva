/*
 * TreeNode.java
 *
 * Created on 16 October 2006, 16:45
 *
 */

package Util.Structures;

import Mapper.Rule;

import java.util.ArrayList;

/**
 * Node for use in the nimble tree structure. Has a parent and data. Is an array list
 * @author EHemberg
 */
public class TreeNode<E> extends ArrayList<TreeNode<E>>{
    
    private TreeNode<E> parent;
    private E data;
    private int id;
    
    /** Creates a new instance of TreeNode */
    public TreeNode() {
        super();
	id = 0;
    }

    /**
     * Create node with parent and data
     * @param parent node parent
     * @param data node data
     */
    public TreeNode(TreeNode<E> parent, E data) {
        super();
        this.parent = parent;
        this.data = data;
	this.id = 0;
    }
    
    /** Copy constructor
     * @param copy node to copy
     */
    public TreeNode(TreeNode<E> copy) {
        super(copy);
        for (TreeNode<E> aCopy : copy) {
            this.add(new TreeNode<E>(aCopy));
        }
        this.parent = new TreeNode<E>(copy.parent);
        this.data = copy.data;
	this.id = copy.id;
    }

    /**
     * Get parent node
     * @return parent node
     */
    public TreeNode<E> getParent(){
        return this.parent;
    }

    /**
     * Set parent node
     * @param tn parent node
     */
    public void setParent(TreeNode<E> tn) {
        this.parent = tn;
    }

    /**
     * Get data in node
     * @return data
     */
    public E getData() {
        return data;
    }

    /**
     * Set data in node
     * @param data node data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Get node id
     * @return int
     */
    public int getID() {
        return id;
    }

    /**
     * Set node id
     * @param int id
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Get the last node
     * @return the last node
     */
    public TreeNode<E> getEnd() {
        return this.get(this.size() - 1);
    }

    /**
     * Collapse the node to a string
     * @return string representation
     */
    public String collapse() {
        String s = "";
        s+=this.getData();
        if(this.getParent() != null) {
            s += this.getParent().collapse();
        }
        for (TreeNode<E> e : this) {
            s += e.getData().toString();
            s += e.getParent().collapse();
        }
        return s;
    }
    
    
    //Not working
    @Override
    public String toString() {
        String s = "";
        s += this.getData() + ":";
        for (TreeNode<E> e : this) {
            s += e.toString() + " ";
        }
        return s;
    }
    
    public static void main(String[] args) {
        TreeNode<Rule> tn = new TreeNode<Rule>();
        TreeNode<Rule> tn2 = new TreeNode<Rule>();
        TreeNode<Rule> tn3;
        Rule c = new Rule();
        c.setMinimumDepth(1);
        tn.setParent(tn);
        tn.setData(c);
        tn.add(tn2);
        tn2.setParent(tn);
        tn2.setData(new Rule(c));
        tn2.getData().setMinimumDepth(2);
        tn3 = new TreeNode<Rule>(tn2);
        System.out.println(tn.getParent().getData().getMinimumDepth());
        System.out.println(tn2.getData().getMinimumDepth());
        System.out.println(tn3.getParent().getData().getMinimumDepth());
        tn2.getData().setMinimumDepth(4);
        tn3.setParent(tn2);
        System.out.println(tn.getParent().getData().getMinimumDepth());
        System.out.println(tn2.getData().getMinimumDepth());
        System.out.println(tn3.getParent().getData().getMinimumDepth());
        
    }
    
}
