package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.OnSoulFire;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {
	@SuppressWarnings("ConstantConditions")
	@ModifyArg(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
	private float onsoulfire$soulFireBlockDamageMultiplier(float value) {
		if (CampfireBlock.class.cast(this) == Blocks.SOUL_CAMPFIRE) {
			return value * OnSoulFire.getConfig().soulFireBlockDamageMultiplier;
		}
		return value;
	}
}
