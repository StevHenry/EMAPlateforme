package fr.tys.emaplateforme.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.world.blocks.Block;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class World {

    /**
     * Width and height of the loaded world
     */
    private final float width, height;

    /**
     * Position where the character is spawning
     */
    private final Vector2 spawn;

    /**
     * 2D array of the blocks contained in the World
     */
    private final Block[][] blocks;

    /**
     * Character used to play
     */
    private final Character emario;

    /**
     * @param blocks {@link List} of {@link Block} that the world contains
     * @param width  horizontal size of the world
     * @param height vertical size of the world
     */
    public World(Block[][] blocks, float width, float height, Vector2 spawn) {
        this.blocks = blocks;
        //createDemoWorld();
        this.emario = new Character(spawn);
        this.width = width;
        this.height = height;
        this.spawn = spawn;
    }

    /**
     * @param x : x position
     * @param y : y position
     * @return an {@link Optional} of {@link Block} containing the block located atthe provided (x,y)
     */
    public Optional<Block> getBlockAt(int x, int y) {
        if (x >= 0 && x < blocks.length && y >= 0 && y < blocks[0].length) {
            return Optional.ofNullable(blocks[x][y]);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param x : x position
     * @param y : y position
     * Casts all float to int and calls {@link #getBlockAt(int, int)}
     */
    public Optional<Block> getBlockAt(float x, float y){
        return getBlockAt((int)x, (int)y);
    }

    /**
     * @return the character to play
     */
    public Character getCharacter() {
        return this.emario;
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

    /**
     * @return the Spawn coordinates
     */
    public Vector2 getSpawn(){
        return spawn;
    }
}
