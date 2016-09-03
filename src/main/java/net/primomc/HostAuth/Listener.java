package net.primomc.HostAuth;

import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class Listener implements net.md_5.bungee.api.plugin.Listener
{
    private PrimoHostAuth plugin;

    public Listener( PrimoHostAuth plugin )
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin( final LoginEvent event )
    {
        if ( event.isCancelled() )
        {
            return;
        }
        Configuration section = plugin.getConfiguration().getSection( "host-keys" );
        for ( String sUuid : section.getKeys() )
        {
            UUID uuid = UUID.fromString( sUuid );

            if ( event.getConnection().getUniqueId().equals( uuid ) )
            {
                String hostkey = section.getString( sUuid );
                if ( !event.getConnection().getVirtualHost().getHostName().equalsIgnoreCase( hostkey ) )
                {
                    event.setCancelled( true );
                    event.setCancelReason( plugin.getKickMessage() );
                }
            }
        }
    }
}