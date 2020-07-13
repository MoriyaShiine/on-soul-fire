package moriyashiine.onsoulfire.mixin;

import com.mojang.authlib.GameProfile;
import moriyashiine.onsoulfire.misc.OnSoulFireAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.ServerTask;
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
public abstract class OnSoulFireHandler implements OnSoulFireAccessor
{
	private boolean onSoulFire = false;
	
	@Shadow
	private World world;
	
	@Shadow
	abstract int getFireTicks();
	
	@Override
	public boolean getOnSoulFire() {
		return onSoulFire;
	}

	@Override
	public void setOnSoulFire(boolean onSoulFire) {
		this.onSoulFire = onSoulFire;
	}
	
	@Inject(method = "fromTag", at = @At("HEAD"))
	private void fromTag(CompoundTag tag, CallbackInfo callbackInfo)
	{
		setOnSoulFire(tag.getBoolean("OnSoulFire"));
	}
	
	@Inject(method = "toTag", at = @At("HEAD"))
	private void toTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> callbackInfo)
	{
		tag.putBoolean("OnSoulFire", getOnSoulFire());
		if (!world.isClient)
		{
			Object obj = this;
			Entity thisObj = (Entity) obj;
			//noinspection Convert2Lambda
			world.getServer().send(new ServerTask(1, new Runnable() {
				@Override
				public void run() {
					sync(thisObj);
				}
			}));
		}
	}
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo callbackInfo) {
		if (!world.isClient && getFireTicks() <= 0 && getOnSoulFire()) {
			setOnSoulFire(false);
			Object obj = this;
			Entity thisObj = (Entity) obj;
			sync(thisObj);
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