package moriyashiine.onsoulfire.api.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.onsoulfire.common.registry.OSFComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

public class OnSoulFireComponent implements AutoSyncedComponent, ServerTickingComponent {
	private final Entity obj;
	private boolean onSoulFire = false;
	
	public OnSoulFireComponent(Entity obj) {
		this.obj = obj;
	}
	
	public boolean isOnSoulFire() {
		return onSoulFire;
	}
	
	public void setOnSoulFire(boolean onSoulFire) {
		this.onSoulFire = onSoulFire;
		OSFComponents.ON_SOUL_FIRE_COMPONENT.sync(obj);
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
	
	public static OnSoulFireComponent get(Entity obj) {
		return OSFComponents.ON_SOUL_FIRE_COMPONENT.get(obj);
	}
	
	@SuppressWarnings("unused")
	public static Optional<OnSoulFireComponent> maybeGet(Entity obj) {
		return OSFComponents.ON_SOUL_FIRE_COMPONENT.maybeGet(obj);
	}
}
