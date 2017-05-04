package nl.sogyo.mancala;

class Kalaha extends Field
{
    Kalaha(int fieldsCreated, Hole startNode, Player playerOwner)
    {
        super(fieldsCreated, startNode, playerOwner);
        owner = playerOwner;
        stones = 0;
    }

    Kalaha findKalaha()
    {
        return this;
    }

    Field findOppositeField()
    {
        return this;
    }

    void capture(int stonesCaptured)
    {
        stones += stonesCaptured;
    }

    void checkEndOfGame()
    {
        ((Hole)neighbour).checkEnd();
    }

    void sweep()
    {
       neighbour.sweep();
    }

    void endOfMove(Player currentPlayer)
    {
        neighbour.findKalaha().checkEndOfGame();
    }

}
