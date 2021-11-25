package fr.tys.emaplateforme;

import com.badlogic.gdx.Game;

public class EMAPlateforme extends Game {

    /**
     * Screen used to play
     */
    private GameScreen gameScreen;

    /**
     * Called when the screen becomes active
     * Generates a {@link GameScreen} and loads it
     */
    public void create() {
        this.gameScreen = new GameScreen();
        setScreen(this.gameScreen);
    }
}
