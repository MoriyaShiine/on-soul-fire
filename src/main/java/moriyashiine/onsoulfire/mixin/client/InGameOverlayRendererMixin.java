/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.onsoulfire.mixin.client;

import moriyashiine.onsoulfire.common.registry.ModEntityComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
	@Unique
	private static Sprite SOUL_FIRE_1;

	@SuppressWarnings("ConstantConditions")
	@ModifyVariable(method = "renderFireOverlay", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;"))
	private static Sprite onsoulfire$renderSoulFire(Sprite value, MinecraftClient client) {
		if (ModEntityComponents.ON_SOUL_FIRE.get(client.player).isOnSoulFire()) {
			if (SOUL_FIRE_1 == null) {
				SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_1")).getSprite();
			}
			return SOUL_FIRE_1;
		}
		return value;
	}
}
