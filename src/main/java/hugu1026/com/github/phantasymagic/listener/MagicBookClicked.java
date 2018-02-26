package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.MagicBookClickedEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
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

        if (PlayerDataUtil.getMagicList(player).contains(magicName)) {
            player.sendMessage(ChatColor.RED + "その魔法は既に開放されている！");
            return;
        }
        PlayerDataUtil.addMagicList(player, magicName);
        player.getInventory().removeItem(magicBook);
        player.sendMessage(ChatColor.DARK_PURPLE + "新たな魔法: " + ChatColor.RED + magicDisplayName + ChatColor.DARK_PURPLE + "が開放された！");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, (float) 1.5);
        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().add(0, 0.5, 0), 200);
        player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation().add(0, 0.5, 0), 100);
    }
}
