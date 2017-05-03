package nl.sogyo.mancala;

abstract class Field
{
    int counter = 1;
    int stones;
    int stonesLeft;
    private int turns;
    Boolean endGame = false;
    Hole startNode;
    Field neighbour;
    Player owner;

    Field()
    {

    }

    Field(int counter, Hole startNode, Player currentPlayer)
    {
        counter++;
        if(counter < 8)
        {
            owner = currentPlayer;
        }
        else
        {
            owner = currentPlayer.opponent;
        }
        if(counter < 15 && counter % 7 != 0)
        {
            neighbour = new Hole(counter, startNode, owner);
        }
        else if(counter < 15 && counter % 7 == 0)
        {
            neighbour = new Kalaha(counter, startNode, owner);
        }
        if(counter == 15)           //links the final node to the first node, completing the circle
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

    private void endOfMove(Player currentPlayer)
    {
        Kalaha checkEndGame;
        if(getClass() == Kalaha.class)
        {

        }
        else if(getClass() == Hole.class && stones == 1 && owner == currentPlayer)
        {
            capture();
            currentPlayer.switchTurn();
        }
        else
        {
            currentPlayer.switchTurn();
        }

        if(owner == currentPlayer)
        {
            checkEndGame = findKalaha();
        }
        else
        {
            checkEndGame = findOppositeField().findKalaha();
        }

        checkEndGame.checkEndOfGame();
    }

    void checkEnd()
    {

    }

    void endGame(Kalaha kalaha1, Kalaha kalaha2, Player currentPlayer)
    {

        int player1score;
        int player2score;

        if(turns%2 == 1)
        {
            player1score = kalaha1.stones;
            player2score = kalaha2.stones;
        }
        else
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

        currentPlayer.endGameTurn();
    }

    protected void play(Player currentPlayer)
    {

    }

    protected abstract Kalaha findKalaha();

    protected abstract Field findOppositeField();

    protected void capture()
    {

    }

    abstract void sweep();
}
