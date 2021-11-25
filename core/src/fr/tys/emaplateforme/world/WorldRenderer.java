package fr.tys.emaplateforme.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.tys.emaplateforme.EMArio;
import fr.tys.emaplateforme.world.blocks.Block;

import static fr.tys.emaplateforme.util.Constants.VIEWPORT_HEIGHT;
import static fr.tys.emaplateforme.util.Constants.VIEWPORT_WIDTH;

public class WorldRenderer {

    /**
     * Game {@link World}
     */
    private World world;

    /**
     * {@link OrthographicCamera} used to show the world
     */
    private OrthographicCamera cam;

    /**
     * {@link SpriteBatch} used to render objects (blocks, entities, etc.)
     */
    private SpriteBatch batch;

    /**
     * Sets the Camera and the SpriteBatch
     *
     * @param world world to use
     */
    public WorldRenderer(World world) {
        this.world = world;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.cam.position.set(7.5F, 5.0F, 0.0F);
        this.cam.update();
    }

    /**
     * Renders the elements of the {@link #world}
     */
    public void render(float deltaTime) {
        //Background
        ScreenUtils.clear(0.0F, 0.6F, 1.0F, 1.0F);

        this.batch.setProjectionMatrix(this.cam.combined);
        this.batch.begin();

        //Blocks
        for (Block block : this.world.getBlocks()) {
            Rectangle hitbox = block.getHitbox();
            float f1 = block.getPosition().x + hitbox.x;
            float f2 = block.getPosition().y + hitbox.y;
            this.batch.draw(block.getTexture(), f1, f2, 1.0F, 1.0F);
            if (block instanceof fr.tys.emaplateforme.world.blocks.Bricks)
                this.cam.position.x = Math.min(Math.max((block.getPosition()).x, 7.5F), 22.5F);
        }

        //Character
        EMArio emario = this.world.getCharacter();
        Rectangle rect = emario.getHitbox();
        float x1 = emario.getPosition().x + rect.x;
        float y1 = emario.getPosition().y + rect.y;
        this.batch.draw(new Texture("textures/emario.png"), x1, y1, rect.getWidth(), rect.getHeight());

        this.batch.end();
        this.cam.update();
    }

    /**
     * Disposes the loaded textures on closing
     * TODO: Create an AssetsManager
     */
    public void dispose() {
        for (BlockTexture blockTexture : BlockTexture.values())
            blockTexture.getTexture().dispose();
    }
}
