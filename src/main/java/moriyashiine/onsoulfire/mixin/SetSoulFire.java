package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.misc.OSFDataTrackers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFireBlock.class)
public class SetSoulFire
{
	@SuppressWarnings("ConstantConditions")
	@Inject(method = "onEntityCollision", at = @At("HEAD"))
	private void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo callbackInfo)
	{
		if (entity instanceof LivingEntity)
		{
			OSFDataTrackers.setOnSoulFire(entity, state.getBlock() == Blocks.SOUL_FIRE);
		}
	}
}