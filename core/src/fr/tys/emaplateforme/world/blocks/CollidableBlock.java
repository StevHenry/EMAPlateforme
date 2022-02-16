package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public abstract class CollidableBlock extends Block {

    protected final Rectangle hitbox;

    public CollidableBlock(Vector2 position) {
        super(position);
        this.hitbox = new Rectangle(0, 0, dimensions.getWidth(), dimensions.getHeight());
    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}
