/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.ModConfig;
import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
	private float onsoulfire$onSoulFireDamageMultiplier(float amount, ServerWorld world, DamageSource source) {
		if (source == world.getDamageSources().onFire() && ModEntityComponents.ON_SOUL_FIRE.get(this).isOnSoulFire()) {
			amount *= ModConfig.onSoulFireDamageMultiplier;
		}
		return amount;
	}
}
