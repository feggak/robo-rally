package edu.chl.roborally.model.tiles.attributes;

import edu.chl.roborally.utilities.Constants;
import edu.chl.roborally.model.gameactions.KillPlayer;
import edu.chl.roborally.utilities.GlobalImageHolder;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by axel on 2015-03-31.
 *
 * Represents a pit attribute.
 * This is a hole in the board and a player dies instantly if he/she walks over it.
 */
public class PitAttribute extends Attribute {
    
    public PitAttribute() {
        super.addAction(new KillPlayer());
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        BufferedImage temp = GlobalImageHolder.getInstance().getBoardTileImage().getSubimage(
                5*Constants.TILE_SIZE, 0, Constants.TILE_SIZE, Constants.TILE_SIZE);
        g.drawImage(temp, x, y, null);
    }
}
