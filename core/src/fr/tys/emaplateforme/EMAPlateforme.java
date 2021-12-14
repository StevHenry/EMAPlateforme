package fr.tys.emaplateforme;

import com.badlogic.gdx.Game;
import fr.tys.emaplateforme.screen.GameScreen;
import fr.tys.emaplateforme.util.AssetsManager;

public class EMAPlateforme extends Game {

    /**
     * Screen used to play
     */
    private GameScreen gameScreen;

    /**
     * Generates a {@link GameScreen} and loads it
     */
    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    /**
     * Unloads the game
     */
    @Override
    public void dispose(){
        AssetsManager.getInstance().unloadTextures();
    }
}
