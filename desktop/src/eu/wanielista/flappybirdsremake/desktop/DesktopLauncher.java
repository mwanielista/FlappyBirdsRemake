package eu.wanielista.flappybirdsremake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu.wanielista.flappybirdsremake.FlyingBirds;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlyingBirds.WIDTH;
		config.height = FlyingBirds.HEIGHT;
		config.title = FlyingBirds.TITLE;
		new LwjglApplication(new FlyingBirds(), config);
	}
}
