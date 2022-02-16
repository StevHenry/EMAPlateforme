package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.controller.CharacterMovementController;
import fr.tys.emaplateforme.controller.GameInputsManager;
import fr.tys.emaplateforme.util.WorldCreator;
import fr.tys.emaplateforme.world.Character;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.world.WorldRenderer;

public class GameScreen implements Screen {

    private final World world;
    private final WorldRenderer renderer;
    private final CharacterMovementController controller;
    private final GameInputsManager gameInputsManager;

    public GameScreen(EMAPlateforme game) {
        gameInputsManager = new GameInputsManager();
        world = WorldCreator.loadWorldFromFile("world0.txt");
        renderer = new WorldRenderer(world);
        controller = new CharacterMovementController(game, world, gameInputsManager);
    }

    /**
     * Method called when the screen becomes active
     */
    public void show() {
        controller.reset();
        Character character = world.getCharacter();
        character.getAcceleration().set(0, 0);
        character.getVelocity().set(0, 0);
        character.getPosition().set(world.getSpawn());
        character.update();
        Gdx.input.setInputProcessor(gameInputsManager);
    }

    /**
     * Delegates to the {@link WorldRenderer#render()} method
     *
     * @param delta time between two frames
     */
    public void render(float delta) {
        controller.update(delta);
        renderer.render();
    }

    /**
     * Method called when the Screen becomes inactive
     */
    public void dispose() {
        this.renderer.dispose();
    }

    public void hide() {
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {}

    public World getWorld(){
        return world;
    }
}
