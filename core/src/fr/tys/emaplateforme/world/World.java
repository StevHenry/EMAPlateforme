package fr.tys.emaplateforme.world;

import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.EMArio;
import fr.tys.emaplateforme.world.blocks.Block;
import fr.tys.emaplateforme.world.blocks.Bricks;
import fr.tys.emaplateforme.world.blocks.Dirt;
import fr.tys.emaplateforme.world.blocks.Grass;

import java.util.ArrayList;
import java.util.List;

public class World {

    /**
     * {@link ArrayList} of the blocks contained in the World
     */
    private List<Block> blocks = new ArrayList<>();

    /**
     * Character to play
     */
    private EMArio emario;

    public World() {
        createDemoWorld();
    }

    /**
     * @return a {@link List} of the blocks of the wolrd
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * @return the character to play
     */
    public EMArio getCharacter() {
        return this.emario;
    }

    /**
     * Creates a testing world
     */
    public void createDemoWorld() {
        this.emario = new EMArio(new Vector2(7.0F, 1.0F));

        for (int i = 0; i < 10; i++) {
            this.blocks.add(new Grass(new Vector2(i, 0.0F)));
            this.blocks.add(new Grass(new Vector2(i, 7.0F)));
        }

        Bricks bricks = new Bricks(new Vector2(9.0F, 2.0F));
        this.blocks.add(bricks);
        this.blocks.add(new Dirt(new Vector2(9.0F, 3.0F)));
        this.blocks.add(new Dirt(new Vector2(9.0F, 4.0F)));
        this.blocks.add(new Dirt(new Vector2(9.0F, 5.0F)));
        this.blocks.add(new Dirt(new Vector2(6.0F, 3.0F)));
        this.blocks.add(new Dirt(new Vector2(6.0F, 4.0F)));
        this.blocks.add(new Dirt(new Vector2(6.0F, 5.0F)));
    }
}
