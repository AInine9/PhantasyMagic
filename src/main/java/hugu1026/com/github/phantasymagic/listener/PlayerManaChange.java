package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.PlayerManaChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerManaChange implements Listener {

    @EventHandler
    public void PlayerManaChange(PlayerManaChangeEvent event) {
        Player player = event.getPlayer();
        int originalMana = event.getOriginalMana();
        int manaDifference = event.getManaDifference();
        int maxMana = event.getMaxMana();

        float proportion = (float) (originalMana + manaDifference) / maxMana;
        player.setTotalExperience(0);
        player.setLevel(0);
        player.setExp(proportion);
    }
}
