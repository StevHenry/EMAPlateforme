package fr.tys.emaplateforme.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.util.AssetsManager;

import static fr.tys.emaplateforme.util.Constants.PLAYER_SIZE_HEIGHT;
import static fr.tys.emaplateforme.util.Constants.PLAYER_SIZE_WIDTH;


public class Character {

    /**
     * Character acceleration
     */
    private final Vector2 acceleration = new Vector2();
    /**
     * Character speed
     */
    private final Vector2 velocity = new Vector2();

    /**
     * Location in the world
     */
    private final Vector2 position;

    /**
     * Variable used to manipulate vectors without initiating new {@link Vector2} objects
     */
    private final Vector2 tempMultiplier;

    /**
     * Orientation of the character
     */
    private boolean facingLeft = false;

    /**
     * Character state during the game
     */
    private CharacterState state = CharacterState.IDLE;

    /**
     * Hitbox stored as a {@link Rectangle}
     */
    private Rectangle hitbox = new Rectangle();

    /**
     * Character texture
     */
    private final Texture texture;

    /**
     * @param position default position when the map is started
     */
    public Character(Vector2 position) {
        this.position = position;
        this.tempMultiplier = new Vector2(0, 0);
        this.hitbox.height = PLAYER_SIZE_HEIGHT;
        this.hitbox.width = PLAYER_SIZE_WIDTH;
        this.texture = AssetsManager.getInstance().getTexture("emario.png")
                .orElse(AssetsManager.getInstance().getTexture("0not-found-texture.png").get());
    }

    // Movement
    /**
     * @return the position of the character as a {@link Vector2}
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * @return the position of the character as a {@link Vector2}
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * @return the position of the character as a {@link Vector2}
     */
    public Vector2 getAcceleration() {
        return acceleration;
    }


    // Collision
    /**
     * @return the hitbox of the character as a {@link Rectangle}
     */
    public Rectangle getHitbox() {
        return this.hitbox;
    }


    //Visuals
    /**
     * @return the Texture of the character
     */
    public Texture getTexture() {
        return texture;
    }

    // Interactions

    /**
     * @param comparison compared state
     * @return whether the current state is equal to the compared one
     */
    public boolean isState(CharacterState comparison) {
        return state == comparison;
    }

    /**
     * Sets a new state for the character
     */
    public void setState(CharacterState state) {
        this.state = state;
    }

    /**
     * @return whether the character is facing left
     */
    public boolean isFacingLeft() {
        return facingLeft;
    }

    /**
     * Makes the character look to the left
     */
    public void setFacingLeft() {
        this.facingLeft = true;
    }

    /**
     * Makes the character look to the right
     */
    public void setFacingRight() {
        this.facingLeft = false;
    }

    /**
     * Updates the player position from the velocity
     * @param delta time between two frames of the game
     */
    public void update(float delta) {
        //velocity.scl(delta);
        tempMultiplier.set(velocity.x * delta, velocity.y * delta);
        position.add(velocity);
    }

    public enum CharacterState {
        IDLE, WALKING, JUMPING
    }
}