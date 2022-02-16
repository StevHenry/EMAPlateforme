package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;

public class Dirt extends CollidableBlock {

    public Dirt(Vector2 position) {
        super(position);
        this.blockType = BlockType.DIRT;
    }
}
