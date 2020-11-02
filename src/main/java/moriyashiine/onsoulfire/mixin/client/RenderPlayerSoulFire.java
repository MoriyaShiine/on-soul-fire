package moriyashiine.onsoulfire.mixin.client;

import moriyashiine.onsoulfire.api.accessor.OnSoulFireAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(InGameOverlayRenderer.class)
public class RenderPlayerSoulFire {
	private static final SpriteIdentifier SOUL_FIRE_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("block/soul_fire_1"));
	
	@Redirect(method = "renderFireOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getSprite()Lnet/minecraft/client/texture/Sprite;"))
	private static Sprite getSprite(SpriteIdentifier obj, MinecraftClient minecraftClient) {
		if (OnSoulFireAccessor.of(minecraftClient.player).map(OnSoulFireAccessor::getOnSoulFire).orElse(false)) {
			return SOUL_FIRE_1.getSprite();
		}
		return obj.getSprite();
	}
}
