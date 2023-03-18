/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.onsoulfire.common.component.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class OnSoulFireComponent implements AutoSyncedComponent, ServerTickingComponent {
	private final Entity obj;
	private boolean onSoulFire = false;

	public OnSoulFireComponent(Entity obj) {
		this.obj = obj;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		onSoulFire = tag.getBoolean("OnSoulFire");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("OnSoulFire", onSoulFire);
	}

	@Override
	public void serverTick() {
		if (obj.getFireTicks() <= 0 && isOnSoulFire()) {
			setOnSoulFire(false);
			sync();
		}
	}

	public void sync() {
		obj.syncComponent(ModEntityComponents.ON_SOUL_FIRE);
	}

	public boolean isOnSoulFire() {
		return onSoulFire;
	}

	public void setOnSoulFire(boolean onSoulFire) {
		this.onSoulFire = onSoulFire;
	}
}
