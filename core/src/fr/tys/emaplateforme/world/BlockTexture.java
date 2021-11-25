package fr.tys.emaplateforme.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum BlockTexture {

    GRASS("grass.png"),
    DIRT("dirt.png"),
    BRICKS("bricks.png");

    private final Texture texture;

    BlockTexture(String textureFileName) {
        this.texture = new Texture(Gdx.files.internal("textures/" + textureFileName));
        Gdx.app.debug("TEXTURE LOADING", "Successfully loaded texture of " + this.name() + " block");
    }

    /**
     * @return the {@link Texture} of the selected BlockTexture
     */
    public Texture getTexture() {
        return this.texture;
    }
}
