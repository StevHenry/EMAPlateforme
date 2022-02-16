package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.util.AssetsManager;
import fr.tys.emaplateforme.world.Character;
import fr.tys.emaplateforme.world.WorldRenderer;

public class EndScreen extends ScreenAdapter {

    private final EMAPlateforme game;
    private final Character character;
    private final SpriteBatch batch;

    public EndScreen(EMAPlateforme game, Character character) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.character = character;
    }

    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setCharacterScreen();
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
        ScreenUtils.clear(0.0F, 0F, 0F, 1.0F);
        BitmapFont font = AssetsManager.getInstance().getFont();
        GlyphLayout layout1 = new GlyphLayout(font, "Congratulations: you won! Thanks for playing!");
        GlyphLayout layout2 = new GlyphLayout(font, "You died " + character.getDeathCount() +
                " time(s). Press SPACE to restart!");

        batch.begin();
        font.draw(batch, layout1, (Gdx.graphics.getWidth() - layout1.width) / 2, Gdx.graphics.getHeight() * 0.75f);
        font.draw(batch, layout2, (Gdx.graphics.getWidth() - layout2.width) / 2, Gdx.graphics.getHeight() * 0.70f);
        batch.end();
    }

    public void dispose() {
        character.resetDeathCount();
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
