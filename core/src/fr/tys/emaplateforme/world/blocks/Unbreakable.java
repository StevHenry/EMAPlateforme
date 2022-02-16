package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;

public class Unbreakable extends CollidableBlock {

    public Unbreakable(Vector2 position) {
        super(position);
        this.blockType = BlockType.UNBREAKABLE;
    }
}
