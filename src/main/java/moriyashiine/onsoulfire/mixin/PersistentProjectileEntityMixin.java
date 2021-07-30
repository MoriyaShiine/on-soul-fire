package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.api.component.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin extends Entity {
	public PersistentProjectileEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo callbackInfo, Entity entity) {
		if (OnSoulFireComponent.get(this).isOnSoulFire()) {
			OnSoulFireComponent.get(entity).setOnSoulFire(true);
		}
	}
}
