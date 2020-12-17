package eu.wanielista.flappybirdsremake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu.wanielista.flappybirdsremake.FlappyBirdsRemake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBirdsRemake.WIDTH;
		config.height = FlappyBirdsRemake.HEIGHT;
		config.title = FlappyBirdsRemake.TITLE;
		new LwjglApplication(new FlappyBirdsRemake(), config);
	}
}
