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

    @Before
    public void setUp()             //all holes are given a name for testing purposes
    {
        field1 = new Hole();
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

        Player player1 = new Player();
        Player player2 = new Player();
    }

    @Test
    public void createHoleWithStones()
    {
        Assert.assertEquals(4, field1.stones);
    }

    @Test
    public void doesFieldClearItselfWhenPlayed()
    {
        field1.play();
        Assert.assertEquals(0, field1.stones);
    }

    @Test
    public void areStonesPassedToTheNextField()
    {
        field1.play();
        Assert.assertEquals(5,field2.stones);
    }

    @Test
    public void doNeighboursUpdateProperlyAndPassStones()
    {
        field1.play();
        Assert.assertEquals(5,field3.stones);
        Assert.assertEquals(5,field4.stones);
        Assert.assertEquals(5,field5.stones);
        Assert.assertEquals(4,field6.stones);
    }

    @Test
    public void isTheFinalNodeLinkedToTheFirstNode()
    {
        field12.play();
        Assert.assertEquals(5,field1.stones);
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
        Assert.assertEquals(field1.findKalaha(), field7);
        Assert.assertEquals(field10.findKalaha(), field14);
    }

    @Test
    public void isAHoleAbleToFindItsOppositeHole()
    {
        Assert.assertEquals(field4.findOpposite(), field10);
    }

    @Test
    public void doesCapturingClearTheHolesAndFillTheKalaha()
    {
        field3.capture();
        Assert.assertEquals(0, field3.stones);
        Assert.assertEquals(0, field11.stones);
        Assert.assertEquals(8, field7.stones);
    }
}
