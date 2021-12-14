package fr.tys.emaplateforme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.tys.emaplateforme.EMAPlateforme;

import java.awt.*;

public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "EMAPlateforme";
        config.foregroundFPS = 120;
        //Window size is set to 3/4 of the screen size by default
        float ratio = 0.75f;
        config.width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * ratio);
        config.height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * ratio);
        new LwjglApplication(new EMAPlateforme(), config);
    }

}


