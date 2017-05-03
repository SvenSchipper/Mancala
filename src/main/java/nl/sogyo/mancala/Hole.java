package nl.sogyo.mancala;

class Hole extends Field
{
    Hole(Player currentPlayer)
    {
        stones = 4;
        startNode = this;
        counter++;
        owner = currentPlayer;
        neighbour = new Hole(counter, startNode, currentPlayer);
    }

    Hole(int counter, Hole startNode, Player playerOwner)
    {
        super(counter, startNode, playerOwner);
        owner = playerOwner;
        stones = 4;
    }

    protected void play(Player currentPlayer)
    {
        if(owner == currentPlayer)
        {
            stonesLeft = stones;
            stones = 0;
            neighbour.giveStones(stonesLeft, currentPlayer);
        }
    }

    protected Kalaha findKalaha()
    {
        return neighbour.findKalaha();
    }

    protected Field findOppositeField()
    {
        return neighbour.findOppositeField().neighbour;
    }

    protected void capture()
    {
        ((Hole)findOppositeField()).getCaptured(findKalaha());
        getCaptured(findKalaha());
    }

    private void getCaptured(Kalaha kalaha)
    {
        kalaha.capture(this.stones);
        this.stones = 0;
    }

    void checkEnd()
    {
        if(stones == 0)
        {
            endGame = true;
        }
        else
        {
            endGame = false;
        }

        if(neighbour.getClass() != Kalaha.class && endGame)
        {
            neighbour.checkEnd();
        }
        else if(endGame)
        {
            if(this.owner.turn)
            {
                findOppositeField().findKalaha().sweep();
            }
            else
            {
                findKalaha().sweep();
            }
            endGame(findKalaha(), findOppositeField().findKalaha(), owner);
        }
    }

    void sweep()
    {
        findKalaha().stones += stones;
        if(neighbour.getClass() != Kalaha.class)
        {
            neighbour.sweep();
        }
    }
}
