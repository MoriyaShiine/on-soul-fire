package moriyashiine.onsoulfire.client;

import moriyashiine.onsoulfire.client.network.message.SyncOnSoulFireMessage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

public class OnSoulFireClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient() {
		ClientSidePacketRegistry.INSTANCE.register(SyncOnSoulFireMessage.ID, SyncOnSoulFireMessage::handle);
	}
}