package fr.tys.emaplateforme;

import com.badlogic.gdx.Screen;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.world.WorldRenderer;

public class GameScreen implements Screen {

    private WorldRenderer renderer;

    /**
     * Method called when the Screen becomes active
     */
    public void show() {
        this.renderer = new WorldRenderer(new World());
    }

    /**
     * Delegates to the {@link WorldRenderer#render(float)} method
     * @param delta time between two frames
     */
    public void render(float delta) {
        this.renderer.render(delta);
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
