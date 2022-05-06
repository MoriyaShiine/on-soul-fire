package moriyashiine.onsoulfire.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import moriyashiine.onsoulfire.common.OnSoulFire;
import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ModEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<OnSoulFireComponent> ON_SOUL_FIRE_COMPONENT = ComponentRegistry.getOrCreate(new Identifier(OnSoulFire.MOD_ID, "on_soul_fire"), OnSoulFireComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(Entity.class, ON_SOUL_FIRE_COMPONENT, OnSoulFireComponent::new);
	}
}
