package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.interfaces.OnSoulFireAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements OnSoulFireAccessor {
	private static final TrackedData<Boolean> ON_SOUL_FIRE = DataTracker.registerData(Entity.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	@Shadow
	public World world;
	
	@Final
	@Shadow
	protected DataTracker dataTracker;
	
	@Shadow
	public abstract int getFireTicks();
	
	@Override
	public boolean getOnSoulFire() {
		return dataTracker.get(ON_SOUL_FIRE);
	}
	
	@Override
	public void setOnSoulFire(boolean onSoulFire) {
		dataTracker.set(ON_SOUL_FIRE, onSoulFire);
	}
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo callbackInfo) {
		if (!world.isClient && getFireTicks() <= 0 && getOnSoulFire()) {
			setOnSoulFire(false);
		}
	}
	
	@Inject(method = "fromTag", at = @At("TAIL"))
	private void fromTag(CompoundTag tag, CallbackInfo callbackInfo) {
		setOnSoulFire(tag.getBoolean("OnSoulFire"));
	}
	
	@Inject(method = "toTag", at = @At("TAIL"))
	private void toTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> callbackInfo) {
		tag.putBoolean("OnSoulFire", getOnSoulFire());
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(CallbackInfo callbackInfo) {
		dataTracker.startTracking(ON_SOUL_FIRE, false);
	}
}
