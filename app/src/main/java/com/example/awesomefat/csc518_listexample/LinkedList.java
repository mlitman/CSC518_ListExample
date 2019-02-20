package com.example.awesomefat.csc518_listexample;

public class LinkedList
{
    private Node head; //always points to the front of the list
    private int count;
    public LinkedList()
    {
        this.head = null;
        this.count = 0;
    }

    public int length()
    {
        return this.count;
    }

    //public CreditCard getAtIndex....
    public int getAtIndex(int index)
    {
        Node currNode = this.head;
        for(int i = 0; i < index; i++)
        {
            currNode = currNode.getNextNode();
        }
        return currNode.getPayload();
    }

    public void addEnd(int payload)
    {
        Node n = new Node(payload);

        if(this.head == null)
        {
            this.head = n;
        }
        else
        {
            //not the empty list
            //walk to the end of the list
            Node currNode = this.head;
            while(currNode.getNextNode() != null)
            {
                currNode = currNode.getNextNode();
            }

            //I am at the end of the list
            currNode.setNextNode(n);
        }
        this.count++;
    }

    public void display()
    {
        if(this.head == null)
        {
            System.out.println("Empty List");
        }
        else
        {
            //traverse the list and display it
        }
    }
}
