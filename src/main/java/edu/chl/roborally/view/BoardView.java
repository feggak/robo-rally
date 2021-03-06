package edu.chl.roborally.view;

import edu.chl.roborally.utilities.Constants;
import edu.chl.roborally.model.Player;
import edu.chl.roborally.utilities.Position;
import edu.chl.roborally.model.maps.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by fredrikkindstrom on 21/04/15.
 *
 * Contains the board of the game and the players on the board.
 */
class BoardView extends JPanel {
    
    private final GameBoard board;
    private final ArrayList<Player> players;
    private final int tileSize = Constants.TILE_SIZE;
    private final int columns = Constants.NUM_COLS;
    private final int rows = Constants.NUM_ROWS;
    private final int width = (tileSize * columns) + columns;
    private final int height = (tileSize * rows) + rows;

    /**
     * Contains the board of the game and the players on the board.
     * @param board The board to be drawn. Is unique for the game and is the same for all players.
     * @param players The list of players that are in the game. This is used to be able to draw them.
     */
    BoardView(GameBoard board, ArrayList<Player> players) {
        this.board = board;
        this.players = players;
        setSize(width, height);
    }

    /*
    Class methods
     */

    /**
     * Draws the grid of black lines that are between the tiles on the board.
     * @param g The graphics object to use when painting.
     */
    private void drawGrid(Graphics g) {
        // Vertical Lines
        g.drawLine(0,0,0,height);
        g.drawLine(width,0,width,height);
        int x = tileSize;
        for (int i = 1; i <= columns; i++) {
            g.drawLine(x+i,0,x+i,height);
            x += tileSize;
        }
        // Horizontal Lines
        g.drawLine(0, 0, width, 0);
        g.drawLine(0, height, width, height);
        int y = tileSize;
        for (int i = 1; i <= rows; i++) {
            g.drawLine(0,y+i,width,y+i);
            y += tileSize;
        }
    }

    /**
     * Draws the tiles on the board. This is done using the tiles own draw methods.
     * @param g The graphics object to use when painting.
     */
    private void drawTiles(Graphics g) {
        for (int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                board.getTile(i,j).draw(g, (i * tileSize) + i + 1, (j * tileSize) + j + 1);
            }
        }
    }

    /**
     * Draws the players on their respective positions on the board.
     * This is done using the players own draw methods.
     * @param g The graphics object to use when painting.
     */
    private void drawPlayers(Graphics g) {
        for (Player player : players) {
            Position pos = player.getPosition();
            if(player.isAlive()){
                player.draw(g, (pos.getX() * tileSize) + pos.getX() + 1, (pos.getY() * tileSize) + pos.getY() + 1);
            }
        }
    }

    /*
    Commands
     */

    /**
     * Repaints the entire board panel.
     */
    public void update() {
        repaint();
    }

    /*
    Painters
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawTiles(g);
        drawPlayers(g);
    }
}