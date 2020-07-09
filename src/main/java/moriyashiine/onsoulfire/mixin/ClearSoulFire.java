package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.misc.OSFDataTrackers;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ClearSoulFire
{
	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo callbackInfo)
	{
		Object obj = this;
		//noinspection ConstantConditions
		if (obj instanceof LivingEntity)
		{
			LivingEntity thisObj = (LivingEntity) obj;
			if (thisObj.getFireTicks() <= 0 && OSFDataTrackers.getOnSoulFire(thisObj))
			{
				OSFDataTrackers.setOnSoulFire(thisObj, false);
			}
		}
	}
}