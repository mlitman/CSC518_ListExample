package com.example.awesomefat.csc518_listexample;

public class LinkedListOfCreditCards
{
    private CreditCardNode head;
    private int count;

    public LinkedListOfCreditCards()
    {
        this.head = null;
        this.count = 0;
    }

    public int length()
    {
        return this.count;
    }

    public CreditCard getAtIndex(int index)
    {
        CreditCardNode currNode = this.head;
        for(int i = 0; i < index; i++)
        {
            currNode = currNode.getNextNode();
        }
        return currNode.getPayload();
    }

    public void addEnd(CreditCard payload)
    {
        CreditCardNode n = new CreditCardNode(payload);

        if(this.head == null)
        {
            this.head = n;
        }
        else
        {
            CreditCardNode currNode = this.head;

            while(currNode.getNextNode() != null)
            {
                currNode = currNode.getNextNode();
            }
            currNode.setNextNode(n);
        }
        this.count++;
    }
}
