package moriyashiine.onsoulfire.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ModComponents implements EntityComponentInitializer {
	public static final ComponentKey<OnSoulFireComponent> ON_SOUL_FIRE_COMPONENT = ComponentRegistry.getOrCreate(new Identifier("onsoulfire", "on_soul_fire"), OnSoulFireComponent.class);
	
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(Entity.class, ON_SOUL_FIRE_COMPONENT).respawnStrategy(RespawnCopyStrategy.LOSSLESS_ONLY).end(OnSoulFireComponent::new);
	}
}
