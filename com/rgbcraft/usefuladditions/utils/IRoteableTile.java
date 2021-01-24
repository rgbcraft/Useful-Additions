package com.rgbcraft.usefuladditions.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public interface IRoteableTile {

    int getRotation(World world, int x, int y, int z, EntityPlayer entityPlayer, int side);

}
