package pbone.randomadditions.content.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Arrays;

public class QuiverItem extends ArrowItem {
    public static final int INVENTORY_SIZE = 3;

    public QuiverItem(Settings settings) {
        super(settings);
    }

    public static ItemStack[] getInventory(ItemStack stack) {
        ItemStack[] items = new ItemStack[INVENTORY_SIZE];
        int[] itemAmounts = new int[INVENTORY_SIZE];
        String[] itemTypes = new String[INVENTORY_SIZE];
        CompoundTag tag = stack.getOrCreateTag();

        if (tag.contains("quiverItemAmounts")) {
            // Get saved item stack sizes
            itemAmounts = tag.getIntArray("quiverItemAmounts");
        } else {
            // Make them all stacks of 0 if none are saved
            Arrays.fill(itemAmounts, 0);
            tag.putIntArray("quiverItemAmounts", itemAmounts);
        }

        boolean empty = false;
        if (tag.contains("quiverItem0")) {
            for (int i = 0; i < INVENTORY_SIZE; i++) {
                itemTypes[i] = tag.getString("quiverItem" + i);
            }
        }
        else {
            empty = true;
        }

        if (!empty) {
            for (int i = 0; i < INVENTORY_SIZE; i++) {
                String[] splitId = itemTypes[i].split(":");
                items[i] = new ItemStack(Registry.ITEM.get(new Identifier(splitId[0], splitId[1])), itemAmounts[i]);
            }
        } else {
            Arrays.fill(items, ItemStack.EMPTY);
        }

        return items;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
        CompoundTag tag = stack.getOrCreateTag();

        arrowEntity.initFromStack(new ItemStack(Items.ARROW));


        return arrowEntity;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        CompoundTag tag = stack.getOrCreateTag();

        int[] emptyItemAmounts = new int[INVENTORY_SIZE];
        Arrays.fill(emptyItemAmounts, 0);
        tag.putIntArray("quiverItemAmmounts", emptyItemAmounts);

        ListTag emptyItemTypes = new ListTag();
        CompoundTag itemTypes = stack.getOrCreateSubTag("quiverItemTypes");
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            tag.putString("quiverItem" + i, "");
        }
    }
}
