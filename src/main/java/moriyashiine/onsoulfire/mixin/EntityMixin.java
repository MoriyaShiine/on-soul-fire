/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
	private void onsoulfire$setOnSoulFire(CallbackInfo ci) {
		OnSoulFireComponent onSoulFireComponent = ModEntityComponents.ON_SOUL_FIRE.get(this);
		if (onSoulFireComponent.isOnSoulFire()) {
			onSoulFireComponent.setOnSoulFire(false);
		}
	}
}
