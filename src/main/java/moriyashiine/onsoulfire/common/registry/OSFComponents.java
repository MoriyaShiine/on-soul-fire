package moriyashiine.onsoulfire.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import moriyashiine.onsoulfire.api.component.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class OSFComponents implements EntityComponentInitializer {
	public static final ComponentKey<OnSoulFireComponent> ON_SOUL_FIRE_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("onsoulfire", "on_soul_fire"), OnSoulFireComponent.class);
	
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(Entity.class, ON_SOUL_FIRE_COMPONENT).impl(OnSoulFireComponent.class).respawnStrategy(RespawnCopyStrategy.LOSSLESS_ONLY).end(OnSoulFireComponent::new);
	}
}
