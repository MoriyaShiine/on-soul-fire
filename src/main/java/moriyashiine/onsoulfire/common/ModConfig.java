package moriyashiine.onsoulfire.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = OnSoulFire.MOD_ID)
public class ModConfig implements ConfigData {
	public float onSoulFireDamageMultiplier = 2;
	public float soulFireBlockDamageMultiplier = 1;
}
