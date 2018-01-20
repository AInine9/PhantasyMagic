package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class ActivateMagic implements Listener {

    @EventHandler
    public void ActivateMagic(ActivateMagicEvent event) {
        HashMap<Integer, String> activateMagicList = event.getActivateMagicList();
    }
}
