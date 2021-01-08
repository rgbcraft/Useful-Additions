package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.IRarityBlock;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSmartSafe extends BlockBase implements IRarityBlock {

	protected BlockSmartSafe(int id) {
		super(id, "smartSafe", Material.iron);

		setBlockUnbreakable();
		setResistance(6000000.0F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ)) {
			if (Utils.isOperator(entityPlayer))
				entityPlayer.openGui(UsefulAdditions.instance, 0, world, x, y, z);
			else
				entityPlayer.openGui(UsefulAdditions.instance, 1, world, x, y, z);
			return true;
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity) {
		int yaw = (int) entity.rotationYaw;

		if (yaw < 0)
			yaw += 360;
		yaw += 22;
		yaw %= 360;
		int facing = yaw / 45;

		world.setBlockAndMetadataWithNotify(x, y, z, blockID, facing / 2);
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
		return UsefulAdditions.proxy.getRenderId("smartSafe");
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.rare;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileSmartSafe();
	}
	
	@Override
	public String getTextureFile() {
		return Utils.getResource(ResourceType.MODEL, "ModelSmartSafe.png");
	}
	
}
