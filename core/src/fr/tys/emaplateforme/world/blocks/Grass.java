package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;

public class Grass extends CollidableBlock {

    public Grass(Vector2 position) {
        super(position);
        this.blockType = BlockType.GRASS;
    }
}
