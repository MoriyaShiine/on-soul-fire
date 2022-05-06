package moriyashiine.onsoulfire.common;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class OnSoulFire implements ModInitializer {
	public static final String MOD_ID = "onsoulfire";

	private static ConfigHolder<ModConfig> config;

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class);
	}

	public static ModConfig getConfig() {
		return config.getConfig();
	}
}
