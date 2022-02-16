package fr.tys.emaplateforme.util;

public class Constants {

    /**
     * Unit size of a {@link fr.tys.emaplateforme.world.blocks.Block},
     * elementary elements for a {@link fr.tys.emaplateforme.world.World}
     */
    public static final float UNIT_BLOCK_SIZE = 1.0F;

    /**
     * Count of {@link fr.tys.emaplateforme.world.blocks.Block}s horizontally show by the camera
     */
    public static final float VIEWPORT_WIDTH = 8F;

    /**
     * Count of {@link fr.tys.emaplateforme.world.blocks.Block}s vertically show by the camera
     */
    public static final float VIEWPORT_HEIGHT = 6F;

    /**
     * Blocks per second for a moving character horizontally
     */
    public static final float PLAYER_MAXIMUM_MOVING_SPEED = 3.5F;

    /**
     * Blocks per second for a moving character vertically
     */
    public static final float PLAYER_MAXIMUM_JUMPING_SPEED = 4F;

    /**
     * Player width (calculated with the ratio of the image compared to {@link #PLAYER_HEIGHT})
     */
    public static final float PLAYER_WIDTH = 0.406F;

    /**
     * Player height
     */
    public static final float PLAYER_HEIGHT = 0.9F;

    /**
     * Gravity factor value
     * Negative because gravity sticks you towards the ground
     */
    public static final float GRAVITY = -9.81F;

    /**
     * Maximum time with key pressed to jump in ms
     */
    public static final long MAXIMUM_JUMP_TIME_PRESS = 140L;

    /**
     * Maximum value of acceleration sideways
     */
    public static final float SIDEWAYS_ACCELERATION = 3F;

    /**
     * Factor used to calculate the inertia of our player
     * (equivalent to a friction factor multiplied to the speed)
     * Has to be between 0 and 1
     */
    public static final float DAMP = 0.75F;
}
