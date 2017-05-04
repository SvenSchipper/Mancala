package nl.sogyo.mancala;

abstract class Field
{
    int stones;
    int stonesLeft;
    int turns;
    Hole startNode;
    Field neighbour;
    Player owner;

    Field() {}

    Field(int fieldsCreated, Hole startNode, Player currentPlayer)
    {
        fieldsCreated++;
        if(fieldsCreated < 8)
        {
            owner = currentPlayer;
        }
        else
        {
            owner = currentPlayer.opponent;
        }
        if(fieldsCreated < 15 && fieldsCreated % 7 != 0)
        {
            neighbour = new Hole(fieldsCreated, startNode, owner);
        }
        else if(fieldsCreated < 15 && fieldsCreated % 7 == 0)
        {
            neighbour = new Kalaha(fieldsCreated, startNode, owner);
        }
        if(fieldsCreated == 15)           //links the final node to the first node, completing the circle
        {
            neighbour = startNode;
        }
    }

    void giveStones(int stonesLeft, Player currentPlayer)
    {
        if(getClass() == Kalaha.class && owner != currentPlayer)
        {
            neighbour.giveStones(stonesLeft, currentPlayer);
        }
        else
        {
            stones++;
            stonesLeft--;
            if (stonesLeft > 0)
            {
                neighbour.giveStones(stonesLeft, currentPlayer);
            }
            else
            {
               endOfMove(currentPlayer);
            }
        }
    }

    void endGame(Kalaha kalaha1, Kalaha kalaha2, Player currentPlayer)
    {
        int player1score = kalaha1.stones;
        int player2score = kalaha2.stones;

        if(turns%2 == 0)
        {
            player1score = kalaha2.stones;
            player2score = kalaha1.stones;
        }

        if(player1score > player2score)
        {
            System.out.println("Player 1 has won!");
        }
        else if(player1score < player2score)
        {
            System.out.println("Player 2 has won!");
        }
        else
        {
            System.out.println("It's a tie!");
        }

        currentPlayer.finishGame();
    }

    void play(Player currentPlayer) {}

    void capture() {}

    abstract Kalaha findKalaha();

    abstract Field findOppositeField();

    abstract void endOfMove(Player currentPlayer);

    abstract void sweep();
}
