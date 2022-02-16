package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;

public class Bricks extends CollidableBlock {

    public Bricks(Vector2 position) {
        super(position);
        this.blockType = BlockType.BRICKS;
    }

}
