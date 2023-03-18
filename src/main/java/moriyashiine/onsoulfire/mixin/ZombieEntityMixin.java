/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
	@Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void onsoulfire$setTargetOnSoulFire(Entity target, CallbackInfoReturnable<Boolean> callbackInfo) {
		OnSoulFireComponent onSoulFireComponent = ModEntityComponents.ON_SOUL_FIRE.get(target);
		boolean onSoulFire = ModEntityComponents.ON_SOUL_FIRE.get(this).isOnSoulFire();
		if (onSoulFireComponent.isOnSoulFire() != onSoulFire) {
			onSoulFireComponent.setOnSoulFire(onSoulFire);
			onSoulFireComponent.sync();
		}
	}
}
