package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class Heal extends Magic {
    public Heal(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 7, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Player player = event.getPlayer();

        PlayerDataUtil.addPlayerHP(player, (int) magicDamageCalc(10, magicPower));

        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, magicLocation.add(0, 1, 0), 50, 2, 2, 2, 10);
        player.getWorld().playSound(magicLocation, Sound.ENTITY_PLAYER_LEVELUP, 1, (float) 1.7);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("ヒール")
                && PlayerDataUtil.getMagicList(player).contains("heal");
    }
}
