package nl.sogyo.mancala;

class Player
{
    Player opponent;
    boolean turn;

    Player()
    {
        turn = true;
        opponent = new Player(this);
    }

    private Player(Player player1)
    {
        opponent = player1;
        turn = false;
    }

    void switchTurn()
    {
        this.turn = !turn;
        opponent.turn = !turn;
    }

    void finishGame()
    {
        this.turn = false;
        opponent.turn = false;
    }
}
