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
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityRenderDispatcher.class)
public class RenderEntitySoulFire {
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_0"));
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_1"));
	
	@Shadow
	private Camera camera;
	
	@Shadow
	private static void drawFireVertex(MatrixStack.Entry entry, VertexConsumer vertices, float x, float y, float z, float u, float v) {
	}
	
	@Overwrite
	private void renderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
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
		float width = entity.getWidth() * 1.4f;
		float height = entity.getHeight() / width;
		float x = 0.5f;
		float y = 0;
		float z = 0;
		int i = 0;
		//noinspection SuspiciousNameCombination
		matrices.scale(width, width, width);
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-camera.getYaw()));
		matrices.translate(0, 0, -0.3f + (float) ((int) height) * 0.02f);
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(TexturedRenderLayers.getEntityCutout());
		for (MatrixStack.Entry entry = matrices.peek(); height > 0; ++i) {
			Sprite spriteToUse = i % 2 == 0 ? sprite : sprite2;
			float minU = spriteToUse.getMinU();
			float maxU = spriteToUse.getMaxU();
			float minV = spriteToUse.getMinV();
			float maxV = spriteToUse.getMaxV();
			if (i / 2 % 2 == 0) {
				float temp = maxU;
				maxU = minU;
				minU = temp;
			}
			drawFireVertex(entry, vertexConsumer, x, -y, z, maxU, maxV);
			drawFireVertex(entry, vertexConsumer, -x, -y, z, minU, maxV);
			drawFireVertex(entry, vertexConsumer, -x, 1.4f - y, z, minU, minV);
			drawFireVertex(entry, vertexConsumer, x, 1.4f - y, z, maxU, minV);
			height -= 0.45f;
			x *= 0.9f;
			y -= 0.45f;
			z += 0.03f;
		}
		matrices.pop();
	}
}