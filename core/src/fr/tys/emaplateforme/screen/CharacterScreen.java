package fr.tys.emaplateforme.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.util.AssetsManager;
import fr.tys.emaplateforme.world.Character;

public class CharacterScreen extends ScreenAdapter {

    private static int choice;
    private final EMAPlateforme game;
    private final SpriteBatch batch;
    private final Character character;


    public CharacterScreen(EMAPlateforme game, Character character) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.character = character;
    }

    public static int getChoice() {
        return choice;
    }

    @Override
    public void show() {
        choice = 0;
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode >= 7 && keyCode <= 13) {
                    choice = keyCode - 7;
                } else if (keyCode >= 144 && keyCode <= 150) {
                    choice = keyCode - 144;
                }
                if (choice != 0) {
                    character.updateTexture();
                    character.resetDeathCount();
                    character.land();
                    game.setGameScreen();
                }
                return true;
            }
        });
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        int charactersWidth = 900;

        batch.draw(AssetsManager.getInstance().getTexture("characters.png").get(),
                Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.13f, charactersWidth, 314);
        batch.draw(AssetsManager.getInstance().getTexture("choose.png").get(),
                Gdx.graphics.getWidth() * 0.29f, Gdx.graphics.getHeight() * 0.6f, 250, 81);
        BitmapFont font = AssetsManager.getInstance().getFont();
        for (int i = 0; i < 6; i++) {
            font.draw(batch, "PRESS " + (i + 1), (Gdx.graphics.getHeight() * 0.13f)
                    + charactersWidth / 6f * i, Gdx.graphics.getHeight() * 0.1f);
        }
        batch.end();
    }
}
