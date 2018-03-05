package hugu1026.com.github.phantasymagic.gui;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SelfMagicSelectGui extends Gui {

    private ItemStack HEAL;
    private List<String> HEALlore = new ArrayList<>();
    private ItemStack[] magicSet;
    private List<ItemStack> magicSets;
    private Inventory guiSource;
    private int slotSource;
    private HashMap<String, ItemStack> magicListName = new HashMap<>();

    public SelfMagicSelectGui(Inventory inventory, int slotSource) {
        this.createLore();

        this.HEAL = super.createItemStack(Material.POTION, ChatColor.YELLOW + "ヒール /7 マナ /5 スピリット", HEALlore, 1);

        this.magicSet = new ItemStack[]{HEAL};
        this.magicSets = Arrays.asList(magicSet);

        this.slotSource = slotSource;
        this.guiSource = inventory;

        magicListName.put("heal", HEAL);

        super.createInventory(this, 9 * 6, "魔法を選択");
    }

    public void createLore() {
        this.HEALlore.add(0, ChatColor.RED + "発動者の体力を回復させる");
    }

    @Override
    public void openInventory(Player player) {
        List<String> magicList = PlayerDataUtil.getMagicList(player);
        for (String name : magicList) {
            addInventory(magicListName.get(name));
        }
        super.openInventory(player);
    }

    /**
     * return magic icons set as Arrays.
     */
    public ItemStack[] getMagicSet() {
        return this.magicSet;
    }

    /**
     * return magic icons set as collection.
     */
    public List<ItemStack> getMagicSets() {
        return this.magicSets;
    }

    public Inventory getGuiSource() {
        return this.guiSource;
    }

    public int getSlotSource() {
        return this.slotSource;
    }

    public boolean checkMagicAmount(Player player) {
        Inventory guiSource = this.guiSource;
        MagicSquareGui magicSquareGui = (MagicSquareGui) guiSource.getHolder();
        int amount = 0;
        int playerMaxMagicAmount = PlayerDataUtil.getMagicAmountLimit(player);

        for (int i = 0; i < 45; i++) {
            ItemStack magicIcon = guiSource.getItem(i);
            if (!magicSquareGui.checkItemisIcon(magicIcon)) {
                amount++;
            }
        }
        return amount == playerMaxMagicAmount;
    }
}
