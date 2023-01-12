package com.thejourneyofone.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.thejourneyofone.game.Main;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
	public static void main(String[] args) {
		pack();
		createApplication();
	}

	public static void pack() {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxHeight = 4096; settings.maxWidth = 2048;
		settings.pot = false;
		TexturePacker.process(settings, "textures", "packed", "game");
	}

	private static LwjglApplication createApplication() {
		return new LwjglApplication(new Main(), getDefaultConfiguration());
	}

	private static LwjglApplicationConfiguration getDefaultConfiguration() {
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		configuration.title = "TheJourneyOfOne";
		configuration.width = 640;
		configuration.height = 480;
		//// This prevents a confusing error that would appear after exiting normally.
		configuration.forceExit = false;

		for (int size : new int[] { 128, 64, 32, 16 }) {
			configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
		}
		return configuration;
	}
}