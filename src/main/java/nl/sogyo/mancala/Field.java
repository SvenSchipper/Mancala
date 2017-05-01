package nl.sogyo.mancala;

abstract class Field
{
    int counter = 1;
    int stones;
    int stonesLeft;
    Hole startNode;
    Field neighbour;

    Field()
    {

    }

    Field(int counter, Hole startNode)
    {
        counter++;
        if(counter < 15 && counter % 7 != 0)
        {
            neighbour = new Hole(counter, startNode);
        }
        else if(counter < 15 && counter % 7 == 0)
        {
            neighbour = new Kalaha(counter, startNode);
        }
        if(counter == 15)           //links the final node to the first node, completing the circle
        {
            neighbour = startNode;
        }
    }

    void giveStones(int stonesLeft)
    {
        this.stones++;
        stonesLeft--;
        if(stonesLeft > 0)
        {
            neighbour.giveStones(stonesLeft);
        }
    }

    protected void play()
    {

    }

    protected Kalaha findKalaha()
    {
        return (Kalaha) this.neighbour;
    }

    protected abstract Field findOpposite();

    protected void capture()
    {

    }
}
