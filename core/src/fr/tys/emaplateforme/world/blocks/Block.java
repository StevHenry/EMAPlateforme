package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static fr.tys.emaplateforme.util.Constants.UNIT_BLOCK_SIZE;

public abstract class Block {

    /**
     * Block position in the world
     */
    protected Vector2 position;

    /**
     * Type of the block
     */
    protected BlockType blockType;

    /**
     * Hitbox of the block as a {@link Rectangle}
     */
    protected Rectangle dimensions;

    public Block(Vector2 pos) {
        this.dimensions = new Rectangle();
        this.dimensions.setWidth(UNIT_BLOCK_SIZE);
        this.dimensions.setHeight(UNIT_BLOCK_SIZE);
        this.position = pos;
    }

    /**
     * @return the {@link Rectangle} of the block hitbox
     */
    public Rectangle getDimensions() {
        return this.dimensions;
    }

    /**
     * @return the {@link Texture} of the block
     */
    public Texture getTexture() {
        return this.blockType.getTexture();
    }

    /**
     * @return the {@link Vector2} of the position of the block
     */
    public Vector2 getPosition(){
        return position;
    }

    /**
     * @return the {@link BlockType}
     */
    public BlockType getBlockType(){
        return blockType;
    }

}
