package zensagex;

import cn.nukkit.plugin.PluginBase;

import zensagex.listener.PlayerEvents;

// © ZenSageX444
// © SimpleZx

public class SavePlayerSkin extends PluginBase{
    
    public static SavePlayerSkin INSTANCE;
    
    public String getPrefix(){
        return "§eSavePlayerSkin§f:";
    }

    @Override
    public void onLoad(){
        INSTANCE = this;
        getDataFolder().mkdirs();
        getLogger().info(getPrefix()+" §eLoading plugin...");
    }

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        getLogger().info(getPrefix()+" §aI've been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info(getPrefix()+" §cI've been disabled!");
    }
    
}
