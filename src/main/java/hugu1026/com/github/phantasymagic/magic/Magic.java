package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.event.Event;

public abstract class Magic {

    public Magic(String magicName, Event event) {
        if (checkMagicName(magicName) && this.checkActivateEventName(event)) ActivatedMagic((ActivateMagicEvent) event);
    }

    public abstract void ActivatedMagic(ActivateMagicEvent event);

    public abstract boolean checkMagicName(String magicName);

    public boolean checkActivateEventName(Event event) {
        if (event instanceof ActivateMagicEvent) {
            return true;
        } else {
            return false;
        }
    }
}
