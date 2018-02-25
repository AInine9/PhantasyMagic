package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.Collection;

public class Fire extends Magic {

    public Fire(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 5, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.setFireTicks((int) magicDamageCalc(20, magicPower));
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.LAVA, magicLocation.add(0, 1, 0), 20);
        event.getPlayer().getWorld().playSound(magicLocation, Sound.ENTITY_GHAST_SHOOT, 1, 0);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("ファイヤ")
                && PlayerDataUtil.getMagicList(player).contains("fire");
    }
}
