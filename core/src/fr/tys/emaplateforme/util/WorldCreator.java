package fr.tys.emaplateforme.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.world.blocks.BlockType;
import fr.tys.emaplateforme.world.World;
import fr.tys.emaplateforme.world.blocks.Block;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorldCreator {

    /**
     * Creates and loads all blocks to create a world from a .TXT file
     *
     * @param fileName file used to set up the world (Stored in assets/world)
     * @return a world automatically filled by the file configuration
     */
    public static World loadWorldFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        Vector2 spawn = new Vector2(0, 0);

        try {
            lines = Files.readAllLines((new File("./core/assets/world/" + fileName)).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxX = 0;
        for (int y = 0; y < lines.size(); y++) {
            maxX = Math.max(lines.get(y).length(), maxX);
        }
        Block[][] blocks = new Block[maxX][lines.size()];

        for (int y = 0; y < lines.size(); y++) {
            char[] characters = lines.get(y).toCharArray();
            maxX = Math.max(characters.length, maxX);
            for (int x = 0; x < characters.length; x++) {
                Optional<BlockType> blockOpt = BlockType.getTypeByChar(characters[x]);
                if (blockOpt.isPresent()) {
                    try {
                        Constructor<? extends Block> cons = blockOpt.get().getInstantiationClass()
                                .getConstructor(Vector2.class);
                        blocks[x][lines.size() - y - 1] = cons.newInstance(new Vector2(x, lines.size() - y -1));
                    } catch (NoSuchMethodException | InstantiationException |
                            IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else if (characters[x] == 'S') {
                    spawn.set(x, lines.size() - y - 1);
                } else if (characters[x] != '\n' && characters[x] != ' ') {
                    Gdx.app.error("WORLD_GENERATION",
                            "Error during world generation - Cannot find symbol: " + characters[x]);
                }
            }
        }
        return new World(blocks, maxX, lines.size(), spawn);
    }

    /* OLD WAY TO RENDER
        int x = 0;
        int y = lines.size();

        for (String line : lines) {
            y = y - 1;
            x = 0;
            for (char c : line.toCharArray()) {
                x = x + 1;
                if (c == 'D') {
                    blocks.add(new Dirt(new Vector2(x, y)));
                }
                if (c == 'B') {
                    blocks.add(new Bricks(new Vector2(x, y)));
                }
                if (c == 'G') {
                    blocks.add(new Grass(new Vector2(x, y)));
                }
        }
     */
}