package nl.sogyo.mancala;

class Kalaha extends Field
{
    Kalaha(int counter, Hole startNode)
    {
        super(counter, startNode);
        stones = 0;
    }

    protected Kalaha findKalaha()
    {
        return this;
    }

    protected Field findOpposite()
    {
        return this;
    }

    void capture(int stonesCaptured)
    {
        this.stones += stonesCaptured;
    }
}
