package com.example.awesomefat.csc518_listexample.AirportTree;


import com.example.awesomefat.csc518_listexample.Airport;

public class AirportTree
{
    private ATree root;

    public AirportTree()
    {
        this.root = null;
    }

    public ATree getRoot() {
        return root;
    }

    public void add(Airport payload)
    {
        ATree a = new ATree(payload);
        if(this.root == null)
        {
            this.root = a;
        }
        else
        {
            this.root.add(a);
        }
    }

    public void visitInOrder()
    {
        System.out.println("**** Airports: " + this.root.visitInOrder());
    }
}
