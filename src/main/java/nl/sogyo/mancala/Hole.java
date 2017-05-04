package nl.sogyo.mancala;

class Hole extends Field
{
    Hole(Player currentPlayer)
    {
        int fieldsCreated = 1;

        stones = 4;
        startNode = this;
        fieldsCreated++;
        owner = currentPlayer;
        neighbour = new Hole(fieldsCreated, startNode, currentPlayer);
    }

    Hole(int fieldsCreated, Hole startNode, Player playerOwner)
    {
        super(fieldsCreated, startNode, playerOwner);
        owner = playerOwner;
        stones = 4;
    }

    void play(Player currentPlayer)
    {
        if(owner == currentPlayer)
        {
            stonesLeft = stones;
            stones = 0;
            neighbour.giveStones(stonesLeft, currentPlayer);
        }
    }

    Kalaha findKalaha()
    {
        return neighbour.findKalaha();
    }

    Field findOppositeField()
    {
        return neighbour.findOppositeField().neighbour;
    }

    void capture()
    {
        ((Hole)findOppositeField()).getCaptured(findKalaha());
        getCaptured(findKalaha());
    }

    private void getCaptured(Kalaha kalaha)
    {
        kalaha.capture(this.stones);
        stones = 0;
    }

    void checkEnd()
    {
        if(neighbour.getClass() != Kalaha.class && stones == 0)
        {
            ((Hole)neighbour).checkEnd();
        }
        else if(stones == 0)
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
        stones = 0;
        if(neighbour.getClass() != Kalaha.class)
        {
            neighbour.sweep();
        }
    }

    void endOfMove(Player currentPlayer)
    {
        if(stones == 1 && owner == currentPlayer)
        {
            capture();
            currentPlayer.switchTurn();
        }
        else
        {
            currentPlayer.switchTurn();
        }
        turns++;
        findKalaha().checkEndOfGame();
    }
}
