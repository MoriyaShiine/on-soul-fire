/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin.client;

import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
	@Unique
	private static Sprite SOUL_FIRE_0;
	@Unique
	private static Sprite SOUL_FIRE_1;

	@ModifyVariable(method = "renderFire", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 0), ordinal = 0)
	private Sprite onsoulfire$renderSoulFire0(Sprite value, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (entity.getComponent(ModEntityComponents.ON_SOUL_FIRE).isOnSoulFire()) {
			if (SOUL_FIRE_0 == null) {
				SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_0")).getSprite();
			}
			return SOUL_FIRE_0;
		}
		return value;
	}

	@ModifyVariable(method = "renderFire", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 1), ordinal = 1)
	private Sprite onsoulfire$renderSoulFire1(Sprite value, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (entity.getComponent(ModEntityComponents.ON_SOUL_FIRE).isOnSoulFire()) {
			if (SOUL_FIRE_1 == null) {
				SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_1")).getSprite();
			}
			return SOUL_FIRE_1;
		}
		return value;
	}
}
