package moriyashiine.onsoulfire.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import moriyashiine.onsoulfire.misc.OnSoulFireAccessor;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(InGameOverlayRenderer.class)
public class RenderPlayerSoulFire {
	@SuppressWarnings("deprecation")
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier("block/soul_fire_1"));
	
	@Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
	private static void renderFireOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo callbackInfo) {
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthFunc(519);
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableTexture();
		Sprite sprite = ((OnSoulFireAccessor) Objects.requireNonNull(minecraftClient.player)).getOnSoulFire() ? SOUL_FIRE_1.getSprite() : ModelLoader.FIRE_1.getSprite();
		minecraftClient.getTextureManager().bindTexture(sprite.getAtlas().getId());
		float f = sprite.getMinU();
		float g = sprite.getMaxU();
		float h = (f + g) / 2;
		float i = sprite.getMinV();
		float j = sprite.getMaxV();
		float k = (i + j) / 2;
		float l = sprite.getAnimationFrameDelta();
		float m = MathHelper.lerp(l, f, h);
		float n = MathHelper.lerp(l, g, h);
		float o = MathHelper.lerp(l, i, k);
		float p = MathHelper.lerp(l, j, k);
		for (int r = 0; r < 2; ++r) {
			matrixStack.push();
			matrixStack.translate((float) (-(r * 2 - 1)) * 0.24f, -0.3, 0);
			matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) (r * 2 - 1) * 10));
			Matrix4f matrix4f = matrixStack.peek().getModel();
			bufferBuilder.begin(7, VertexFormats.POSITION_COLOR_TEXTURE);
			bufferBuilder.vertex(matrix4f, -0.5f, -0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(n, p).next();
			bufferBuilder.vertex(matrix4f, 0.5f, -0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(m, p).next();
			bufferBuilder.vertex(matrix4f, 0.5f, 0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(m, o).next();
			bufferBuilder.vertex(matrix4f, -0.5f, 0.5f, -0.5f).color(1, 1, 1, 0.9f).texture(n, o).next();
			bufferBuilder.end();
			BufferRenderer.draw(bufferBuilder);
			matrixStack.pop();
		}
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		RenderSystem.depthFunc(515);
		callbackInfo.cancel();
	}
}