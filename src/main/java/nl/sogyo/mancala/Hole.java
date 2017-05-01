package nl.sogyo.mancala;

class Hole extends Field
{
    Hole()
    {
        stones = 4;
        startNode = this;
        counter++;
        neighbour = new Hole(counter, startNode);
    }

    Hole(int counter, Hole startNode )
    {
        super(counter, startNode);
        stones = 4;
    }

    protected void play()
    {
        stonesLeft = this.stones;
        this.stones = 0;
        neighbour.giveStones(stonesLeft);
    }

    protected Kalaha findKalaha()
    {
            return this.neighbour.findKalaha();
    }

    protected Field findOpposite()
    {
        return this.neighbour.findOpposite().neighbour;
    }

    protected void capture()
    {
        Hole opposite = (Hole) this.findOpposite();
        opposite.getCaptured(this.findKalaha());
        this.getCaptured(this.findKalaha());
    }

    private void getCaptured(Kalaha kalaha)
    {
        kalaha.capture(this.stones);
        this.stones = 0;
    }
}
