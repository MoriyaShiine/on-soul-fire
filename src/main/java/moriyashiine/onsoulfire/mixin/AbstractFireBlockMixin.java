/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.ModConfig;
import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoulFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
	@Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setFireTicks(I)V"))
	private void onsoulfire$setOnSoulFire(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		OnSoulFireComponent onSoulFireComponent = ModEntityComponents.ON_SOUL_FIRE.get(entity);
		boolean onSoulFire = state.getBlock() instanceof SoulFireBlock;
		if (onSoulFireComponent.isOnSoulFire() != onSoulFire) {
			onSoulFireComponent.setOnSoulFire(onSoulFire);
		}
	}

	@SuppressWarnings("ConstantConditions")
	@ModifyArg(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
	private float onsoulfire$soulFireBlockDamageMultiplier(float value) {
		if ((AbstractFireBlock) (Object) this instanceof SoulFireBlock) {
			return value * ModConfig.soulFireBlockDamageMultiplier;
		}
		return value;
	}
}
