package fr.tys.emaplateforme.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.tys.emaplateforme.world.blocks.Block;

import static fr.tys.emaplateforme.util.Constants.VIEWPORT_HEIGHT;
import static fr.tys.emaplateforme.util.Constants.VIEWPORT_WIDTH;

public class WorldRenderer {

    /**
     * Game {@link World}
     */
    private World world;

    /**
     * {@link OrthographicCamera} used to visualize the world
     */
    private OrthographicCamera cam;

    /**
     * {@link SpriteBatch} used to render objects (blocks, entities, etc.)
     */
    private SpriteBatch batch;

    /**
     * {@link ShapeRenderer} used to render objects' hitboxes (for debug)
     */
    private ShapeRenderer hitboxRenderer;

    /**
     * DDebug mode activation state
     */
    private boolean debugMode = false;

    /**
     * Texture of EMArio going left
     */
    private Sprite flippedEmario;

    /**
     * Defines the Camera and the SpriteBatch
     *
     * @param world world to use
     */
    public WorldRenderer(World world) {
        this.world = world;
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.hitboxRenderer = new ShapeRenderer();

        Character emario = this.world.getCharacter();
        this.cam.position.set(emario.getPosition().x, emario.getPosition().y, 0);
        this.cam.update();

        flippedEmario = new Sprite(emario.getTexture());
        flippedEmario.flip(true, false);
    }

    /**
     * Renders the elements of the {@link #world}
     */
    public void render(float deltaTime) {
        //Background
        ScreenUtils.clear(0.0F, 0.6F, 1.0F, 1.0F);

        // Batches initialization
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        if (debugMode) {
            hitboxRenderer.setProjectionMatrix(cam.combined);
            hitboxRenderer.setAutoShapeType(true);
            hitboxRenderer.begin();
            hitboxRenderer.setColor(1F, 0F, 0F, 1F);
        }

        //Blocks
        for (Block block : world.getAllBlocks()) {
            Rectangle hitbox = block.getHitbox();
            float x = block.getPosition().x + hitbox.x;
            float y = block.getPosition().y + hitbox.y;
            batch.draw(block.getTexture(), x, y, 1.0F, 1.0F);

            if (debugMode)
                this.hitboxRenderer.rect(x, y, hitbox.getWidth(), hitbox.getHeight());
        }

        //Character
        Character emario = world.getCharacter();
        Rectangle hitbox = emario.getHitbox();
        float characterX = emario.getPosition().x;
        float characterY = emario.getPosition().y;
        if (emario.isFacingLeft()) {
            flippedEmario.setBounds(characterX, characterY, hitbox.getWidth(), hitbox.getHeight());
            flippedEmario.draw(batch);
        } else {
            batch.draw(emario.getTexture(), characterX, characterY, hitbox.getWidth(), hitbox.getHeight());
        }

        // Batches ending
        batch.end();
        if (debugMode) {
            hitboxRenderer.rect(characterX, characterY, hitbox.getWidth(), hitbox.getHeight());
            hitboxRenderer.end();
        }

        //Camera update
        cam.position.set(emario.getPosition().x, emario.getPosition().y + 3, 0);
        cam.update();
    }

    public void dispose() {}

    public void enableDebugMode() {
        debugMode = true;
    }

    public void disableDebugMode() {
        debugMode = false;
    }
}
