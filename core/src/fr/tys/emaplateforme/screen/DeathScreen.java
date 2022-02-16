package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.util.AssetsManager;
import fr.tys.emaplateforme.world.WorldRenderer;

public class DeathScreen extends ScreenAdapter {

    private final EMAPlateforme game;
    private final SpriteBatch batch;
    private final GlyphLayout layout;

    public DeathScreen(EMAPlateforme game) {
        this.game = game;
        this.batch = new SpriteBatch();
        layout = new GlyphLayout(AssetsManager.getInstance().getFont(), "You died! Press space to respawn!");
    }

    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setGameScreen();
                }
                return true;
            }
        });
    }

    /**
     * Delegates to the {@link WorldRenderer#render()} method
     *
     * @param delta time between two frames
     */
    public void render(float delta) {
        batch.begin();
        AssetsManager.getInstance().getFont().draw(batch, layout, (Gdx.graphics.getWidth() - layout.width) / 2,
                Gdx.graphics.getHeight() * 0.75f);
        batch.end();
    }

    /**
     * Method called when the Screen becomes inactive
     */
    public void dispose() {
    }

    public void hide() {
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }
}
