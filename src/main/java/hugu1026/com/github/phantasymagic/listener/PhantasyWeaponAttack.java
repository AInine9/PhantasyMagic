package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.PlayerManaChangeEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

public class PhantasyWeaponAttack implements Listener {

    @EventHandler
    public void PhantasyWeaponAttack(PhantasyWeaponAttackEvent event) {

        Player player = event.getAttacker();

        int magicPower = PlayerDataUtil.getPlayerMAGIC(player);
        int mana = PlayerDataUtil.getPlayerMANA(player);
        int maxMana = PlayerDataUtil.getPlayerMAX_MANA(player);
        int healMana = (magicPower + maxMana) / 30;

        if (mana != maxMana) {
            File playerFile = PlayerDataUtil.getPlayerFile(player);
            FileConfiguration playerData = PlayerDataUtil.getPlayerData(player);

            if (mana + healMana < maxMana) {
                playerData.set("status.mana", mana + healMana);
                PlayerDataUtil.savePlayerData(playerFile, playerData, player);

                PlayerManaChangeEvent manaChangeEvent = new PlayerManaChangeEvent(player, mana, healMana);
                Bukkit.getServer().getPluginManager().callEvent(manaChangeEvent);
            } else {
                playerData.set("status.mana", maxMana);
                PlayerDataUtil.savePlayerData(playerFile, playerData, player);

                PlayerManaChangeEvent manaChangeEvent = new PlayerManaChangeEvent(player, mana, maxMana - mana);
                Bukkit.getServer().getPluginManager().callEvent(manaChangeEvent);
            }
        }
    }
}
