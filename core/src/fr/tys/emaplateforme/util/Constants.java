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
    public static final float VIEWPORT_WIDTH = 15.0F;

    /**
     * Count of {@link fr.tys.emaplateforme.world.blocks.Block}s vertically show by the camera
     */
    public static final float VIEWPORT_HEIGHT = 10.0F;

    /**
     * Blocks per second for a moving character horizontally
     */
    public static final float PLAYER_MAXIMUM_MOVING_SPEED = 0.1F;

    /**
     * Blocks per second for a moving character vertically
     */
    public static final float PLAYER_MAXIMUM_JUMPING_SPEED = 0.15F;

    /**
     * Player width
     */
    public static final float PLAYER_SIZE_WIDTH = 0.75F;

    /**
     * Player height (calculated with the ratio of the image compared to {@link #PLAYER_SIZE_WIDTH})
     */
    public static final float PLAYER_SIZE_HEIGHT = 1.62F;

    /**
     * Gravity factor value
     * Negative because gravity sticks you towards the ground
     */
    public static final float GRAVITY = -1.81F;

    /**
     * Maximum time with key pressed to jump
     */
    public static final long LONG_JUMP_PRESS = 200L;

    /**
     * Maximum value of acceleration
     */
    public static final float ACCELERATION = 20F;

    /**
     * Factor used to calculate the inertia of our player
     * (equivalent to a friction factor multiplied to the speed)
     */
    public static final float DAMP = 0.7F;
}
