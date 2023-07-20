package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockCedarLeaves;
import net.lepidodendron.block.BlockCedarLog;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenCedar extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenCedar(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenCedar!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenCedar!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenCedar!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenCedar!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = (int) dependencies.get("x");
		int yy = (int) dependencies.get("y");
		int zz = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double TrunkHeight = 0;
		double counter = 0;
		int randomiser = 0;
		int randomiser2 = 0;
		String BranchAxis = "";
		double layer = 0;
		double branchskip = 0;
		boolean try2 = false;
		Random rand = world.rand;
		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		Material material1 = world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z - 1))).getMaterial();
		Material material2= world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)).getMaterial();
		Material material3 = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
				&& material != Material.GRASS
				&& material != Material.GROUND
				&& material != Material.GLASS
				&& material != Material.IRON
				&& material != Material.ROCK
				&& material != Material.SAND
				&& material != Material.WOOD
				&& (world.canSeeSky(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z - 1))))
				&& material1 != Material.GRASS
				&& material1 != Material.GROUND
				&& material1 != Material.GLASS
				&& material1 != Material.IRON
				&& material1 != Material.ROCK
				&& material1 != Material.SAND
				&& material1 != Material.WOOD
				&& (world.canSeeSky(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)))
				&& material2 != Material.GRASS
				&& material2 != Material.GROUND
				&& material2 != Material.GLASS
				&& material2 != Material.IRON
				&& material2 != Material.ROCK
				&& material2 != Material.SAND
				&& material2 != Material.WOOD
				&& (world.canSeeSky(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))))
				&& material3 != Material.GRASS
				&& material3 != Material.GROUND
				&& material3 != Material.GLASS
				&& material3 != Material.IRON
				&& material3 != Material.ROCK
				&& material3 != Material.SAND
				&& material3 != Material.WOOD
		) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			world.setBlockToAir(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)));
			world.setBlockToAir(new BlockPos((int) (x + 1), (int) y, (int) z));
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) (z - 1)));
			ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y - 1), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y - 1), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure((int) x, (int) (y - 1), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);

			//Trunk:
			TrunkHeight = 6 + (Math.random() * 4) + (Math.random() * 4);

			counter = 0;
			while (counter <= TrunkHeight) {
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);
				counter = counter + 1;
			}

			//Pick a side for the top branch
			int ii = rand.nextInt(4);
			//ii = 0;
			switch (ii) {
				case 0: default:
					//North
					randomiser = rand.nextInt(2) + 1;
					limbSouthNorth(world, x, (int) (y + counter - 1), z - randomiser, rand, -1, true);
					setUpright(world, x, (int) (y + counter - 1) + 3, z - randomiser - 2 - rand.nextInt(2), rand);
					break;

				case 1:
					//South
					randomiser = rand.nextInt(2) + 1;
					limbSouthNorth(world, x, (int) (y + counter - 1), z + randomiser, rand, 1, true);
					setUpright(world, x, (int) (y + counter - 1) + 3, z + randomiser + 2 - rand.nextInt(2), rand);
					break;

				case 2:
					//East
					randomiser = rand.nextInt(2) + 1;
					limbEastWest(world, x + randomiser, (int) (y + counter - 1), z, rand, 1, true);
					setUpright(world, x + randomiser + 2 - rand.nextInt(2), (int) (y + counter - 1) + 3, z, rand);
					break;

				case 3:
					//West
					randomiser = rand.nextInt(2) + 1;
					limbEastWest(world, x - randomiser, (int) (y + counter - 1), z, rand, -1, true);
					setUpright(world, x - randomiser - 2 - rand.nextInt(2), (int) (y + counter - 1) + 3, z, rand);
					break;
			}

			//Other branches:
			if (ii != 0) {
				randomiser = rand.nextInt(2) + 1;
				randomiser2 = rand.nextInt(4) + 3;
				limbSouthNorth(world, x, (int) ((y + counter - 1) - randomiser2), z - randomiser, rand, -1, false);
			}
			//South:
			if (ii != 1) {
				randomiser = rand.nextInt(2) + 1;
				randomiser2 = rand.nextInt(4) + 3;
				limbSouthNorth(world, x, (int) ((y + counter - 1) - randomiser2), z + randomiser, rand, 1, false);
			}
			//East:
			if (ii != 2) {
				randomiser = rand.nextInt(2) + 1;
				randomiser2 = rand.nextInt(4) + 3;
				limbEastWest(world, x + randomiser, (int) ((y + counter - 1) - randomiser2), z, rand, 1, false);
			}
			//West:
			if (ii != 3) {
				randomiser = rand.nextInt(2) + 1;
				randomiser2 = rand.nextInt(4) + 3;
				limbEastWest(world, x - randomiser, (int) ((y + counter - 1) - randomiser2), z, rand, -1, false);
			}


		}
	}

	public static void limbSouthNorth(World world, int x, int y, int z, Random rand, int flip, boolean top) {
		int n = 0;
		if (flip == -1) {
			n = -1;
		}
		if (rand.nextInt(2) == 0 && !top) {
			//Small:
			ProcedureTreeLog.executeProcedure(x, y, z + n, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z + n, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z + n + (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z + n, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z + n + (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z + n + (2 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 3, z + n + (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 3, z + n + (2 * flip), world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x, y + 3, z + n + (3 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 4, z + n + (2 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 4, z + n + (3 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);

			setUprightSmall(world, x, y + 5, z + n + (3 * flip), rand);
		}
		else {
			//Large:
			ProcedureTreeLog.executeProcedure(x, y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y, z - (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z, world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x, y + 1, z - (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z + (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z, world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x, y + 2, z + (1 * flip), world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x, y + 2, z + (2 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);

			ProcedureTreeLog.executeProcedure(x + 1, y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + 1, y, z - (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + 1, y + 1, z, world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x + 1, y + 1, z - (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + 1, y + 1, z + (1 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + 1, y + 2, z, world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x + 1, y + 2, z + (1 * flip), world, BlockCedarLog.block, EnumFacing.EAST);
			ProcedureTreeLog.executeProcedure(x + 1, y + 2, z + (2 * flip), world, BlockCedarLog.block, EnumFacing.NORTH);

			if (!top) {
				setUpright(world, x, y + 3, z + (2 * flip), rand);
			}
		}
	}

	public static void limbEastWest(World world, int x, int y, int z, Random rand, int flip, boolean top) {
		int n = 0;
		if (flip == -1) {
			n = 1;
		}
		if (rand.nextInt(2) == 0 && !top) {
			//Small:
			ProcedureTreeLog.executeProcedure(x + n, y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n, y + 1, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (1 * flip), y + 1, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n, y + 2, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (1 * flip), y + 2, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (2 * flip), y + 2, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (1 * flip), y + 3, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (2 * flip), y + 3, z, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x + n + (3 * flip), y + 3, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (2 * flip), y + 4, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + n + (3 * flip), y + 4, z, world, BlockCedarLog.block, EnumFacing.NORTH);

			setUprightSmall(world, x + n + (3 * flip), y + 5, z, rand);
		}
		else {
			//Large:
			ProcedureTreeLog.executeProcedure(x, y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x - (1 * flip), y, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x- (1 * flip), y + 1, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + (1 * flip), y + 1, z, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x + (1 * flip), y + 2, z, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x + (2 * flip), y + 2, z, world, BlockCedarLog.block, EnumFacing.NORTH);

			ProcedureTreeLog.executeProcedure(x, y, z - 1, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x - (1 * flip), y, z - 1, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 1, z - 1, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x - (1 * flip), y + 1, z - 1, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x + (1 * flip), y + 1, z - 1, world, BlockCedarLog.block, EnumFacing.NORTH);
			ProcedureTreeLog.executeProcedure(x, y + 2, z - 1, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x + (1 * flip), y + 2, z - 1, world, BlockCedarLog.block, EnumFacing.UP);
			ProcedureTreeLog.executeProcedure(x + (2 * flip), y + 2, z - 1, world, BlockCedarLog.block, EnumFacing.NORTH);

			if (!top) {
				setUpright(world, x + (2 * flip), y + 3, z, rand);
			}
		}
	}

	public static void setUpright(World world, int x, int y, int z, Random rand) {

		int xx = x;
		int yy = y;
		int zz = z;
		double TrunkHeight = 0;
		double counter = 0;
		double counterext = 0;
		double randomiser = 0;
		String BranchAxis = "";
		double layer = 0;
		double branchskip = 0;
		boolean try2 = false;
		//Trunk:
		TrunkHeight = 12 + rand.nextInt(4) + rand.nextInt(4);

		counter = 0;
		while (counter <= TrunkHeight) {
			ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
			if (counter < TrunkHeight * 0.90D) {
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) (z - 1), world, BlockCedarLog.block, EnumFacing.NORTH);
			}
			if (counter >= TrunkHeight * 0.90D && counter < TrunkHeight - 2) {
				ProcedureTreeLeaf.executeProcedure((int) (x + 1), (int) (y + counter), (int) (z - 1), world, BlockCedarLeaves.block);
				ProcedureTreeLeaf.executeProcedure((int) (x + 1), (int) (y + counter), (int) z, world, BlockCedarLeaves.block);
				ProcedureTreeLeaf.executeProcedure((int) x, (int) (y + counter), (int) (z - 1), world, BlockCedarLeaves.block);
			}
			counter = counter + 1;
		}

		xx = x;
		zz = z;

		randomiser = Math.random();

		while (counter <= TrunkHeight + 2 + Math.floor(2 * randomiser)) {
			ProcedureTreeLog.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLog.block, EnumFacing.NORTH);
			//Add leaves, but not always:
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) (xx + 1), (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) (xx - 1), (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) (zz + 1), world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) (zz - 1), world, BlockCedarLeaves.block);
			}
			counter = counter + 1;
		}
		//A last few leaves on the top:
		ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
		if (Math.random() > 0.5) {
			ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter + 1), (int) zz, world, BlockCedarLeaves.block);
		}

		counter = TrunkHeight;
		//Scatter some leaves around the main trunk top:
		while (counter >= TrunkHeight - 2) {
			if (Math.random() > 0.7) {
				xx = x - 1;
				zz = z;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x + 1;
				zz = z;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x;
				zz = z - 1;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x;
				zz = z + 1;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}

			counter = counter - 1;
		}


		//Generate branches:
		//We spiral round the tree, skipping one to three rows each time, picking a random point of attachment and adding 0-2 nubs of branch befor the main end:
		//Pick a direction to start:
		randomiser = Math.random();
		if (randomiser >= 0.75) {
			BranchAxis = "north";
		} else {
			if (randomiser >= 0.5) {
				BranchAxis = "east";
			} else {
				if (randomiser >= 0.25) {
					BranchAxis = "south";
				} else {
					BranchAxis = "west";
				}
			}
		}


		while (counter >= 0) {
			//rotate to the next axis
			if (BranchAxis == "north") {
				BranchAxis = "east";
			} else {
				if (BranchAxis == "east") {
					BranchAxis = "south";
				} else {
					if (BranchAxis == "south") {
						BranchAxis = "west";
					} else {
						BranchAxis = "north";
					}
				}
			}


			//Get our attachment position:
			if (BranchAxis == "east") {
				xx = x + 2;
				yy = (int) (y + counter);
				zz = z - world.rand.nextInt(2);
				//Instead of randomly adding the extenders, they are proporional to the distance from the top:
				//randomiser = Math.ceil((TrunkHeight-counter)/(TrunkHeight/5))

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 1, world, BlockCedarLog.block, EnumFacing.EAST);
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 2, world, BlockCedarLog.block, EnumFacing.EAST);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz + 2);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz - 1, world, BlockCedarLog.block, EnumFacing.EAST);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz - 1);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
					}

					xx = xx + 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);
			}

			if (BranchAxis == "west") {
				xx = x - 1;
				yy = (int) (y + counter);
				zz = z - world.rand.nextInt(2);

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 1, world, BlockCedarLog.block, EnumFacing.EAST);
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 2, world, BlockCedarLog.block, EnumFacing.EAST);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz + 2);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz - 1, world, BlockCedarLog.block, EnumFacing.EAST);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz - 1);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
					}

					xx = xx - 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}

				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
			}

			if (BranchAxis == "north") {
				xx = x + world.rand.nextInt(2);
				yy = (int) (y + counter);
				zz = z - 2;

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx + 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						ProcedureTreeLog.executeProcedure((int) xx + 2, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx + 2);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx - 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx - 1);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
					}

					zz = zz - 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
			}

			if (BranchAxis == "south") {
				xx = x + world.rand.nextInt(2);
				yy = (int) (y + counter);
				zz = z + 1;

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 6)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx + 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						ProcedureTreeLog.executeProcedure((int) xx + 2, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx + 2);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx - 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx - 1);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
					}

					zz = zz + 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);
			}

			//Skip 1 to three rows and then trigger next branch
			if (world.rand.nextInt(2) == 0) {
				counter = counter - Math.round(Math.random());
			} else {
				counter = counter - Math.round(Math.random() * 2);
			}

		}
	}

	public static void setUprightSmall(World world, int x, int y, int z, Random rand) {

		int xx = x;
		int yy = y;
		int zz = z;
		double TrunkHeight = 0;
		double counter = 0;
		double counterext = 0;
		double randomiser = 0;
		String BranchAxis = "";
		double layer = 0;
		double branchskip = 0;
		boolean try2 = false;
		//Trunk:
		TrunkHeight = 6 + rand.nextInt(4) + rand.nextInt(4);

		counter = 0;
		while (counter <= TrunkHeight) {
			ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockCedarLog.block, EnumFacing.NORTH);
			counter = counter + 1;
		}

		xx = x;
		zz = z;

		randomiser = Math.random();

		while (counter <= TrunkHeight + 2 + Math.floor(2 * randomiser)) {
			ProcedureTreeLog.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLog.block, EnumFacing.NORTH);
			//Add leaves, but not always:
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) (xx + 1), (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) (xx - 1), (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) (zz + 1), world, BlockCedarLeaves.block);
			}
			if (((Math.random() > 0.2) || (counter == TrunkHeight + 1)) && (counter != TrunkHeight + 2)) {
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) (zz - 1), world, BlockCedarLeaves.block);
			}
			counter = counter + 1;
		}
		//A last few leaves on the top:
		ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
		if (Math.random() > 0.5) {
			ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter + 1), (int) zz, world, BlockCedarLeaves.block);
		}

		counter = TrunkHeight;
		//Scatter some leaves around the main trunk top:
		while (counter >= TrunkHeight - 2) {
			if (Math.random() > 0.7) {
				xx = x - 1;
				zz = z;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x + 1;
				zz = z;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x;
				zz = z - 1;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}
			if (Math.random() > 0.7) {
				xx = x;
				zz = z + 1;
				ProcedureTreeLeaf.executeProcedure((int) xx, (int) (y + counter), (int) zz, world, BlockCedarLeaves.block);
			}

			counter = counter - 1;
		}


		//Generate branches:
		//We spiral round the tree, skipping one to three rows each time, picking a random point of attachment and adding 0-2 nubs of branch befor the main end:
		//Pick a direction to start:
		randomiser = Math.random();
		if (randomiser >= 0.75) {
			BranchAxis = "north";
		} else {
			if (randomiser >= 0.5) {
				BranchAxis = "east";
			} else {
				if (randomiser >= 0.25) {
					BranchAxis = "south";
				} else {
					BranchAxis = "west";
				}
			}
		}


		while (counter >= 0) {
			//rotate to the next axis
			if (BranchAxis == "north") {
				BranchAxis = "east";
			} else {
				if (BranchAxis == "east") {
					BranchAxis = "south";
				} else {
					if (BranchAxis == "south") {
						BranchAxis = "west";
					} else {
						BranchAxis = "north";
					}
				}
			}


			//Get our attachment position:
			if (BranchAxis == "east") {
				xx = x + 1;
				yy = (int) (y + counter);
				zz = z - world.rand.nextInt(2);
				//Instead of randomly adding the extenders, they are proporional to the distance from the top:
				//randomiser = Math.ceil((TrunkHeight-counter)/(TrunkHeight/5))

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 1, world, BlockCedarLog.block, EnumFacing.EAST);
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 2, world, BlockCedarLog.block, EnumFacing.EAST);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz + 2);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz - 1, world, BlockCedarLog.block, EnumFacing.EAST);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz - 1);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
					}

					xx = xx + 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);
			}

			if (BranchAxis == "west") {
				xx = x - 1;
				yy = (int) (y + counter);
				zz = z - world.rand.nextInt(2);

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 1, world, BlockCedarLog.block, EnumFacing.EAST);
						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 2, world, BlockCedarLog.block, EnumFacing.EAST);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz + 2);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz - 1, world, BlockCedarLog.block, EnumFacing.EAST);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz - 1);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
					}

					xx = xx - 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}

				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
			}

			if (BranchAxis == "north") {
				xx = x + world.rand.nextInt(2);
				yy = (int) (y + counter);
				zz = z - 1;

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx + 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						ProcedureTreeLog.executeProcedure((int) xx + 2, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx + 2);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx - 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx - 1);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
					}

					zz = zz - 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchNorth.executeProcedure($_dependencies);
			}

			if (BranchAxis == "south") {
				xx = x + world.rand.nextInt(2);
				yy = (int) (y + counter);
				zz = z + 1;

				int siderandomiser = world.rand.nextInt(4);

				randomiser = 0;
				while (randomiser < Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4))) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, (int) yy, zz, world, BlockCedarLeaves.block, 2, 0.1);

					if (Math.ceil((TrunkHeight - counter) / (TrunkHeight / 4)) >= 5 && randomiser == 3 + siderandomiser) {
						//Do side branches here too!
						ProcedureTreeLog.executeProcedure((int) xx + 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						ProcedureTreeLog.executeProcedure((int) xx + 2, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx + 2);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchEast.executeProcedure($_dependencies);

						ProcedureTreeLog.executeProcedure((int) xx - 1, (int) yy, (int) zz, world, BlockCedarLog.block, EnumFacing.UP);
						$_dependencies = new java.util.HashMap<>();
						$_dependencies.put("x", xx - 1);
						$_dependencies.put("y", (int) yy);
						$_dependencies.put("z", zz);
						$_dependencies.put("world", world);
						ProcedureWorldGenCedarBranchWest.executeProcedure($_dependencies);
					}

					zz = zz + 1;
					randomiser = randomiser + 1;
					if (Math.random() > 0.6) yy = yy - 1;
					if (yy < (y + 1)) yy = y + 1;
				}
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", xx);
				$_dependencies.put("y", (int) yy);
				$_dependencies.put("z", zz);
				$_dependencies.put("world", world);
				ProcedureWorldGenCedarBranchSouth.executeProcedure($_dependencies);
			}

			//Skip 1 to three rows and then trigger next branch
			if (world.rand.nextInt(2) == 0) {
				counter = counter - Math.round(Math.random());
			} else {
				counter = counter - Math.round(Math.random() * 2);
			}

		}
	}
}