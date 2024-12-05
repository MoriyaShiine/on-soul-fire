/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.onsoulfire.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
	@Unique
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.ofVanilla("block/soul_fire_0"));
	@Unique
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.ofVanilla("block/soul_fire_1"));

	@Unique
	private static Entity cachedEntity = null;

	@Inject(method = "render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V", at = @At("HEAD"))
	private <E extends Entity, S extends EntityRenderState> void onsoulfire$cacheEntity(E entity, double x, double y, double z, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, EntityRenderer<? super E, S> renderer, CallbackInfo ci) {
		cachedEntity = entity;
	}

	@WrapOperation(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 0))
	private Sprite onsoulfire$renderSoulFire0(SpriteIdentifier instance, Operation<Sprite> original, MatrixStack matrices, VertexConsumerProvider vertexConsumers, EntityRenderState renderState) {
		if (cachedEntity != null && ModEntityComponents.ON_SOUL_FIRE.get(cachedEntity).isOnSoulFire()) {
			return SOUL_FIRE_0.getSprite();
		}
		return original.call(instance);
	}

	@WrapOperation(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 1))
	private Sprite onsoulfire$renderSoulFire1(SpriteIdentifier instance, Operation<Sprite> original, MatrixStack matrices, VertexConsumerProvider vertexConsumers, EntityRenderState renderState) {
		if (cachedEntity != null && ModEntityComponents.ON_SOUL_FIRE.get(cachedEntity).isOnSoulFire()) {
			return SOUL_FIRE_1.getSprite();
		}
		return original.call(instance);
	}
}
