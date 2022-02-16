package fr.tys.emaplateforme.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AssetsManager {

    private static AssetsManager instance;
    private final Map<String, Texture> textures;
    private final BitmapFont font;

    AssetsManager() {
        instance = this;
        textures = new HashMap<>();
        font = loadFont();
        loadTextures();
    }

    public static AssetsManager getInstance() {
        return instance == null ? new AssetsManager() : instance;
    }

    /**
     * Loads all the textures contained in the assets/images folder
     */
    @SuppressWarnings("ConstantConditions")
    private void loadTextures() {
        for (File f : new File(getClass().getClassLoader().getResource("textures").getPath()).listFiles()) {
            loadFile("textures", f);
        }
    }

    /**
     * Loads a file in the {@link #textures} map, and calls itself when the file is a directory
     *
     * @param path to the linked file
     * @param file that needs to be load
     */
    private void loadFile(String path, File file) {
        if (!file.isDirectory()) {
            textures.put(file.getName(), new Texture(Gdx.files.internal(path + "/" + file.getName())));
            Gdx.app.debug("TEXTURE LOADING", "Successfully loaded texture called " + file.getName());
        } else {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                loadFile(path + "/" + file.getName(), f);
            }
        }
    }

    /**
     * Loads the font used in all the scenes
     *
     * @return the loaded font in size 30
     */
    private BitmapFont loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Charge Vector.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30; // font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    /**
     * Disposes the loaded textures
     */
    public void unloadTextures() {
        textures.values().forEach(Texture::dispose);
    }

    /**
     * @param key file name with extension
     * @return an {@link Optional} the texture linked to its file name
     */
    public Optional<Texture> getTexture(String key) {
        return Optional.ofNullable(textures.get(key));
    }

    /**
     * @return the font for the scenes
     */
    public BitmapFont getFont() {
        return font;
    }
}
