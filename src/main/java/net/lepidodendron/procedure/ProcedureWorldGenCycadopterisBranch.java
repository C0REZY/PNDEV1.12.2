package net.lepidodendron.procedure;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockCycadopterisLog;
import net.lepidodendron.block.BlockCycadopterisShoot;
import net.lepidodendron.util.Functions;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenCycadopterisBranch extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenCycadopterisBranch(ElementsLepidodendronMod instance) {
		super(instance, 328);
	}

	
	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenCycadopterisBranch!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenCycadopterisBranch!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenCycadopterisBranch!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenCycadopterisBranch!");
			return;
		}
		if (dependencies.get("counter") == null) {
			System.err.println("Failed to load dependency counter for procedure WorldGenCycadopterisBranch!");
			return;
		}
		if (dependencies.get("TreeHeight") == null) {
			System.err.println("Failed to load dependency TreeHeight for procedure WorldGenCycadopterisBranch!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		double counter = (double) dependencies.get("counter");
		double TreeHeight = (double) dependencies.get("TreeHeight");
		World world = (World) dependencies.get("world");
		Block block;
		boolean Trunkable;

		while (
			(counter < TreeHeight) 
			&& (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z)).getBlock() == BlockCycadopterisLog.block)
			)
		{
			//Can we trunk up?
			Trunkable = true;
			if ((world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z + 1)).getBlock() == BlockCycadopterisLog.block)
			|| (world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z - 1)).getBlock() == BlockCycadopterisLog.block)
			|| (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter, (int) z)).getBlock() == BlockCycadopterisLog.block)
			|| (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter, (int) z)).getBlock() == BlockCycadopterisLog.block)
			|| (world.getBlockState(new BlockPos((int) x, (int) y + counter + 1, (int) z)).getBlock() == BlockCycadopterisLog.block)
			) {Trunkable = false;}
							
			if (((Math.random() * (counter/TreeHeight)) < 0.08) && Trunkable) {
				//Just trunk up:
				block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter , (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
					Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter , (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
				}
			}
			else {
				//Shall we top off here anyway?
				if (((Math.random() * (counter/TreeHeight)) > 0.95) || (counter > TreeHeight)) {
					//if (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z)).getBlock() == BlockCycadopterisLog.block) {
						block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
						if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
							Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter, (int) z), BlockCycadopterisShoot.block.getDefaultState(), 3);
						}
					//}
					break;
				}
				else {
					if (((Math.random()) < 0.04) && Trunkable) {
						//Just trunk up:
						block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
						if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter , (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
							Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter , (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
						}
						Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
						$_dependencies.put("x", (int) x);
						$_dependencies.put("y", (int) y);
						$_dependencies.put("z", (int) z);
						$_dependencies.put("world", world);
						$_dependencies.put("TreeHeight", TreeHeight);
						$_dependencies.put("counter", counter + 1);
						ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
						//break;
					}
					else {
						//Branch here:
						//Block block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
						//if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter , (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
						//	Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter , (int) z), BlockCycadopterisShoot.block.getDefaultState(), 3);
						//}
						//Which way?
						if (Math.random() > 0.5) {
							block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
							if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
								Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter, (int) z), BlockCycadopterisShoot.block.getDefaultState(), 3);
							}
						}

						if (Math.random() > 0.5) {
							//North/South:
							block = world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z - 1)).getBlock();
							if (
								(block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z - 1)), world, new BlockPos((int) x, (int) y + counter - 1, (int) z - 1))) 
								&& (block != BlockCycadopterisShoot.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1 - 1, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z - 2)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								)
							{
								Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter - 1, (int) z - 1), BlockCycadopterisLog.block.getDefaultState(), 3);
								//And start again:
								block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z - 1)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z - 1)), world, new BlockPos((int) x, (int) y + counter, (int) z - 1))) {
									Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter, (int) z - 1), BlockCycadopterisLog.block.getDefaultState(), 3);
									Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
									$_dependencies.put("x", (int) x);
									$_dependencies.put("y", (int) y);
									$_dependencies.put("z", (int) z - 1);
									$_dependencies.put("world", world);
									$_dependencies.put("TreeHeight", TreeHeight);
									$_dependencies.put("counter", counter + 1);
									ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
								}
							}
							
							block = world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z + 1)).getBlock();
							if (
								(block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z + 1)), world, new BlockPos((int) x, (int) y + counter - 1, (int) z + 1))) 
								&& (block != BlockCycadopterisShoot.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1 - 1, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z + 2)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								)
							{
							
							//if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z + 1)), world, new BlockPos((int) x, (int) y + counter - 1, (int) z + 1))) {
								Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter - 1, (int) z + 1), BlockCycadopterisLog.block.getDefaultState(), 3);
								//And start again:
								block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z + 1)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z + 1)), world, new BlockPos((int) x, (int) y + counter, (int) z + 1))) {
									Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter, (int) z + 1), BlockCycadopterisLog.block.getDefaultState(), 3);
									Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
									$_dependencies.put("x", (int) x);
									$_dependencies.put("y", (int) y);
									$_dependencies.put("z", (int) z + 1);
									$_dependencies.put("world", world);
									$_dependencies.put("TreeHeight", TreeHeight);
									$_dependencies.put("counter", counter + 1);
									ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
								}
							}
						}
						else {
							//East/West:
							block = world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z)).getBlock();
							if (
								(block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z)), world, new BlockPos((int) x - 1, (int) y + counter - 1, (int) z))) 
								&& (block != BlockCycadopterisShoot.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1 - 1, (int) z)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 2, (int) y + counter - 1, (int) z)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x - 1, (int) y + counter, (int) z)).getBlock() != BlockCycadopterisLog.block)
								)
							{
							
							//if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) y + counter - 1, (int) z)), world, new BlockPos((int) x - 1, (int) y + counter - 1, (int) z))) {
								Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x - 1, (int) y + counter - 1, (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
								//And start again:
								block = world.getBlockState(new BlockPos((int) x - 1, (int) y + counter, (int) z)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) y + counter, (int) z)), world, new BlockPos((int) x - 1, (int) y + counter, (int) z ))) {
									Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x - 1, (int) y + counter, (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
									Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
									$_dependencies.put("x", (int) x - 1);
									$_dependencies.put("y", (int) y);
									$_dependencies.put("z", (int) z);
									$_dependencies.put("world", world);
									$_dependencies.put("TreeHeight", TreeHeight);
									$_dependencies.put("counter", counter + 1);
									ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
								}
							}
							block = world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z)).getBlock();
							if (
								(block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z)), world, new BlockPos((int) x + 1, (int) y + counter - 1, (int) z))) 
								&& (block != BlockCycadopterisShoot.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1 - 1, (int) z)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 2, (int) y + counter - 1, (int) z)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z + 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z - 1)).getBlock() != BlockCycadopterisLog.block)
								&& (world.getBlockState(new BlockPos((int) x + 1, (int) y + counter, (int) z)).getBlock() != BlockCycadopterisLog.block)
								)
							{
							//if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) y + counter - 1, (int) z)), world, new BlockPos((int) x + 1, (int) y + counter - 1, (int) z))) {
								Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x + 1, (int) y + counter - 1, (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
								//And start again:
								block = world.getBlockState(new BlockPos((int) x + 1, (int) y + counter, (int) z)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) y + counter, (int) z)), world, new BlockPos((int) x + 1, (int) y + counter, (int) z ))) {
									Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x + 1, (int) y + counter, (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);
									Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
									$_dependencies.put("x", (int) x + 1);
									$_dependencies.put("y", (int) y);
									$_dependencies.put("z", (int) z);
									$_dependencies.put("world", world);
									$_dependencies.put("TreeHeight", TreeHeight);
									$_dependencies.put("counter", counter + 1);
									ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
								}
							}
						}
					}

				}
				
			}

			counter = counter + 1;
		}

		if (world.getBlockState(new BlockPos((int) x, (int) y + counter - 1, (int) z)).getBlock() == BlockCycadopterisLog.block) {
			block = world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y + counter, (int) z)), world, new BlockPos((int) x, (int) y + counter, (int) z))) {
				Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y + counter, (int) z), BlockCycadopterisShoot.block.getDefaultState(), 3);
			}
		}
	}
}