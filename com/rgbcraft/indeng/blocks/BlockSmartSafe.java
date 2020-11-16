package com.rgbcraft.indeng.blocks;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.BlockInventory;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSmartSafe extends BlockInventory {

	protected BlockSmartSafe(int id) {
		super(id, Material.iron);
		
		setBlockName("blockSmartSafe");
		setBlockUnbreakable();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		FMLNetworkHandler.openGui(player, IndustrialEngineering.instance, 0, world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity) {
		if (entity instanceof EntityPlayer) {
			int yaw = (int) entity.rotationYaw;

			if (yaw < 0)
				yaw += 360;
			yaw += 22;
			yaw %= 360;
			int facing = yaw / 45;

			world.setBlockAndMetadataWithNotify(x, y, z, blockID, facing / 2);
		}
	}
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    	setBlockBounds(0.05F, 0F, 0.05F, 0.95F, 1F, 0.95F);
    }
    
    @Override
    public boolean renderAsNormalBlock() {
    	return false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
    	setBlockBoundsBasedOnState(world, x, y , z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
    	setBlockBoundsBasedOnState(world, x, y , z);
    	return super.collisionRayTrace(world, x, y, z, start, end);
    }
    
    @Override
    public void setBlockBoundsForItemRender() {
    	setBlockBounds(0, 0, 0, 1, 1, 1);
    }
    
    @Override
    public int getRenderType() {
		return IndustrialEngineering.proxy.getRenderId("blockSmartSafe");
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileSmartSafe("0000");
	}
	
}
