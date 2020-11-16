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

public interface IToolHead
{
    boolean canHarvestBlock(final int p0, final Block p1);
    
    boolean handleHarvestSpeed(final int p0, final Block p1, final int p2);
    
    int getHarvestSpeed(final int p0, final Block p1, final int p2);
    
    void doEntityDamage(final int p0, final EntityPlayer p1, final Entity p2);
    
    boolean handleEntityDamage(final int p0, final EntityPlayer p1, final Entity p2);
    
    void onRightClick(final int p0, final ItemStack p1, final World p2, final EntityPlayer p3);
    
    boolean damageHead(final int p0, final ItemStack p1, final DamageTypes p2);
    
    int useEnergy(final int p0, final ItemStack p1, final DamageTypes p2);
    
    boolean interactWithEntity(final int p0, final ItemStack p1, final EntityLiving p2);
    
    boolean itemUseBeforeBlock(final int p0, final ItemStack p1, final EntityPlayer p2, final World p3, final int p4, final int p5, final int p6, final int p7, final float p8, final float p9, final float p10);
    
    boolean handleBlockBreak(final int p0, final Block p1);
    
    boolean doBlockBreak(final int p0, final ItemStack p1, final int p2, final int p3, final int p4, final World p5, final EntityPlayer p6);
    
    String getToolName(final ItemStack p0);
}
