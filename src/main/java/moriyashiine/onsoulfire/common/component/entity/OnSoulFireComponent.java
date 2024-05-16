/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.onsoulfire.common.component.entity;

import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class OnSoulFireComponent implements AutoSyncedComponent, ServerTickingComponent {
	private final Entity obj;
	private boolean onSoulFire = false;
	private int syncTicks = 0;

	public OnSoulFireComponent(Entity obj) {
		this.obj = obj;
	}

	@Override
	public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
		onSoulFire = tag.getBoolean("OnSoulFire");
		syncTicks = tag.getInt("SyncTicks");
	}

	@Override
	public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
		tag.putBoolean("OnSoulFire", onSoulFire);
		tag.putInt("SyncTicks", syncTicks);
	}

	@Override
	public void serverTick() {
		if (isOnSoulFire() && obj.getFireTicks() <= 0) {
			setOnSoulFire(false);
			syncTicks = 3;
		}
		if (syncTicks > 0 && --syncTicks == 0) {
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
