package pbone.randomadditions.content.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class AutoSmeltEnchantment extends Enchantment {
    public AutoSmeltEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level){
        return 15;
    }

    public int getMaxPower(int level){
        return super.getMinPower(level) + 50;
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    public boolean canAccept(Enchantment other){
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }
}
