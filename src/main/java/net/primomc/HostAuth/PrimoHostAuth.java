package net.primomc.HostAuth;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class PrimoHostAuth extends Plugin
{

    private Configuration configuration;

    @Override
    public void onEnable()
    {
        loadConfig();
        getProxy().getPluginManager().registerListener( this, new PlayerListener( this ) );
    }

    private void loadConfig()
    {
        if ( !getDataFolder().exists() )
        {
            getDataFolder().mkdir();
        }

        File file = new File( getDataFolder(), "config.yml" );

        if ( !file.exists() )
        {
            try ( InputStream in = getResourceAsStream( "config.yml" ) )
            {
                Files.copy( in, file.toPath() );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        try
        {
            configuration = ConfigurationProvider.getProvider( YamlConfiguration.class ).load( new File( getDataFolder(), "config.yml" ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public String getKickMessage()
    {
        String message = configuration.getString( "message" );
        return ChatColor.translateAlternateColorCodes( '&', message );
    }
}