package moriyashiine.onsoulfire.common.mixin;

import com.mojang.authlib.GameProfile;
import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(Entity.class)
public abstract class OnSoulFireHandler implements OnSoulFireAccessor {
	private static final TrackedData<Boolean> ON_SOUL_FIRE = DataTracker.registerData(Entity.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	@Shadow
	private World world;
	
	@Shadow
	abstract int getFireTicks();
	
	@Override
	public boolean getOnSoulFire() {
		Object obj = this;
		return ((Entity) obj).getDataTracker().get(ON_SOUL_FIRE);
	}
	
	@Override
	public void setOnSoulFire(boolean onSoulFire) {
		Object obj = this;
		((Entity) obj).getDataTracker().set(ON_SOUL_FIRE, onSoulFire);
	}
	
	@Inject(method = "fromTag", at = @At("HEAD"))
	private void fromTag(CompoundTag tag, CallbackInfo callbackInfo) {
		setOnSoulFire(tag.getBoolean("OnSoulFire"));
	}
	
	@Inject(method = "toTag", at = @At("HEAD"))
	private void toTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> callbackInfo) {
		tag.putBoolean("OnSoulFire", getOnSoulFire());
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	private void initDataTracker(CallbackInfo callbackInfo) {
		Object obj = this;
		((Entity) obj).getDataTracker().startTracking(ON_SOUL_FIRE, false);
	}
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo callbackInfo) {
		if (!world.isClient && getFireTicks() <= 0 && getOnSoulFire()) {
			setOnSoulFire(false);
		}
	}
	
	@Mixin(ServerPlayerEntity.class)
	private abstract static class Server extends PlayerEntity implements OnSoulFireAccessor {
		public Server(World world, BlockPos blockPos, GameProfile gameProfile) {
			super(world, blockPos, gameProfile);
		}
		
		@Inject(method = "copyFrom", at = @At("TAIL"))
		private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo callbackInfo) {
			setOnSoulFire(((OnSoulFireAccessor) oldPlayer).getOnSoulFire());
		}
	}
}