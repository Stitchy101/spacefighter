package com.thinkbox.sf.control.timers;

import java.awt.Rectangle;
import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.Timer;
import com.thinkbox.sf.model.Enemy;
import com.thinkbox.sf.model.Unit;

public class TileMapTimer extends Thread {
	public TileMapTimer(){
		
	}
	
	public void run(){
		while(true){
			
			if(Game.getInstance().play.asteriods.getActive().size() == 0 && Game.getInstance().play.enemyGroup.getActive().size() == 0){
				Game.getInstance().play.map.setLock(false);
			}
			
			try {
				for (Timer aTimer : Game.getInstance().play.timers) {
					if(aTimer.getDone() && aTimer.getPurpose() == "Tiles"){
						Game.getInstance().play.map.setLock(false);
						Game.getInstance().play.timers.remove(aTimer);
					}
				}
			} catch (ConcurrentModificationException e) {
			}
			
			if (!Game.getInstance().play.map.getLock()) {
				Rectangle rec = new Rectangle(0, 0, Game.frame.getWidth(), 50);
				Rectangle rec5 = new Rectangle(Game.getInstance().play.player.getX(),
						Game.getInstance().play.player.getY(), 1, 1);
				if (rec5.intersects(rec) && Game.getInstance().play.map.getY() + 1 < GameConstants.MAP_WIDTH) {
					Game.getInstance().play.map.transitionUp();
				}

				Rectangle rec2 = new Rectangle(0, Game.frame.getHeight() - 50, Game.frame.getWidth(), 100);
				if (rec5.intersects(rec2) && Game.getInstance().play.map.getY() - 1 >= 0) {
					Game.getInstance().play.map.transitionDown();
				}

				Rectangle rec3 = new Rectangle(Game.frame.getWidth() - 100, 0, 100, Game.frame.getHeight());
				if (rec5.intersects(rec3) && Game.getInstance().play.map.getX() + 1 < GameConstants.MAP_WIDTH) {
					Game.getInstance().play.map.transitionRight();
				}

				Rectangle rec4 = new Rectangle(0, 0, 100, Game.frame.getHeight());
				if (rec5.intersects(rec4) && (Game.getInstance().play.map.getX() - 1 >= 0)) {
					Game.getInstance().play.map.transitionLeft();
				}
			}			
			
			try {
				sleep(GameConstants.MAP_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
