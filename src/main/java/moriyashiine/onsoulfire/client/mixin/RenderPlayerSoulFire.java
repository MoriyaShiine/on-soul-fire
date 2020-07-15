package moriyashiine.onsoulfire.client.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Objects;

@Mixin(InGameOverlayRenderer.class)
public class RenderPlayerSoulFire {
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_1"));
	
	@Overwrite
	private static void renderFireOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack) {
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthFunc(519);
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableTexture();
		Sprite sprite = ((OnSoulFireAccessor) Objects.requireNonNull(minecraftClient.player)).getOnSoulFire() ? SOUL_FIRE_1.getSprite() : ModelLoader.FIRE_1.getSprite();
		minecraftClient.getTextureManager().bindTexture(sprite.getAtlas().getId());
		float delta = sprite.getAnimationFrameDelta();
		float minU = sprite.getMinU();
		float maxU = sprite.getMaxU();
		float minV = sprite.getMinV();
		float maxV = sprite.getMaxV();
		float medianV = (minV + maxV) / 2;
		float medianU = (minU + maxU) / 2;
		float x = MathHelper.lerp(delta, maxU, medianU);
		float y = MathHelper.lerp(delta, minU, medianU);
		float z = MathHelper.lerp(delta, minV, medianV);
		float w = MathHelper.lerp(delta, maxV, medianV);
		for (int i = 0; i < 2; ++i) {
			matrixStack.push();
			matrixStack.translate((float) (-(i * 2 - 1)) * 0.24f, -0.3, 0);
			matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) (i * 2 - 1) * 10));
			Matrix4f matrix = matrixStack.peek().getModel();
			bufferBuilder.begin(7, VertexFormats.POSITION_COLOR_TEXTURE);
			bufferBuilder.vertex(matrix, -0.5f, -0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(x, w).next();
			bufferBuilder.vertex(matrix, 0.5f, -0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(y, w).next();
			bufferBuilder.vertex(matrix, 0.5f, 0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(y, z).next();
			bufferBuilder.vertex(matrix, -0.5f, 0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(x, z).next();
			bufferBuilder.end();
			BufferRenderer.draw(bufferBuilder);
			matrixStack.pop();
		}
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		RenderSystem.depthFunc(515);
	}
}