package moriyashiine.onsoulfire;

import moriyashiine.onsoulfire.interfaces.OnSoulFireAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class OnSoulFire implements ModInitializer {
	@Override
	public void onInitialize() {
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			if (alive) {
				((OnSoulFireAccessor) newPlayer).setOnSoulFire(((OnSoulFireAccessor) oldPlayer).getOnSoulFire());
			}
		});
	}
}
