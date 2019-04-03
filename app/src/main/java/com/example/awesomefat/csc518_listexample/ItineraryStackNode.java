package com.example.awesomefat.csc518_listexample;

public class ItineraryStackNode
{
    private String payload;
    private ItineraryStackNode nextNode;

    public ItineraryStackNode(String payload)
    {
        this.payload = payload;
        this.nextNode = null;
    }

    public ItineraryStackNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(ItineraryStackNode nextNode) {
        this.nextNode = nextNode;
    }

    public String getPayload() {
        return payload;
    }

    public void display()
    {
        System.out.print(this.payload + " -> ");
    }
}
