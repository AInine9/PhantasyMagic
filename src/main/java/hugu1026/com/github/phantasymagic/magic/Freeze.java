package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class Freeze extends Magic {

    public Freeze(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 5, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) magicDamageCalc(20, magicPower), 100));
                creature.damage(0);
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.BLOCK_CRACK, magicLocation.add(0, 1, 0), 100, new MaterialData(Material.ICE));
        event.getPlayer().getWorld().playSound(magicLocation, Sound.BLOCK_GLASS_BREAK, 1, 0);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("フリーズ")
                && PlayerDataUtil.getMagicList(player).contains("freeze");
    }
}
