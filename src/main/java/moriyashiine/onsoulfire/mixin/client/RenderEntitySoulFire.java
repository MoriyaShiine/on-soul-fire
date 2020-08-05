package moriyashiine.onsoulfire.mixin.client;

import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.concurrent.atomic.AtomicReference;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class RenderEntitySoulFire {
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_0"));
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_1"));
	
	@Redirect(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 0))
	private Sprite getSprite0(SpriteIdentifier obj, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		AtomicReference<Sprite> sprite = new AtomicReference<>(obj.getSprite());
		OnSoulFireAccessor.get(entity).ifPresent(onSoulFireAccessor -> {
			if (onSoulFireAccessor.getOnSoulFire()) {
				sprite.set(SOUL_FIRE_0.getSprite());
			}
		});
		return sprite.get();
	}
	
	@Redirect(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 1))
	private Sprite getSprite1(SpriteIdentifier obj, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		AtomicReference<Sprite> sprite = new AtomicReference<>(obj.getSprite());
		OnSoulFireAccessor.get(entity).ifPresent(onSoulFireAccessor -> {
			if (onSoulFireAccessor.getOnSoulFire()) {
				sprite.set(SOUL_FIRE_1.getSprite());
			}
		});
		return sprite.get();
	}
}
