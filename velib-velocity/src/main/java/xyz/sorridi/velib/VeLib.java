package xyz.sorridi.velib;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import java.util.logging.Logger;

@Plugin(id = "velib",
        name = "VeLib",
        version = "1.0",
        authors = {"Sorridi"})

public class VeLib
{
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public VeLib(ProxyServer server, Logger logger)
    {
        this.server = server;
        this.logger = logger;
    }
}