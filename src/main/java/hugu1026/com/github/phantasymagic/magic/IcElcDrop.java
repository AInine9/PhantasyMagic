package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.PhantasyMagic;
import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Collection;

public class IcElcDrop extends Magic{

    public IcElcDrop(String magicName, Event event, Integer slot) {
        super(magicName, event, slot, 10);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.damage(magicDamageCalc(10, magicPower));
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.CRIT_MAGIC, magicLocation.add(0, 1, 0), 100);
        FallingBlock fallingBlock = magicLocation.getWorld().spawnFallingBlock(magicLocation.add(0, 1, 0), Material.PACKED_ICE, (byte)0);
        fallingBlock.setDropItem(false);
        fallingBlock.setMetadata("notPlace", new FixedMetadataValue(PhantasyMagic.getPlugin(PhantasyMagic.class), Boolean.TRUE));
        event.getPlayer().getWorld().playSound(magicLocation, Sound.ENTITY_LIGHTNING_THUNDER, 1, 2);
    }

    @Override
    public boolean checkMagicName(String magicName) {
        return magicName.equals("アイゼルクドロップ");
    }
}
