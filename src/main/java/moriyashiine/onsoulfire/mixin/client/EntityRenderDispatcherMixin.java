package moriyashiine.onsoulfire.mixin.client;

import moriyashiine.onsoulfire.api.component.OnSoulFireComponent;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_0"));
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_1"));
	
	@ModifyVariable(method = "renderFire", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 0), ordinal = 0)
	private Sprite getSprite0(Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (OnSoulFireComponent.get(entity).isOnSoulFire()) {
			return SOUL_FIRE_0.getSprite();
		}
		return sprite;
	}
	
	@ModifyVariable(method = "renderFire", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;", ordinal = 1), ordinal = 1)
	private Sprite getSprite1(Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
		if (OnSoulFireComponent.get(entity).isOnSoulFire()) {
			return SOUL_FIRE_1.getSprite();
		}
		return sprite;
	}
}
