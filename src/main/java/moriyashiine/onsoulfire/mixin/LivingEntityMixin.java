/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.ModConfig;
import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
	private float onsoulfire$onSoulFireDamageMultiplier(float amount, DamageSource source) {
		if (source == DamageSource.ON_FIRE && ModEntityComponents.ON_SOUL_FIRE.get(this).isOnSoulFire()) {
			amount *= ModConfig.onSoulFireDamageMultiplier;
		}
		return amount;
	}
}
