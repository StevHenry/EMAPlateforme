package fr.tys.emaplateforme;

import com.badlogic.gdx.Game;
import fr.tys.emaplateforme.screen.*;
import fr.tys.emaplateforme.util.AssetsManager;

public class EMAPlateforme extends Game {

    /**
     * Screen used to play
     */
    private GameScreen gameScreen;

    /**
    * Screen to choose the character (after TitleScreen)
    */
    private CharacterScreen characterScreen;

    /**
     * Screen when the player dies
     */
    private DeathScreen deathScreen;

    /**
     * Screen when the game is ended
     */
    private EndScreen endScreen;

    /**
     * Generates all the scenes and loads the first
     */
    @Override
    public void create() {
        setScreen(new TitleScreen(this));
        gameScreen = new GameScreen(this);
        characterScreen = new CharacterScreen(this, gameScreen.getWorld().getCharacter());
        deathScreen = new DeathScreen(this);
        endScreen = new EndScreen(this, gameScreen.getWorld().getCharacter());
    }

    /**
     * Unloads the game
     */
    @Override
    public void dispose(){
        AssetsManager.getInstance().unloadTextures();
    }

    /**
     * Updates the game to the game step
     */
    public void setGameScreen(){
        setScreen(gameScreen);
    }

    /**
     * Updates the game to the character selection step
     */
    public void setCharacterScreen(){
        setScreen(characterScreen);
    }

    /**
     * Updates the game to the game over step
     */
    public void setDeathScreen(){
        setScreen(deathScreen);
    }

    /**
     * Congratulates player and restart the game
     */
    public void setEndScreen(){
        setScreen(endScreen);
    }
}
