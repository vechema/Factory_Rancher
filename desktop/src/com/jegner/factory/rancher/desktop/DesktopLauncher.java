package com.jegner.factory.rancher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.jegner.factory.rancher.FactoryRancherGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
        TexturePacker.process("./Player","./Output","human");
		new LwjglApplication(new FactoryRancherGame(), config);
	}
}
