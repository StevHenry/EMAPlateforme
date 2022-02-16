package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;

public class Cloud extends Block {

    public Cloud(Vector2 position) {
        super(position);
        this.blockType = BlockType.CLOUD;
    }
}
