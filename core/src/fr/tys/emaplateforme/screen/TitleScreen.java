package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.util.AssetsManager;

public class TitleScreen extends ScreenAdapter {

    private final EMAPlateforme game;
    private final SpriteBatch batch;
    private final GlyphLayout pressToPlay;

    public TitleScreen(EMAPlateforme game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.pressToPlay = new GlyphLayout(AssetsManager.getInstance().getFont(), "Press space to play");
    }

    @Override
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2F, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        AssetsManager.getInstance().getFont().draw(batch, pressToPlay, (Gdx.graphics.getWidth() - pressToPlay.width) / 2, Gdx.graphics.getHeight() * 0.17f);
        batch.draw(AssetsManager.getInstance().getTexture("title.png").get(), Gdx.graphics.getWidth() * 0.047f,
                Gdx.graphics.getHeight() * 0.025f, 936, 702);
        batch.end();
    }
}
