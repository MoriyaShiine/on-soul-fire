package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.misc.OSFDataTrackers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ClearSoulFire extends Entity {
	public ClearSoulFire(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo callbackInfo) {
		if (getFireTicks() <= 0 && OSFDataTrackers.getOnSoulFire(this)) {
			OSFDataTrackers.setOnSoulFire(this, false);
		}
	}
}