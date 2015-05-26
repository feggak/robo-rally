package edu.chl.roborally.model.gameactions;

import edu.chl.roborally.utilities.Constants;
import edu.chl.roborally.model.Player;
import edu.chl.roborally.utilities.EventTram;

/**
 * Created by henriknilson on 31/03/15.
 */
public class RotatePlayer implements GameAction {

    @Override
    public void action(Player p) {
        switch(p.getDirection()) {

            // Turn Right
            case EAST:
                switch (super.player.getDirection()) {
                    case NORTH:
                        super.player.setDirection(Constants.Directions.EAST);
                        break;
                    case EAST:
                        super.player.setDirection(Constants.Directions.SOUTH);
                        break;
                    case SOUTH:
                        super.player.setDirection(Constants.Directions.WEST);
                        break;
                    case WEST:
                        super.player.setDirection(Constants.Directions.NORTH);
                }
                break;

            // Turn Left
            case WEST:
                switch (super.player.getDirection()) {
                    case NORTH:
                        super.player.setDirection(Constants.Directions.WEST);
                        break;
                    case WEST:
                        super.player.setDirection(Constants.Directions.SOUTH);
                        break;
                    case SOUTH:
                        super.player.setDirection(Constants.Directions.EAST);
                        break;
                    case EAST:
                        super.player.setDirection(Constants.Directions.NORTH);
                        break;
                }
                break;
        }
    }
}
