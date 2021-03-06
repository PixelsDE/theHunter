package de.bypixels.thehunter.gamestates;

import java.util.ArrayList;


import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Settings;

public class GameStateHandler {

    private static GameState current;
    private static ArrayList<GameState> states = new ArrayList<>();

    public GameStateHandler() {
        if (Settings.editmode == false) {
            states.add(new LobbyState());
            states.add(new IngameState(theHunterMain.getPlugin()));
        }else {

        }
    }

    public static void setGameState(int id) {
        if (Settings.editmode == false) {
            if (current != null)
                current.end();
            try {
				current = states.get(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			
			}
            current.init();

        }
    }


    public static GameState getCurrentState() {
        return current;
    }

}
