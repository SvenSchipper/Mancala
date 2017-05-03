package nl.sogyo.mancala;

class Kalaha extends Field
{
    Kalaha(int counter, Hole startNode, Player playerOwner)
    {
        super(counter, startNode, playerOwner);
        owner = playerOwner;
        stones = 0;
    }

    protected Kalaha findKalaha()
    {
        return this;
    }

    protected Field findOppositeField()
    {
        return this;
    }

    void capture(int stonesCaptured)
    {
        stones += stonesCaptured;
    }

    void checkEndOfGame()
    {
        neighbour.checkEnd();
    }

    void sweep()
    {
       neighbour.sweep();
    }

}
