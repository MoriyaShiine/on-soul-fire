package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.interfaces.OnSoulFireAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin {
	@Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void tryAttack(Entity target, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (((OnSoulFireAccessor) this).getOnSoulFire()) {
			((OnSoulFireAccessor) target).setOnSoulFire(true);
		}
	}
}
