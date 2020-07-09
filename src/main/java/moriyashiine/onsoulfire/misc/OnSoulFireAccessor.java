package moriyashiine.onsoulfire.misc;

import moriyashiine.onsoulfire.client.network.message.SyncOnSoulFireMessage;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface OnSoulFireAccessor
{
	default void sync(Entity entity)
	{
		PlayerStream.watching(entity).forEach(foundPlayer -> SyncOnSoulFireMessage.send(foundPlayer, entity.getEntityId(), getOnSoulFire()));
		if (entity instanceof PlayerEntity)
		{
			SyncOnSoulFireMessage.send((PlayerEntity) entity, entity.getEntityId(), getOnSoulFire());
		}
	}
	
	boolean getOnSoulFire();
	
	void setOnSoulFire(boolean onSoulFire);
}