package fr.tys.emaplateforme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class EMAPlateforme extends Game {

    SpriteBatch batch;
    Texture screenImg, bucketImage;
    Rectangle bucket;

    @Override
    public void create() {
        batch = new SpriteBatch();
        screenImg = new Texture("badlogic.jpg");
        bucketImage = new Texture("bucket.png");
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0f, 0.3f, 1);
        batch.begin();
        batch.draw(screenImg, 0, 0, 800, 300);
        //batch.draw(img, 0, 0);
        batch.draw(bucketImage, bucket.x, bucket.y, 50, 50);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        screenImg.dispose();
        bucketImage.dispose();
    }
}
