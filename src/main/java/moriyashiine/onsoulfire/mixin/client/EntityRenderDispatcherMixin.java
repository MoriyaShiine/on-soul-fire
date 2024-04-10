/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import moriyashiine.onsoulfire.common.init.ModEntityComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
	@Unique
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_0"));
	@Unique
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_1"));

	@WrapOperation(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 0))
	private Sprite onsoulfire$renderSoulFire0(SpriteIdentifier instance, Operation<Sprite> original, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (ModEntityComponents.ON_SOUL_FIRE.get(entity).isOnSoulFire()) {
			return SOUL_FIRE_0.getSprite();
		}
		return original.call(instance);
	}

	@WrapOperation(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 1))
	private Sprite onsoulfire$renderSoulFire1(SpriteIdentifier instance, Operation<Sprite> original, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (ModEntityComponents.ON_SOUL_FIRE.get(entity).isOnSoulFire()) {
			return SOUL_FIRE_1.getSprite();
		}
		return original.call(instance);
	}
}
