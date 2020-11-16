package com.rgbcraft.indeng.handlers;

import java.util.Random;

import com.rgbcraft.indeng.blocks.Blocks;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenerationHandler implements IWorldGenerator {
	
	private WorldGenerator denseCoalOreGen, denseIronOreGen, denseLapisOreGen, denseRedstoneOreGen, denseEmeraldOreGen, denseGoldOreGen, denseDiamondOreGen; 
	
	public WorldGenerationHandler() {
		GameRegistry.registerWorldGenerator(this);

		denseCoalOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 0, 8);
		denseIronOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 1, 7);
		denseLapisOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 2, 15);
		denseRedstoneOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 3, 4);
		denseEmeraldOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 4, 1);
		denseGoldOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 5, 4);
		denseDiamondOreGen = new WorldGenMinable(Blocks.get("blockDenseOre").blockID, 6, 5);
	}
	
	private void generateStandardOre(Random random, int chunkX, int chunkZ, World world, int iterations, WorldGenerator generator, int lowestY, int highestY) {
		for (int i = 0; i < iterations; i++) {
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(highestY - lowestY) + lowestY;
			int z = chunkZ + random.nextInt(16);
			
			generator.generate(world, random, x, y, z);
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateStandardOre(random, chunkX, chunkZ, world, 10, denseCoalOreGen, 0, 127);
		generateStandardOre(random, chunkX, chunkZ, world, 10, denseIronOreGen, 0, 63);
		generateStandardOre(random, chunkX, chunkZ, world, 10, denseLapisOreGen, 0, 30);
		generateStandardOre(random, chunkX, chunkZ, world, 4, denseRedstoneOreGen, 0, 15);
		generateStandardOre(random, chunkX, chunkZ, world, 11, denseEmeraldOreGen, 0, 32);
		generateStandardOre(random, chunkX, chunkZ, world, 2, denseGoldOreGen, 0, 31);
		generateStandardOre(random, chunkX, chunkZ, world, 1, denseDiamondOreGen, 1, 15);
	}

}
