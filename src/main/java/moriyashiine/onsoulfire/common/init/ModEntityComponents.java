/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.common.init;

import moriyashiine.onsoulfire.common.OnSoulFire;
import moriyashiine.onsoulfire.common.component.entity.OnSoulFireComponent;
import net.minecraft.entity.Entity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<OnSoulFireComponent> ON_SOUL_FIRE = ComponentRegistry.getOrCreate(OnSoulFire.id("on_soul_fire"), OnSoulFireComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(Entity.class, ON_SOUL_FIRE, OnSoulFireComponent::new);
	}
}
