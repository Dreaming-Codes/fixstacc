package codes.dreaming.fixstacc;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import java.util.logging.Logger;

@Plugin(id = FixStacc.COMPANION_MOD_ID, name = "fixstacc", version = BuildConstants.VERSION, url = "https://dreaming.codes", authors = {"DreamingCodes"})
public class FixStacc {
    public static final String COMPANION_MOD_ID = "fixstacc";

    public static final ChannelIdentifier ANNOUNCE_STACCED_ID = MinecraftChannelIdentifier.create(COMPANION_MOD_ID, "announce");

    private Logger logger;

    private ProxyServer proxyServer;
    private final ConfigHelper configHelper;

    @Inject
    public FixStacc(ProxyServer server, Logger logger) {
        this.logger = logger;
        this.proxyServer = server;

        this.configHelper = new ConfigHelper(logger);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        this.configHelper.loadConfiguration();

        proxyServer.getChannelRegistrar().register(ANNOUNCE_STACCED_ID);

        proxyServer.getEventManager().register(this, new FixStaccAnnounceHandler(logger, configHelper));

    }
}
