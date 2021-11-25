package fr.tys.emaplateforme.util;

public class Constants {

    /**
     * Unit size of a {@link fr.tys.emaplateforme.world.blocks.Block},
     * elementary elements for a {@link fr.tys.emaplateforme.world.World}
     */
    public static final float UNIT_BLOCK_SIZE = 1.0F;

    /**
     * Count of {@link fr.tys.emaplateforme.world.blocks.Block}s
     * horizontally aligned in a {@link fr.tys.emaplateforme.world.World}
     */
    public static final float WORLD_WIDTH = 30.0F;

    /**
     * Count of {@link fr.tys.emaplateforme.world.blocks.Block}s
     * vertically aligned in a {@link fr.tys.emaplateforme.world.World}
     */
    public static final float WORLD_HEIGHT = 10.0F;

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
    public static final float PLAYER_MOVING_SPEED = 2.0F;

    /**
     * Blocks per second for a moving character vertically
     */
    public static final float PLAYER_JUMPING_SPEED = 1.0F;

    /**
     * Player size ratio
     */
    public static final float PLAYER_SIZE = 1.0F;
}
