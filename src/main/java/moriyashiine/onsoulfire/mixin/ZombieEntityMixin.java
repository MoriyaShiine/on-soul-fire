package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.api.component.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
	protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void tryAttack(Entity target, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (OnSoulFireComponent.get(this).isOnSoulFire()) {
			OnSoulFireComponent.get(target).setOnSoulFire(true);
		}
	}
}
