/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
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
	private void onsoulfire$setTargetOnSoulFire(Entity target, CallbackInfoReturnable<Boolean> callbackInfo) {
		OnSoulFireComponent onSoulFireComponent = target.getComponent(ModEntityComponents.ON_SOUL_FIRE);
		boolean onSoulFire = getComponent(ModEntityComponents.ON_SOUL_FIRE).isOnSoulFire();
		if (onSoulFireComponent.isOnSoulFire() != onSoulFire) {
			onSoulFireComponent.setOnSoulFire(onSoulFire);
			onSoulFireComponent.sync();
		}
	}
}
