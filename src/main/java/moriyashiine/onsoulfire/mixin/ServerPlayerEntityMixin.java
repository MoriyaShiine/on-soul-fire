package moriyashiine.onsoulfire.mixin;

import com.mojang.authlib.GameProfile;
import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
		super(world, pos, yaw, profile);
	}
	
	@Inject(method = "copyFrom", at = @At("TAIL"))
	private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo callbackInfo) {
		OnSoulFireAccessor.of(this).ifPresent(onSoulFireAccessor -> OnSoulFireAccessor.of(oldPlayer).ifPresent(oldOnSoulFireAccessor -> onSoulFireAccessor.setOnSoulFire(oldOnSoulFireAccessor.getOnSoulFire())));
	}
}
