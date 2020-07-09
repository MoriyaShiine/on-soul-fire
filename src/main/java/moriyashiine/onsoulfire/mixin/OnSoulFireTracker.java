package moriyashiine.onsoulfire.mixin;

import com.mojang.authlib.GameProfile;
import moriyashiine.onsoulfire.misc.OSFDataTrackers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class OnSoulFireTracker extends Entity {
	public OnSoulFireTracker(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
	private void readCustomDataFromTag(CompoundTag tag, CallbackInfo callbackInfo) {
		OSFDataTrackers.setOnSoulFire(this, tag.getBoolean("OnSoulFire"));
	}
	
	@Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
	private void writeCustomDataToTag(CompoundTag tag, CallbackInfo callbackInfo) {
		tag.putBoolean("OnSoulFire", OSFDataTrackers.getOnSoulFire(this));
	}
	
	@Inject(method = "initDataTracker", at = @At("TAIL"))
	private void initDataTracker(CallbackInfo callbackInfo) {
		getDataTracker().startTracking(OSFDataTrackers.ON_SOUL_FIRE, false);
	}
	
	@Mixin(ServerPlayerEntity.class)
	private static abstract class Server extends PlayerEntity {
		public Server(World world, BlockPos blockPos, GameProfile gameProfile) {
			super(world, blockPos, gameProfile);
		}
		
		@Inject(method = "copyFrom", at = @At("TAIL"))
		private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo callbackInfo) {
			OSFDataTrackers.setOnSoulFire(this, OSFDataTrackers.getOnSoulFire(oldPlayer));
		}
	}
}