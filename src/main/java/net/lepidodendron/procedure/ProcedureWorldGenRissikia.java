package net.lepidodendron.procedure;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockRissikiaLeaves;
import net.lepidodendron.block.BlockRissikiaLeavesBerries;
import net.lepidodendron.block.BlockPodocarpLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenRissikia extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenRissikia(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenRissikia!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenRissikia!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenRissikia!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenRissikia!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		int TrunkHeight = 10 + (int) (Math.round(Math.random() * 18D));
		int counter = 0;
		int yy = y;
		int upright = 0;
		int yyy = 0;
		Random rand = new Random();

		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& material != Material.GRASS
			&& material != Material.GROUND
			&& material != Material.GLASS
			&& material != Material.IRON
			&& material != Material.ROCK
			&& material != Material.SAND
			&& material != Material.WOOD
		) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			counter = 0;
			while (counter <= TrunkHeight) {
				yy = y + counter;
				ProcedureTreeLog.executeProcedure((int) x, (int) yy, (int) z, world, BlockPodocarpLog.block, EnumFacing.NORTH);
				counter = counter + 1;
			}

			//Topshoot:
			ProcedureTreeLeaf.executeProcedure(x, y + counter, z, world, getLeaves());
			ProcedureTreeLeaf.executeProcedure(x + 1, y + counter - 1 , z, world, getLeaves());
			ProcedureTreeLeaf.executeProcedure(x - 1, y + counter - 1 , z, world, getLeaves());
			ProcedureTreeLeaf.executeProcedure(x, y + counter - 1 , z + 1, world, getLeaves());
			ProcedureTreeLeaf.executeProcedure(x, y + counter - 1 , z - 1, world, getLeaves());

			//Top layer, a bit random....
			yy = y + counter - 2;
			if (Math.random() > 0.85) {
				//North
				ProcedureTreeLog.executeProcedure(x, yy, z - 1, world, BlockPodocarpLog.block, EnumFacing.WEST);
				ProcedureLeavesAroundLog.executeProcedure(x, yy, z - 1, world, getLeaves(), 1, 0.4);
			}
			if (Math.random() > 0.85) {
				//South
				ProcedureTreeLog.executeProcedure(x, yy, z + 1, world, BlockPodocarpLog.block, EnumFacing.WEST);
				ProcedureLeavesAroundLog.executeProcedure(x, yy, z + 1, world, getLeaves(), 1, 0.4);
			}
			if (Math.random() > 0.85) {
				//East
				ProcedureTreeLog.executeProcedure(x + 1, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
				ProcedureLeavesAroundLog.executeProcedure(x + 1, yy, z, world, getLeaves(), 1, 0.4);
			}
			if (Math.random() > 0.85) {
				//West
				ProcedureTreeLog.executeProcedure(x - 1, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
				ProcedureLeavesAroundLog.executeProcedure(x - 1, yy, z, world, getLeaves(), 1, 0.4);
			}

			yy = y + counter - 3;
			ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0);
			yy = yy - 1;
			ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0.2);

			yy = yy - 1;

			while (yy > (Math.round((double)TrunkHeight * 0.58) + y)) {
				boolean branch = false;
				//Try north:
				if (Math.random() > 0.66) {
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x, yy, z - 1, world, BlockPodocarpLog.block, EnumFacing.EAST);
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z - 1, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x, yy, z - 2, world, BlockPodocarpLog.block, EnumFacing.EAST);
					upright = rand.nextInt(3);
					yyy = 1;
					while (yyy <= upright) {
						ProcedureTreeLog.executeProcedure(x, yy + yyy, z - 2, world, BlockPodocarpLog.block, EnumFacing.NORTH);
						ProcedureLeavesAroundLog.executeProcedure(x, yy + yyy, z - 2, world, getLeaves(), 1, 0.5);
						yyy = yyy + 1;
					}
					branch = true;
				}

				//Try south:
				if (Math.random() > 0.66) {
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x, yy, z + 1, world, BlockPodocarpLog.block, EnumFacing.EAST);
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z + 1, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x, yy, z + 2, world, BlockPodocarpLog.block, EnumFacing.EAST);
					upright = rand.nextInt(3);
					yyy = 1;
					while (yyy <= upright) {
						ProcedureTreeLog.executeProcedure(x, yy + yyy, z + 2, world, BlockPodocarpLog.block, EnumFacing.NORTH);
						ProcedureLeavesAroundLog.executeProcedure(x, yy + yyy, z + 2, world, getLeaves(), 1, 0.5);
						yyy = yyy + 1;
					}
					branch = true;
				}

				//Try east:
				if (Math.random() > 0.66) {
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x + 1, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(x + 1, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x + 2, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
					upright = rand.nextInt(3);
					yyy = 1;
					while (yyy <= upright) {
						ProcedureTreeLog.executeProcedure(x + 2, yy + yyy, z, world, BlockPodocarpLog.block, EnumFacing.NORTH);
						ProcedureLeavesAroundLog.executeProcedure(x + 2, yy + yyy, z, world, getLeaves(), 1, 0.5);
						yyy = yyy + 1;
					}
					branch = true;
				}

				//Try west:
				if (Math.random() > 0.66) {
					ProcedureLeavesAroundLog.executeProcedure(x, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x - 1, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(x - 1, yy, z, world, getLeaves(), 1, 0.5);
					ProcedureTreeLog.executeProcedure(x - 2, yy, z, world, BlockPodocarpLog.block, EnumFacing.UP);
					upright = rand.nextInt(3);
					yyy = 1;
					while (yyy <= upright) {
						ProcedureTreeLog.executeProcedure(x - 2, yy + yyy, z, world, BlockPodocarpLog.block, EnumFacing.NORTH);
						ProcedureLeavesAroundLog.executeProcedure(x - 2, yy + yyy, z, world, getLeaves(), 1, 0.5);
						yyy = yyy + 1;
					}
					branch = true;
				}

				if (!branch) {
					yy = yy - 1;
				}
				else {
					yy = yy - 2 - (int) Math.round((Math.random() * 2D));
				}

			}

		}
	}


	public static Block getLeaves()
	{
		if (Math.random() > 0.8) {
			return BlockRissikiaLeavesBerries.block;
		}
		return BlockRissikiaLeaves.block;
	}
	
}