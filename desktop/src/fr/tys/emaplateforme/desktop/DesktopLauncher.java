package fr.tys.emaplateforme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.tys.emaplateforme.EMAPlateforme;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "EMAPlateforme";
        config.width = 800;
        config.height = 300;
        new LwjglApplication(new EMAPlateforme(), config);
    }
}
