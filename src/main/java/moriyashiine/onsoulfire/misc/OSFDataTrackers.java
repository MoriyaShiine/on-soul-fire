package moriyashiine.onsoulfire.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class OSFDataTrackers {
	public static final TrackedData<Boolean> ON_SOUL_FIRE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	public static void setOnSoulFire(Entity entity, boolean onSoulFire) {
		if (entity instanceof LivingEntity)
		{
			entity.getDataTracker().set(ON_SOUL_FIRE, onSoulFire);
		}
	}
	
	public static boolean getOnSoulFire(Entity entity) {
		return entity instanceof LivingEntity ? entity.getDataTracker().get(ON_SOUL_FIRE) : false;
	}
}