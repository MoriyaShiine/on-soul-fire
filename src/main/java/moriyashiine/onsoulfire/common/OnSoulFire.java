/*
 * All Rights Reserved (c) 2022 MoriyaShiine
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
		return new Identifier(MOD_ID, value);
	}
}
