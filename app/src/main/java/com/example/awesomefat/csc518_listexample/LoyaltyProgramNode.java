package com.example.awesomefat.csc518_listexample;

public class LoyaltyProgramNode
{
    private LoyaltyProgramNode nextNode;
    private LoyaltyProgram payload;

    public LoyaltyProgramNode(LoyaltyProgram payload)
    {
        this.payload = payload;
        this.nextNode = null;
    }

    public void setNextNode(LoyaltyProgramNode nextNode)
    {
        this.nextNode = nextNode;
    }

    public LoyaltyProgramNode getNextNode()
    {
        return this.nextNode;
    }

    public LoyaltyProgram getPayload()
    {
        return this.payload;
    }
}
