package fr.tys.emaplateforme;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EMArio {

    /**
     * Orientation of the character
     */
    boolean facingLeft = true;
    /**
     * Location in the world
     */
    private Vector2 position = new Vector2();

    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();

    /**
     * Hitbox stored as a {@link Rectangle}
     */
    private Rectangle hitbox = new Rectangle();

    /**
     * Character state during the game
     */
    private CharacterState state = CharacterState.IDLE;

    /**
     * @param position default position when the map is started
     */
    public EMArio(Vector2 position) {
        this.position = position;
        this.hitbox.height = 1.0F;
        this.hitbox.width = 1.0F;
    }

    /**
     * @return the hitbox of the character as a {@link Rectangle}
     */
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    /**
     * @return the position of the character
     */
    public Vector2 getPosition() {
        return this.position;
    }

    public enum CharacterState {
        IDLE, WALKING, JUMPING, DYING;
    }
}
