package hugu1026.com.github.phantasymagic.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MagicSquareGui extends Gui {
    private static final int barrierSlot[] = {
            0, 1, 7, 8, 9, 10, 16, 17, 18, 19, 25, 27, 28,
            34, 35, 36, 37, 43, 44
    };
    private static final int panelSlot[] = {
            2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24,
            29, 30, 31, 32, 33, 38, 39, 41, 42
    };
    private static final int player_headSlot = 40;
    private static final int create_magic_itemSlot = 26;
    private ItemStack PLAYER_HEAD, PANEL, BARRIER_BLOCK, CREATE_MAGIC_ITEM;

    public MagicSquareGui() {
        PLAYER_HEAD = super.createItemStack(Material.SKULL_ITEM, ChatColor.LIGHT_PURPLE + "プレイヤーの位置", null, 1);
        PANEL = super.createItemStack(Material.STAINED_GLASS_PANE, ChatColor.YELLOW + "ここに魔法を設置する", null, 1);
        BARRIER_BLOCK = super.createItemStack(Material.BARRIER, " ", null, 1);
        CREATE_MAGIC_ITEM = super.createItemStack(Material.EXP_BOTTLE, ChatColor.YELLOW + "この魔方陣で魔法を作る", null, 1);

        super.createInventory(this, 9 * 5, "魔法陣");
    }

    @Override
    public void openInventory(Player player) {
        for (int i : panelSlot) {
            super.setInventory(this.PANEL, i);
        }
        super.setInventory(this.PLAYER_HEAD, player_headSlot);
        for (int i : barrierSlot) {
            super.setInventory(this.BARRIER_BLOCK, i);
        }
        super.setInventory(this.CREATE_MAGIC_ITEM, create_magic_itemSlot);
        super.openInventory(player);
    }

    public boolean canOpen(Block block) {
        return block.getType() == Material.ENCHANTMENT_TABLE;
    }

    public ItemStack getPLAYER_HEAD() {
        return this.PLAYER_HEAD;
    }

    public ItemStack getPANEL() {
        return this.PANEL;
    }

    public ItemStack getBARRIER_BLOCK() {
        return this.BARRIER_BLOCK;
    }

    public ItemStack getCREATE_MAGIC_ITEM() {
        return this.CREATE_MAGIC_ITEM;
    }

    public boolean checkItemisIcon(ItemStack item) {
        return item.equals(this.BARRIER_BLOCK)
                || item.equals(this.CREATE_MAGIC_ITEM)
                || item.equals(this.PANEL)
                || item.equals(this.PLAYER_HEAD);
    }
}