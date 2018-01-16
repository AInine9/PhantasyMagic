package hugu1026.com.github.phantasymagic.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Gui implements InventoryHolder {

    private Inventory inventory;

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(ItemStack itemStack, int slot) {
        if (itemStack != null) {
            inventory.setItem(slot, itemStack);
        }
    }

    public void createInventory(InventoryHolder owner, int size, String name) {
        inventory = Bukkit.createInventory(owner, size, name);
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    public ItemStack createItemStack(Material material, String name, List<String> lore, int amount) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(name);
        if (lore != null) {
            meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}