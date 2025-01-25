package codes.dreaming.fixstacc;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.logging.Logger;

public class FixStaccAnnounceHandler {
    private Logger logger;
    private ConfigHelper configHelper;

    FixStaccAnnounceHandler (Logger logger, ConfigHelper configHelper) {
        this.logger = logger;
        this.configHelper = configHelper;
    }

    @Subscribe()
    public void onPreLogin(ServerPreConnectEvent event) throws SerializationException {
        boolean isStacced = configHelper.getStaccServers().contains(event.getOriginalServer().getServerInfo().getName());

        event.getPlayer().sendPluginMessage(FixStacc.ANNOUNCE_STACCED_ID, isStacced ? new byte[] {1} : new byte[] {0});
    }
}
