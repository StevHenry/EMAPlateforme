package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.tys.emaplateforme.EMAPlateforme;

public class TitleScreen extends ScreenAdapter {

    private EMAPlateforme game;
    private SpriteBatch batch;
    private BitmapFont font;

    public TitleScreen(EMAPlateforme game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Title Screen!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * 0.75f);
        font.draw(batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * 0.25f);
        batch.end();
    }
}
