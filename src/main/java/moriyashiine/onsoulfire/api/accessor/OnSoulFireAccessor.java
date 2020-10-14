package moriyashiine.onsoulfire.api.accessor;

import net.minecraft.entity.Entity;

import java.util.Optional;

public interface OnSoulFireAccessor {
	static Optional<OnSoulFireAccessor> of(Entity entity) {
		if (entity instanceof OnSoulFireAccessor) {
			return Optional.of((OnSoulFireAccessor) entity);
		}
		return Optional.empty();
	}
	
	boolean getOnSoulFire();
	
	void setOnSoulFire(boolean onSoulFire);
}
