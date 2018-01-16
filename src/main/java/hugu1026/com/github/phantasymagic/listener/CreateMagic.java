package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.CreateMagicEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CreateMagic implements Listener {

    @EventHandler
    public void CreateMagic(CreateMagicEvent event) {
        HashMap<Integer, ItemStack> magicIcons = event.getMagicIcons();
        Bukkit.getServer().broadcastMessage(String.valueOf(magicIcons));
    }
}