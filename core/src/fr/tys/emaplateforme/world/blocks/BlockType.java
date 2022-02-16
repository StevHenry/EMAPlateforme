package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.graphics.Texture;
import fr.tys.emaplateforme.util.AssetsManager;

import java.util.Optional;
import java.util.stream.Stream;

public enum BlockType {

    GRASS("grass.png", 'G', Grass.class),
    DIRT("dirt.png", 'D', Dirt.class),
    BRICKS("bricks.png", 'B', Bricks.class),
    CLOUD("cloud.png", 'C', Cloud.class),
    UNBREAKABLE("unbreakable.png", 'U', Unbreakable.class),
    FLAG("flag.png", 'F', Flag.class);

    /**
     * Texture of the block type
     */
    private final Texture texture;
    /**
     * Character symbolizing the block type (used to design levels in TXT file)
     */
    private final char symbol;
    /**
     * Class to instantiate to create a block from the type
     */
    private final Class<? extends Block> instantiationClass;

    /**
     * @param textureFileName texture file name in assets/images folder
     * @param symbol          character used to design levels (TXT)
     * @param blockClass      instantiation class
     */
    BlockType(String textureFileName, char symbol, Class<? extends Block> blockClass) {
        this.texture = AssetsManager.getInstance().getTexture(textureFileName)
                .orElse(AssetsManager.getInstance().getTexture("0not-found-texture.png").get());
        this.symbol = symbol;
        this.instantiationClass = blockClass;
    }

    /**
     * @param character character associated to a BlockType
     * @return an {@link Optional} of BlockType that should contain the BlockType associated to the char in parameter
     */
    public static Optional<BlockType> getTypeByChar(char character) {
        return Stream.of(values()).filter(types -> types.symbol == character).findAny();
    }

    /**
     * @return the {@link Texture} of the selected BlockTexture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * @return the class of the block to instantiate
     */
    public Class<? extends Block> getInstantiationClass() {
        return instantiationClass;
    }

}
