package edu.chl.roborally.model.tiles.attributes;

import edu.chl.roborally.model.Player;
import edu.chl.roborally.model.cards.RegisterCard;
import edu.chl.roborally.model.gameactions.GameAction;
import edu.chl.roborally.model.gameactions.RepairPlayer;
import edu.chl.roborally.utilities.Constants;
import edu.chl.roborally.utilities.EventTram;
import edu.chl.roborally.utilities.GlobalImageHolder;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Pertta on 15-05-12.
 */
public class RepairAttribute extends Attribute {

    public RepairAttribute() {
        super.setAction(new RepairPlayer());
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        BufferedImage temp = GlobalImageHolder.getInstance().getBoardTileImage().getSubimage(
                6*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
        g.drawImage(temp, x, y, null);
    }
}
