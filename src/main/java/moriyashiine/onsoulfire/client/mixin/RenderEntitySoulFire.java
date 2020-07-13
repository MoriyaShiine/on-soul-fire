package moriyashiine.onsoulfire.client.mixin;

import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class RenderEntitySoulFire {
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_0"));
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_1"));
	
	@Shadow
	private Camera camera;
	
	@Inject(method = "renderFire", at = @At("HEAD"), cancellable = true)
	private void renderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, CallbackInfo callbackInfo) {
		Sprite sprite, sprite2;
		if (((OnSoulFireAccessor) entity).getOnSoulFire()) {
			sprite = SOUL_FIRE_0.getSprite();
			sprite2 = SOUL_FIRE_1.getSprite();
		}
		else {
			sprite = ModelLoader.FIRE_0.getSprite();
			sprite2 = ModelLoader.FIRE_1.getSprite();
		}
		matrices.push();
		float f = entity.getWidth() * 1.4f;
		matrices.scale(f, f, f);
		float g = 0.5f;
		float i = entity.getHeight() / f;
		float j = 0;
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-camera.getYaw()));
		matrices.translate(0, 0, -0.3f + (float) ((int) i) * 0.02f);
		float k = 0;
		int l = 0;
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(TexturedRenderLayers.getEntityCutout());
		for (MatrixStack.Entry entry = matrices.peek(); i > 0.0F; ++l) {
			Sprite sprite3 = l % 2 == 0 ? sprite : sprite2;
			float m = sprite3.getMinU();
			float n = sprite3.getMinV();
			float o = sprite3.getMaxU();
			float p = sprite3.getMaxV();
			if (l / 2 % 2 == 0) {
				float q = o;
				o = m;
				m = q;
			}
			drawFireVertex(entry, vertexConsumer, g, 0 - j, k, o, p);
			drawFireVertex(entry, vertexConsumer, -g, 0 - j, k, m, p);
			drawFireVertex(entry, vertexConsumer, -g, 1.4f - j, k, m, n);
			drawFireVertex(entry, vertexConsumer, g, 1.4f - j, k, o, n);
			i -= 0.45f;
			j -= 0.45f;
			g *= 0.9f;
			k += 0.03f;
		}
		matrices.pop();
		callbackInfo.cancel();
	}
	
	private static void drawFireVertex(MatrixStack.Entry entry, VertexConsumer vertices, float x, float y, float z, float u, float v) {
		vertices.vertex(entry.getModel(), x, y, z).color(255, 255, 255, 255).texture(u, v).overlay(0, 10).light(240).normal(entry.getNormal(), 0, 1, 0).next();
	}
}