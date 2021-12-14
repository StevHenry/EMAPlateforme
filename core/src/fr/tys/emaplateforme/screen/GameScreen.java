package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import fr.tys.emaplateforme.controller.CharacterMovementController;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.util.WorldCreator;
import fr.tys.emaplateforme.world.WorldRenderer;

public class GameScreen implements Screen {

    private World world;
    private WorldRenderer renderer;
    private CharacterMovementController controller;

    /**
     * Method called when the Screen becomes active
     */
    public void show() {
        world = WorldCreator.loadWorldFromFile("world0.txt");
        renderer = new WorldRenderer(world);
        controller = new CharacterMovementController(world);
        Gdx.input.setInputProcessor(controller);
    }

    /**
     * Delegates to the {@link WorldRenderer#render(float)} method
     *
     * @param delta time between two frames
     */
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);
    }

    /**
     * Method called when the Screen becomes inactive
     */
    public void dispose() {
        this.renderer.dispose();
    }

    public void hide() {}

    public void resize(int width, int height) {}

    public void pause() {}

    public void resume() {}
}
