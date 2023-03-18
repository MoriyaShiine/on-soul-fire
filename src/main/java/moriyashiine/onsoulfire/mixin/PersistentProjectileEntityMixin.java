/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {
	@Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void onsoulfire$setTargetOnSoulFire(EntityHitResult entityHitResult, CallbackInfo ci, Entity entity) {
		OnSoulFireComponent onSoulFireComponent = ModEntityComponents.ON_SOUL_FIRE.get(entity);
		boolean onSoulFire = ModEntityComponents.ON_SOUL_FIRE.get(this).isOnSoulFire();
		if (onSoulFireComponent.isOnSoulFire() != onSoulFire) {
			onSoulFireComponent.setOnSoulFire(onSoulFire);
			onSoulFireComponent.sync();
		}
	}
}
