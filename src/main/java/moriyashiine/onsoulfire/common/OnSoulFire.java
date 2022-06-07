/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.onsoulfire.common;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

public class OnSoulFire implements ModInitializer {
	public static final String MOD_ID = "onsoulfire";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, ModConfig.class);
	}
}
