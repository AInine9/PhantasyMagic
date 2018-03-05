package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasymagic.magic.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ActivateMagic implements Listener {
    private static Set<Class<? extends Magic>> magicClass;

    static {
        magicClass = new HashSet<>();

        magicClass.add(Fire.class);
        magicClass.add(Freeze.class);
        magicClass.add(Explosion.class);
        magicClass.add(AcidRain.class);
        magicClass.add(IcElcDrop.class);
        magicClass.add(ThunderBolt.class);
        magicClass.add(Heal.class);
    }

    @EventHandler
    public void ActivateMagic(ActivateMagicEvent event) {
        HashMap<Integer, String> activateMagicList = event.getActivateMagicList();

        for (int slot : activateMagicList.keySet()) {
            String activateMagic = activateMagicList.get(slot);

            magicClass.stream().forEach(c -> {
                try {
                    c.getConstructor(String.class, Event.class, Integer.class, Player.class).newInstance(activateMagic, event, slot, event.getPlayer());
                } catch (Exception ex) {
                    return;
                }
            });
        }
    }
}