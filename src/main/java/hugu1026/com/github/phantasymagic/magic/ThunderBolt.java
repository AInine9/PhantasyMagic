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

public class ThunderBolt extends Magic {

    public ThunderBolt(String magicName, Event event, Integer slot, Player player) {
        super(magicName, event, slot, 10, player);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation, int magicPower) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.getWorld().strikeLightningEffect(creature.getLocation());
                creature.damage(magicDamageCalc(10, magicPower));
            }
        }
    }

    @Override
    public boolean checkMagicName(String magicName, Player player) {
        return magicName.equals("サンダーボルト")
                && PlayerDataUtil.getMagicList(player).contains("thunder bolt");
    }
}
