package moriyashiine.onsoulfire.mixin;

import moriyashiine.onsoulfire.api.component.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@ModifyVariable(method = "damage", at = @At("HEAD"))
	private float damage(float amount, DamageSource source) {
		if (source == DamageSource.ON_FIRE && OnSoulFireComponent.get(this).isOnSoulFire()) {
			return amount * 2;
		}
		return amount;
	}
}
