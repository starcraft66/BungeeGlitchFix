package org.tzone.tristan;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeGlitchFix extends JavaPlugin implements Listener{

	Logger logger = Logger.getLogger("Minecraft");
	Player p;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info("[" + pdf.getName() + "] version " + pdf.getVersion() + " enabled!");
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info("[" + pdf.getName() + "] version " + pdf.getVersion() + " disabled!");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerJoinEvent e)
	{
		p = e.getPlayer();
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
		@Override 
		public void run() {
		World w = p.getLocation().getWorld();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY() + 1;
		double z = p.getLocation().getZ();
		float pitch = p.getLocation().getPitch();
		float yaw = p.getLocation().getYaw();
		Location loc = new Location(w, x, y, z);
		loc.setPitch(pitch);
		loc.setYaw(yaw);
		p.teleport(loc);
		    }
		}, 200L);
		
	}
	
	
	
	
}