package com.rgbcraft.usefuladditions.handlers;

import java.util.Random;

import com.rgbcraft.usefuladditions.blocks.Blocks;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenHandler implements IWorldGenerator {

    private WorldGenerator denseCoalOreGen, denseIronOreGen, denseLapisOreGen, denseRedstoneOreGen, denseEmeraldOreGen, denseGoldOreGen, denseDiamondOreGen;

    public WorldGenHandler() {
        GameRegistry.registerWorldGenerator(this);

        this.denseCoalOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 0, 7);
        this.denseIronOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 1, 3);
        this.denseLapisOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 2, 6);
        this.denseRedstoneOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 3, 5);
        this.denseEmeraldOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 4, 1);
        this.denseGoldOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 5, 4);
        this.denseDiamondOreGen = new WorldGenMinable(Blocks.get("denseOre").blockID, 6, 2);
    }

    private void generateStandardOre(Random random, int chunkX, int chunkZ, World world, int iterations, WorldGenerator generator, int lowestY, int highestY) {
        for (int i = 0; i < iterations; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(highestY - lowestY) + lowestY;
            int z = chunkZ * 16 + random.nextInt(16);

            generator.generate(world, random, x, y, z);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            // Overworld
            case 0:
                this.generateStandardOre(random, chunkX, chunkZ, world, 10, this.denseCoalOreGen, 0, 127);
                this.generateStandardOre(random, chunkX, chunkZ, world, 10, this.denseIronOreGen, 0, 63);
                this.generateStandardOre(random, chunkX, chunkZ, world, 8, this.denseLapisOreGen, 0, 30);
                this.generateStandardOre(random, chunkX, chunkZ, world, 4, this.denseRedstoneOreGen, 0, 15);
                this.generateStandardOre(random, chunkX, chunkZ, world, 11, this.denseEmeraldOreGen, 0, 32);
                this.generateStandardOre(random, chunkX, chunkZ, world, 2, this.denseGoldOreGen, 0, 31);
                this.generateStandardOre(random, chunkX, chunkZ, world, 1, this.denseDiamondOreGen, 1, 15);
        }
    }

}
