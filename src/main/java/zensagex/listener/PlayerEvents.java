package zensagex.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;

import zensagex.SavePlayerSkin;
import zensagex.async.SaveSkinAsync;

import java.io.File;

public class PlayerEvents implements Listener{
    
    private SavePlayerSkin plugin;
    
    public PlayerEvents(SavePlayerSkin plugin){
        this.plugin = plugin;
    }
    
    public SavePlayerSkin getPlugin(){
        return plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        
        Player player = event.getPlayer();
        byte[] skinData = player.getSkin().getSkinData().data;
        String outputPath = plugin.getDataFolder() + File.separator + player.getName() + "_skin.png";
        
        SaveSkinAsync task = new SaveSkinAsync(skinData, outputPath);
        plugin.getServer().getScheduler().scheduleAsyncTask(task);
    }
}
