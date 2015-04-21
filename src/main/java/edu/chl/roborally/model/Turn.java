package edu.chl.roborally.model;

import edu.chl.roborally.controller.Controller;
import edu.chl.roborally.model.cards.RegisterCard;
import edu.chl.roborally.model.cards.RegisterCardCompare;
import edu.chl.roborally.model.maps.GameBoard;
import edu.chl.roborally.view.View;
import edu.chl.roborally.model.RoboRally;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by henriknilson on 31/03/15.
 */
public class Turn {

    private RoboRally model;
    private Controller controller;
    private View view;
    private ArrayList<Player> players;
    private ArrayList<RegisterCard> activeCards = new ArrayList<>();
    private Map<RegisterCard,Player> activeCardPlayer = new HashMap<>();

    /**
    * The index of the turn, given by round
    */
    private final int index;

    public Turn(RoboRally r, int index) {
        model = r;
        controller = model.getController();
        view = model.getView();
        players = model.getPlayers();
        this.index = index;
        startTurn();
    }

    private void startTurn() {
        revealProgrammedCards();
        sortActiveCards();
        executeActiveCards();
        executeBoardElements();
    }

    // Executing methods

    private void revealProgrammedCards() {
        for (Player player : players) {
            RegisterCard card = player.getProgrammedCard(index);
            card.setHidden(false);
            activeCards.add(card);
            activeCardPlayer.put(card,player);
        }
    }

    private void sortActiveCards() {
        Collections.sort(activeCards, new RegisterCardCompare());
    }

    private void executeActiveCards() {
        view.print("Card Actions");
        for (RegisterCard card : activeCards) {
            Player player = activeCardPlayer.get(card);
            if (player.isAlive()) {
                card.doAction(player);
            }
        }
    }

    // Express converyor belts move 1 space
    private void executeBoardElements() {
        view.print("Tile Actions");
        for (Player p : players) {
            if (p.isAlive()) {
                try {
                    model.getGameBoard().getTile(p.getPosition()).doAction(p);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // If player is out of bounds we kill him
                    view.print("Player fell of board and died");
                    p.kill();
                }
            }
        }
    }
}