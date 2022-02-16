package fr.tys.emaplateforme.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.screen.CharacterScreen;
import fr.tys.emaplateforme.util.AssetsManager;

import static fr.tys.emaplateforme.util.Constants.PLAYER_HEIGHT;
import static fr.tys.emaplateforme.util.Constants.PLAYER_WIDTH;


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
     * Character texture
     */
    private Texture texture;

    /**
     * Saves the previous movement characteristics in order to cancel it
     */
    private final Vector2 oldAcceleration, oldVelocity, oldPosition;

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
    private final Rectangle hitbox = new Rectangle();

    /**
     * Used to tell if the character is jumping
     */
    private boolean jumping;

    /**
     * Count of deaths
     */
    private int deathCount = 0;

    /**
     * @param position default position when the map is started
     */
    public Character(Vector2 position) {
        this.position = new Vector2(position);
        this.hitbox.height = PLAYER_HEIGHT;
        this.hitbox.width = PLAYER_WIDTH;
        this.oldAcceleration = new Vector2(0, 0);
        this.oldVelocity = new Vector2(0, 0);
        this.oldPosition = new Vector2(position);
    }

    /**
     * Method to call when the character appearance is chosen
     */
    public void updateTexture(){
        this.texture = AssetsManager.getInstance().getTexture("emario" + CharacterScreen.getChoice() + ".png")
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
     * @return the velocity of the character as a {@link Vector2}
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * @return the acceleration of the character as a {@link Vector2}
     */
    public Vector2 getAcceleration() {
        return acceleration;
    }

    /**
     * Cancels the player movement on the X axis
     */
    public void cancelMovementX() {
        this.acceleration.set(0, acceleration.y);
        this.velocity.set(velocity.x * -1F, velocity.y);
        this.position.set(oldPosition.x + (velocity.x < 0 ? -1f : 1f) * 0.001F, position.y);
        //Prevent from jumping
        jumping = true;
    }

    /**
     * Cancels the player movement on the Y axis
     */
    public void cancelMovementY() {
        this.acceleration.set(acceleration.x, 0);
        this.velocity.set(velocity.x, 0);
        this.position.set(position.x, oldPosition.y);
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
     * @return whether the character is jumping
     */
    public boolean isJumping() {
        return this.jumping;
    }

    /**
     * Sets the {@link #jumping} variable to true
     */
    public void jump() {
        this.jumping = true;
    }

    /**
     * Sets the {@link #jumping} variable to false
     */
    public void land() {
        this.jumping = false;
    }

    /**
     * @return the death count
     */
    public int getDeathCount(){
        return deathCount;
    }

    /**
     * Adds 1 to the death count
     */
    public void incrementDeath() {
        deathCount++;
    }

    /**
     * Sets to 0 the death count
     */
    public void resetDeathCount() {
        deathCount = 0;
    }

    /**
     * Updates the player position from the velocity
     */
    public void update() {
        oldAcceleration.set(getAcceleration());
        oldVelocity.set(getVelocity());
        oldPosition.set(getPosition());
    }

    public enum CharacterState {
        IDLE, MOVING
    }
}