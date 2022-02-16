package fr.tys.emaplateforme.world.blocks;

import com.badlogic.gdx.math.Vector2;
import fr.tys.emaplateforme.EMAPlateforme;
import fr.tys.emaplateforme.util.Constants;
import fr.tys.emaplateforme.world.Character;

public class Flag extends Block implements InteractiveBlock {

    public Flag(Vector2 position) {
        super(position);
        this.blockType = BlockType.FLAG;
        this.dimensions.setHeight(6 * Constants.UNIT_BLOCK_SIZE);
    }

    @Override
    public void interact(EMAPlateforme game) {
        game.pause();
        game.setEndScreen();
    }
}
