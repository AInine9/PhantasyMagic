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

public class AcidRain extends Magic {

    public AcidRain(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 7, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.damage(magicDamageCalc(5, magicPower));
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.WATER_SPLASH, magicLocation.add(0, 2.5, 0), 500);
        event.getPlayer().getWorld().spawnParticle(Particle.CRIT, magicLocation.add(0, 1, 0), 100);
        event.getPlayer().getWorld().playSound(magicLocation, Sound.WEATHER_RAIN, 1, 2);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("アシッドレイン")
                && PlayerDataUtil.getMagicList(player).contains("acid rain");
    }
}
