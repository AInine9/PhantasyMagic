package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.PhantasyMagic;
import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.*;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.material.MaterialData;

import java.util.HashSet;
import java.util.Set;

public class SanctamCross extends Magic {

    public SanctamCross(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 15, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25).stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .forEach(creature -> creature.damage(magicDamageCalc(20, magicPower)));

        Set<Location> crossLocation = new HashSet<>();
        crossLocation.add(magicLocation.clone());
        crossLocation.add(magicLocation.clone().add(0, 1, 0));
        crossLocation.add(magicLocation.clone().add(0, 2, 0));
        crossLocation.add(magicLocation.clone().add(0, 3, 0));
        crossLocation.add(magicLocation.clone().add(0, 4, 0));
        crossLocation.add(magicLocation.clone().add(1, 3, 0));
        crossLocation.add(magicLocation.clone().add(-1, 3, 0));

        crossLocation.forEach(blockLocation -> event.getPlayer().sendBlockChange(blockLocation, Material.GOLD_BLOCK, (byte) 0));
        event.getPlayer().spawnParticle(Particle.SPELL_WITCH, magicLocation.add(0, 1, 0), 200, 2, 2, 2, 10);
        event.getPlayer().playSound(magicLocation, Sound.ENTITY_WITHER_SPAWN, 1, (float) 0.5);

        Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyMagic.getPlugin(PhantasyMagic.class), () -> {
            crossLocation.forEach(blockLocation -> {
                blockLocation.getBlock().getState().update();
                event.getPlayer().spawnParticle(Particle.BLOCK_CRACK, blockLocation, 50, new MaterialData(Material.GOLD_BLOCK));
            });
            event.getPlayer().playSound(magicLocation, Sound.BLOCK_GLASS_BREAK, 1, (float) 0.5);
        }, 20L);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("サンクタムクロス")
                && PlayerDataUtil.getMagicList(player).contains("sanctam cross");
    }
}
