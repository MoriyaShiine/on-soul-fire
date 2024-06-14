/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.common;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class OnSoulFire implements ModInitializer {
	public static final String MOD_ID = "onsoulfire";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, ModConfig.class);
	}

	public static Identifier id(String value) {
		return Identifier.of(MOD_ID, value);
	}
}
