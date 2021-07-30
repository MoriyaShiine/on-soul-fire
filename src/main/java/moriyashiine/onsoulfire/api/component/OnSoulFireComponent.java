package moriyashiine.onsoulfire.api.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.onsoulfire.common.registry.OSFComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

public class OnSoulFireComponent implements AutoSyncedComponent, ServerTickingComponent {
	private final Entity entity;
	private boolean onSoulFire = false;
	
	public OnSoulFireComponent(Entity entity) {
		this.entity = entity;
	}
	
	public boolean isOnSoulFire() {
		return onSoulFire;
	}
	
	public void setOnSoulFire(boolean onSoulFire) {
		this.onSoulFire = onSoulFire;
		OSFComponents.ON_SOUL_FIRE_COMPONENT.sync(entity);
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
		if (entity.getFireTicks() <= 0 && isOnSoulFire()) {
			setOnSoulFire(false);
		}
	}
	
	public static OnSoulFireComponent get(Entity entity) {
		return OSFComponents.ON_SOUL_FIRE_COMPONENT.get(entity);
	}
	
	@SuppressWarnings("unused")
	public static Optional<OnSoulFireComponent> maybeGet(Entity entity) {
		return OSFComponents.ON_SOUL_FIRE_COMPONENT.maybeGet(entity);
	}
}
