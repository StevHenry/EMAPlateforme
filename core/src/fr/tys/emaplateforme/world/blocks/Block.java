package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.world.BlockTexture;

import static fr.tys.emaplateforme.util.Constants.UNIT_BLOCK_SIZE;

public abstract class Block {

    /**
     * Texture of the block
     */
    protected BlockTexture blockTexture;

    /**
     * Block location
     */
    protected Vector2 position;

    /**
     * Hitbox of the block with a {@link Rectangle}
     */
    private Rectangle hitbox;

    /**
     * @param pos position of the block in the {@link fr.tys.emaplateforme.world.World}
     */
    public Block(Vector2 pos) {
        this.position = pos;
        this.hitbox = new Rectangle();
        this.hitbox.setWidth(UNIT_BLOCK_SIZE);
        this.hitbox.setHeight(UNIT_BLOCK_SIZE);
    }

    /**
     * @return the position of the block in its {@link fr.tys.emaplateforme.world.World}
     */
    public Vector2 getPosition() {
        return this.position;
    }

    /**
     * @return the {@link Rectangle} of the block hitbox
     */
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    /**
     * @return the {@link Texture} of the block
     */
    public Texture getTexture() {
        return this.blockTexture.getTexture();
    }
}
