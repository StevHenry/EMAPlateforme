package fr.tys.emaplateforme.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.world.blocks.Block;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.tys.emaplateforme.util.Constants.VIEWPORT_HEIGHT;
import static fr.tys.emaplateforme.util.Constants.VIEWPORT_WIDTH;

public class World {

    /**
     * Width and height of the loaded world
     */
    private final float width, height;

    /**
     * Set of the objects using hitboxes
     */
    private Set<Rectangle> collisionShapes = new HashSet<>();

    /**
     * {@link List} of the blocks contained in the World
     */
    private List<Block> blocks;

    /**
     * Character used to play
     */
    private Character emario;

    /**
     * @param blocks {@link List} of {@link Block} that the world contains
     * @param width  horizontal size of the world
     * @param height vertical size of the world
     */
    public World(List<Block> blocks, float width, float height) {
        this.blocks = blocks;
        //createDemoWorld();
        this.emario = new Character(new Vector2(7.0F, 1.0F));
        this.width = width;
        this.height = height;
    }

    /**
     * @return the attribute blocks containing all blocks of the map
     */
    public List<Block> getAllBlocks(){
        return blocks;
    }

    /**
     * @return a {@link List} of the blocks of the world
     */
    public List<Block> getDrawableBlocks() {
        int x1 = (int) Math.max(emario.getPosition().x - VIEWPORT_WIDTH / 2, 0);
        int y1 = (int) Math.max(emario.getPosition().y - VIEWPORT_HEIGHT / 2, 0);
        int x2 = (int) Math.min(x1 + VIEWPORT_WIDTH, VIEWPORT_WIDTH - 1);
        int y2 = (int) Math.min(y1 + 2 * VIEWPORT_HEIGHT, VIEWPORT_HEIGHT - 1);

        return blocks.stream().filter(block -> block.getPosition().x >= x1 &&
                        block.getPosition().x <= x2 &&
                        block.getPosition().y <= y1 &&
                        block.getPosition().y >= y2)
                .collect(Collectors.toList());
    }

    /**
     * @return an {@link Optional} of {@link Block} containing the block located atthe provided (x,y)
     * @param x : x position
     * @param y : y position
     */
    public Optional<Block> getBlockAt(float x, float y) {
        return blocks.stream().filter(block -> block.getPosition().x == x && block.getPosition().y == y).findAny();
    }

    /**
     * @return the character to play
     */
    public Character getCharacter() {
        return this.emario;
    }

    /**
     * @return all the hitboxes in the world
     */
    public Set<Rectangle> getCollisionShapes() {
        return collisionShapes;
    }

    /**
     * @return the width of the world
     */
    public float getWidth() {
        return width;
    }

    /**
     * @return the height of the world
     */
    public float getHeight() {
        return height;
    }
}
