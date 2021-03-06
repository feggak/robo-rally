package edu.chl.roborally.model.gameactions;

import edu.chl.roborally.model.cards.CardDeck;
import edu.chl.roborally.model.robot.Robot;
import edu.chl.roborally.utilities.Constants;
import edu.chl.roborally.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class RotatePlayerTest {

    private Player player;
    private GameAction action;
    private CardDeck deck;

    @Before
    public void setUp() throws Exception {
        player = new Player(0, new Robot("Rotate TestPlayer", Color.ORANGE));
        deck = new CardDeck();
        player.setProgrammedCard(0, deck.getCards(1).get(0));
        player.setProgrammedCard(1, deck.getCards(1).get(0));
        player.setProgrammedCard(2, deck.getCards(1).get(0));
        player.setProgrammedCard(3, deck.getCards(1).get(0));
        player.setProgrammedCard(4, deck.getCards(1).get(0));
    }

    @Test
    public void testAction() throws Exception {
        //Rotate Player from East to North
        player.setDirection(Constants.Directions.EAST);
        action = new RotatePlayer(Constants.Directions.LEFT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.NORTH);

        //Rotate Player from North to West
        action = new RotatePlayer(Constants.Directions.LEFT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.WEST);

        //Rotate Player from West to South
        action = new RotatePlayer(Constants.Directions.LEFT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.SOUTH);

        //Rotate Player from South to East
        action = new RotatePlayer(Constants.Directions.LEFT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.EAST);

        //Rotate Player from East to South
        action = new RotatePlayer(Constants.Directions.RIGHT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.SOUTH);

        //Rotate Player from South to West
        action = new RotatePlayer(Constants.Directions.RIGHT);
        action.doAction(player);
        System.out.println("Player direction " + player.getDirection());
        assertTrue(player.getDirection() == Constants.Directions.WEST);





    }
}