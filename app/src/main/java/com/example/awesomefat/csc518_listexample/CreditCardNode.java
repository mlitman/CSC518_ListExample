package com.example.awesomefat.csc518_listexample;

public class CreditCardNode
{
    private CreditCard payload;
    private CreditCardNode nextNode;

    public CreditCardNode(CreditCard payload)
    {
        this.payload = payload;
        this.nextNode = null;
    }

    public void setNextNode(CreditCardNode nextNode) {
        this.nextNode = nextNode;
    }

    public CreditCard getPayload() {
        return payload;
    }

    public CreditCardNode getNextNode() {
        return nextNode;
    }
}
