package fr.tys.emaplateforme.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.tys.emaplateforme.world.blocks.Block;
import fr.tys.emaplateforme.world.blocks.CollidableBlock;

import java.util.Optional;

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
        //enableDebugMode();
    }

    /**
     * Renders the elements of the {@link #world}
     */
    public void render() {
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
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                Optional<Block> blockOpt = world.getBlockAt(x, y);
                if (blockOpt.isPresent()) {
                    Rectangle dimensions = blockOpt.get().getDimensions();
                    batch.draw(blockOpt.get().getTexture(), x, y, dimensions.getWidth(), dimensions.getHeight());
                    if (debugMode && blockOpt.get() instanceof CollidableBlock) {
                        this.hitboxRenderer.rect(x, y, dimensions.getWidth(), dimensions.getHeight());
                    }
                }
            }
        }

        //Character
        Character emario = world.getCharacter();
        Sprite flippedEmario = new Sprite(emario.getTexture());
        flippedEmario.flip(emario.isFacingLeft(), false);
        Rectangle hitbox = emario.getHitbox();
        float characterX = emario.getPosition().x;
        float characterY = emario.getPosition().y;
        flippedEmario.setBounds(characterX, characterY, hitbox.getWidth(), hitbox.getHeight());
        flippedEmario.draw(batch);


        // Batches ending
        batch.end();
        if (debugMode) {
            hitboxRenderer.rect(characterX, characterY, hitbox.getWidth(), hitbox.getHeight());
            hitboxRenderer.end();
        }

        //Camera update
        cam.position.set(emario.getPosition().x, emario.getPosition().y + VIEWPORT_HEIGHT / 4, 0);
        cam.update();
    }

    public void dispose() {
    }

    /**
     * Sets the debug renderer on
     */
    public void enableDebugMode() {
        debugMode = true;
    }

    /**
     * Sets the debug renderer off
     */
    public void disableDebugMode() {
        debugMode = false;
    }
}
