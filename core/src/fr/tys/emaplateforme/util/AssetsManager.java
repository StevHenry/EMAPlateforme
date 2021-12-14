package fr.tys.emaplateforme.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AssetsManager {


    private static AssetsManager instance;
    public static AssetsManager getInstance(){
        return instance == null ? new AssetsManager() : instance;
    }

    private final Map<String, Texture> textures;

    AssetsManager() {
        instance = this;
        textures = new HashMap<>();
        loadTextures();
    }

    /**
     * Loads all the textures contained in the assets/images folder
     */
    @SuppressWarnings("ConstantConditions")
    private void loadTextures() {
        for (File f : new File(getClass().getClassLoader().getResource("textures").getPath()).listFiles()) {
            textures.put(f.getName(), new Texture(Gdx.files.internal("textures/" + f.getName())));
            Gdx.app.debug("TEXTURE LOADING", "Successfully loaded texture called " + f.getName());
        }
    }

    /**
     * Disposes the loaded textures
     */
    public void unloadTextures(){
        textures.values().forEach(Texture::dispose);
    }

    /**
     * @param key file name with extension
     * @return an {@link Optional} the texture linked to its file name
     */
    public Optional<Texture> getTexture(String key){
        return Optional.ofNullable(textures.get(key));
    }
}
