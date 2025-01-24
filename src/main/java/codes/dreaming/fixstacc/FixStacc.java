package codes.dreaming.fixstacc;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(id = "fixstacc", name = "fixstacc", version = BuildConstants.VERSION, url = "https://dreaming.codes", authors = {"DreamingCodes"})
public class FixStacc {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
