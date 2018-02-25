package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.Collection;

public class Explosion extends Magic {

    public Explosion(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 10, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                double x, y, z;
                x = magicLocation.getX();
                y = magicLocation.getY() + 1;
                z = magicLocation.getZ();

                event.getPlayer().getWorld().createExplosion(x, y, z, 2, false, false);
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.LAVA, magicLocation.add(0, 1, 0), 50);
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("エクスプロージョン")
                && PlayerDataUtil.getMagicList(player).contains("explosion");
    }
}
