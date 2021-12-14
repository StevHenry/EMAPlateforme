package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.world.BlockType;

public class Bricks extends Block {

    public Bricks(Vector2 pos) {
        super(pos);
        this.blockType = BlockType.BRICKS;
    }
}
