// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;

public class OmniHeadBase implements IToolHead
{
    @Override
    public boolean canHarvestBlock(final int headMeta, final Block theBlock) {
        return false;
    }
    
    @Override
    public boolean handleHarvestSpeed(final int headMeta, final Block theBlock, final int meta) {
        return false;
    }
    
    @Override
    public int getHarvestSpeed(final int headMeta, final Block theBlock, final int meta) {
        return 0;
    }
    
    @Override
    public void doEntityDamage(final int headMeta, final EntityPlayer thePlayer, final Entity theEntity) {
    }
    
    @Override
    public void onRightClick(final int headMeta, final ItemStack theStack, final World world, final EntityPlayer player) {
    }
    
    @Override
    public boolean damageHead(final int headMeta, final ItemStack theHead, final DamageTypes damage) {
        return false;
    }
    
    @Override
    public boolean interactWithEntity(final int headMeta, final ItemStack theOmnitool, final EntityLiving entity) {
        return false;
    }
    
    @Override
    public boolean itemUseBeforeBlock(final int headMeta, final ItemStack stack, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ) {
        return false;
    }
    
    @Override
    public boolean handleBlockBreak(final int headMeta, final Block theBlock) {
        return false;
    }
    
    @Override
    public boolean doBlockBreak(final int headMeta, final ItemStack theOmnitool, final int xPos, final int yPos, final int zPos, final World world, final EntityPlayer player) {
        return false;
    }
    
    @Override
    public boolean handleEntityDamage(final int headMeta, final EntityPlayer thePlayer, final Entity theTarget) {
        return false;
    }
    
    @Override
    public int useEnergy(final int headMeta, final ItemStack theHead, final DamageTypes damage) {
        return 0;
    }
    
    @Override
    public String getToolName(final ItemStack theStack) {
        return "";
    }
}
