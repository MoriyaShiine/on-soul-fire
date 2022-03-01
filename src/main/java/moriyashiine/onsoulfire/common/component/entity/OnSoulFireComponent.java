package moriyashiine.onsoulfire.common.component.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.onsoulfire.common.registry.ModComponents;
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
		setOnSoulFire(tag.getBoolean("OnSoulFire"));
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("OnSoulFire", isOnSoulFire());
	}

	@Override
	public void serverTick() {
		if (obj.getFireTicks() <= 0 && isOnSoulFire()) {
			setOnSoulFire(false);
		}
	}

	public boolean isOnSoulFire() {
		return onSoulFire;
	}

	public void setOnSoulFire(boolean onSoulFire) {
		this.onSoulFire = onSoulFire;
		ModComponents.ON_SOUL_FIRE_COMPONENT.sync(obj);
	}
}
