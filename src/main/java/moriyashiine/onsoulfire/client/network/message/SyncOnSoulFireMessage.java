package moriyashiine.onsoulfire.client.network.message;

import io.netty.buffer.Unpooled;
import moriyashiine.onsoulfire.misc.OnSoulFireAccessor;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SyncOnSoulFireMessage {
	public static final Identifier ID = new Identifier("onsoulfire", "sync_on_soul_fire");
	
	public static void send(PlayerEntity player, int entityId, boolean onSoulFire) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeInt(entityId);
		buf.writeBoolean(onSoulFire);
		ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, ID, buf);
	}
	
	public static void handle(PacketContext context, PacketByteBuf buf) {
		int entityId = buf.readInt();
		boolean onSoulFire = buf.readBoolean();
		//noinspection Convert2Lambda
		context.getTaskQueue().submit(new Runnable() {
			@Override
			public void run() {
				World world = MinecraftClient.getInstance().world;
				if (world != null) {
					Entity entity = world.getEntityById(entityId);
					if (entity != null) {
						((OnSoulFireAccessor) entity).setOnSoulFire(onSoulFire);
					}
				}
			}
		});
	}
}