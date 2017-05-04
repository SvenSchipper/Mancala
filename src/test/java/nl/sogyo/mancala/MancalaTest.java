package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MancalaTest
{
    private Field field1;
    private Field field2;
    private Field field3;
    private Field field4;
    private Field field5;
    private Field field6;
    private Field field7;
    private Field field8;
    private Field field9;
    private Field field10;
    private Field field11;
    private Field field12;
    private Field field13;
    private Field field14;

    private Player player1;
    private Player player2;

    private int turns;

    @Before
    public void setUp()             //all holes are given a name for testing purposes
    {
        player1 = new Player();
        player2 = player1.opponent;

        field1 = new Hole(player1);
        field2 = field1.neighbour;
        field3 = field2.neighbour;
        field4 = field3.neighbour;
        field5 = field4.neighbour;
        field6 = field5.neighbour;
        field7 = field6.neighbour;
        field8 = field7.neighbour;
        field9 = field8.neighbour;
        field10 = field9.neighbour;
        field11 = field10.neighbour;
        field12 = field11.neighbour;
        field13 = field12.neighbour;
        field14 = field13.neighbour;

        turns = 0;
    }

    @Test
    public void createHoleWithStones()
    {
        Assert.assertEquals(4, field1.stones);
    }

    @Test
    public void doesFieldClearItselfWhenPlayed()
    {
        field1.play(player1);
        Assert.assertEquals(0, field1.stones);
    }

    @Test
    public void areStonesPassedToTheNextField()
    {
        field1.play(player1);
        Assert.assertEquals(5, field2.stones);
    }

    @Test
    public void doNeighboursUpdateProperlyAndPassStones()
    {
        field1.play(player1);
        Assert.assertEquals(5, field3.stones);
        Assert.assertEquals(5, field4.stones);
        Assert.assertEquals(5, field5.stones);
        Assert.assertEquals(4, field6.stones);
    }

    @Test
    public void isTheFinalNodeLinkedToTheFirstNode()
    {
        field12.play(player2);
        Assert.assertEquals(5, field1.stones);
    }

    @Test
    public void doKalahasGetInitializedWith0Stones()
    {
        Assert.assertEquals(0, field7.stones);
        Assert.assertEquals(0, field14.stones);
    }

    @Test
    public void isAHoleAbleToFindTheKalaha()
    {
        Assert.assertEquals(field7, field1.findKalaha());
        Assert.assertEquals(field14, field10.findKalaha());
    }

    @Test
    public void isAHoleAbleToFindItsOppositeHole()
    {
        Assert.assertEquals(field10, field4.findOppositeField());
    }

    @Test
    public void doesCapturingClearTheHolesAndFillTheKalaha()
    {
        field3.capture();
        Assert.assertEquals(0, field3.stones);
        Assert.assertEquals(0, field11.stones);
        Assert.assertEquals(8, field7.stones);
    }

    @Test
    public void playerInitializationWithCorrectTurns()
    {
        Assert.assertEquals(true, player1.turn);
        Assert.assertEquals(false, player2.turn);
    }

    @Test
    public void doesTurnSwitchingWork()
    {
        player1.switchTurn();
        Assert.assertEquals(false, player1.turn);
        Assert.assertEquals(true, player2.turn);
        player2.switchTurn();
        Assert.assertEquals(true, player1.turn);
        Assert.assertEquals(false, player2.turn);
    }

    @Test
    public void ownersCorrectlyAssignedToFields()
    {
        Assert.assertEquals(player1, field1.owner);
        Assert.assertEquals(player1, field7.owner);
        Assert.assertEquals(player2, field8.owner);
        Assert.assertEquals(player2, field14.owner);
    }

    @Test
    public void canThePlayerSelectOnlyItsOwnHoles()
    {
        Assert.assertEquals(true, player1.turn);
        Assert.assertEquals(false, player2.turn);
        field12.play(player1);
        Assert.assertEquals(4, field13.stones);
        field12.play(player2);
        Assert.assertEquals(5, field13.stones);
    }

    @Test
    public void isOpponentsKalahaSkippedInAMove()
    {
        field3.stones = 12;
        field3.play(player1);
        Assert.assertEquals(0, field14.stones);
        Assert.assertEquals(1, field7.stones);
        Assert.assertEquals(5, field8.stones);
    }

    @Test
    public void doesEndingInKalahaGrantExtraTurn()
    {
        Assert.assertEquals(true, player1.turn);
        Assert.assertEquals(false, player2.turn);
        field3.play(player1);
        Assert.assertEquals(1, field7.stones);
        Assert.assertEquals(4, field8.stones);
        Assert.assertEquals(true, player1.turn);
        Assert.assertEquals(false, player2.turn);
    }

    @Test
    public void doesCapturingTrigger()
    {
        field5.stones = 0;
        field1.play(player1);
        Assert.assertEquals(0, field5.stones);
        Assert.assertEquals(0, field9.stones);
        Assert.assertEquals(5, field7.stones);
    }

    @Test
    public void doesCaptureNotTriggerOnOpponentsEmptyHoles()
    {
        field10.stones = 0;
        field6.play(player1);
        Assert.assertEquals(1, field10.stones);
        Assert.assertEquals(4, field4.stones);
        Assert.assertEquals(1, field7.stones);
    }


    @Test
    public void doesSweepingWork()
    {
        field7.sweep();
        Assert.assertEquals(24,field14.stones);
    }

    @Test
    public void doesGameEndWhenSupposedTo()
    {
        field8.stones = 0;
        field9.stones = 0;
        field10.stones = 0;
        field11.stones = 0;
        field12.stones = 0;
        field13.stones = 0;
        field1.play(player1);
        Assert.assertEquals(false, player1.turn);
        Assert.assertEquals(false, player2.turn);
    }

    @Test
    public void doesSweepingWorkInEndOfGame()
    {
        field8.stones = 0;
        field9.stones = 0;
        field10.stones = 0;
        field11.stones = 0;
        field12.stones = 0;
        field13.stones = 0;
        field1.play(player1);
        Assert.assertEquals(false, player1.turn);
        Assert.assertEquals(false, player2.turn);
        Assert.assertEquals(24, field7.stones);
        Assert.assertEquals(0,field14.stones);
    }
}
