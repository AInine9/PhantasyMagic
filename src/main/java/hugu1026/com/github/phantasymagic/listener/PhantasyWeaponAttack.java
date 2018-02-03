package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

public class PhantasyWeaponAttack implements Listener {

    @EventHandler
    public void PhantasyWeaponAttack(PhantasyWeaponAttackEvent event) {

        Player player = event.getAttacker();

        int mana = PlayerDataUtil.getPlayerMANA(player);
        int maxMana = PlayerDataUtil.getPlayerMAX_MANA(player);

        if (mana != maxMana) {
            File playerFile = PlayerDataUtil.getPlayerFile(player);
            FileConfiguration playerData = PlayerDataUtil.getPlayerData(player);

            if (mana + 10 < maxMana) {
                playerData.set("status.mana", mana + 10);
                PlayerDataUtil.savePlayerData(playerFile, playerData, player);
            } else {
                playerData.set("status.mana", maxMana);
                PlayerDataUtil.savePlayerData(playerFile, playerData, player);
            }
        }
    }
}
