package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.MagicBookClickedEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class MagicBookClicked implements Listener {

    @EventHandler
    public void MagicBookClicked(MagicBookClickedEvent event) {
        Player player = event.getPlayer();
        ItemStack magicBook = event.getMagicBook();
        String magicName = event.getMagicName();
        String magicDisplayName = event.getMagicDisplayName();

        PlayerDataUtil.addMagicList(player, magicName);
        player.getInventory().removeItem(magicBook);
        player.sendMessage(ChatColor.DARK_PURPLE + "新たな魔法: " + ChatColor.RED + magicDisplayName + ChatColor.DARK_PURPLE + "が開放された！");
    }
}
