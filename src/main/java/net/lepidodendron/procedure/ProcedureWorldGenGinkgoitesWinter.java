package net.lepidodendron.procedure;


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockGinkgoitesLog;
import net.lepidodendron.block.BlockLeafLitter;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenGinkgoitesWinter extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenGinkgoitesWinter(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenGinkgoitesWinter!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenGinkgoitesWinter!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenGinkgoitesWinter!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenGinkgoitesWinter!");
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
		double counterext = 0;
		double counterext2 = 0;
		double randomiser = 0;
		double randomiser2 = 0;
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
			
			//Trunk:
			TrunkHeight = (double) (5);
			if (Math.random() >= 0.5) {
				TrunkHeight = TrunkHeight + Math.round((Math.random() * 100) / 6.66) + Math.round((Math.random() * 100) / 10);
			}
			else {
				//Some are extra-large or even extra-small:
				TrunkHeight = TrunkHeight + Math.round((Math.random() * 100) / 2.5);
			}
			
			if ((TrunkHeight >= 30) && (Math.random()>=0.3)) {
				TrunkHeight = Math.round(TrunkHeight * 0.6);
			}
			if (TrunkHeight < 15) {
				TrunkHeight = 15;
			}
			//The above is standard Ginkgo.... now we want these ones just that bit smaller....
			TrunkHeight = Math.max((double)Math.round(TrunkHeight * 0.7), 9D);

			//TrunkHeight = 40;
			//System.err.println("TrunkHeight = " + (int) TrunkHeight);
			
			counter = 0;
			while (counter <= TrunkHeight) {
				ProcedureTreeLog.executeProcedure(x, (int) (y + counter), z, world, BlockGinkgoitesLog.block, EnumFacing.NORTH);
				counter = counter + 1;
				}

			counter = 3;
			while (counter <= TrunkHeight) {

				//North
				counterext = 1;
				randomiser = Math.random() / 5;
				while (counterext <= (1 + randomiser) * ((((((TrunkHeight - counter) / TrunkHeight) * (TrunkHeight / 6)) * ((counter / TrunkHeight) * (TrunkHeight / 6))) * 0.86))) {
					xx = x;
					yy = y + (int) counter;
					zz = z - (int) counterext;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.2);
					counterext = counterext + 1;
					}

				
				//Add a bent end sometimes, rarer the higher up we go:
				if ((counterext>1) && (Math.random() * (7/counter) >= 0.2)) {
					xx = x;
					yy = y + (int) counter;
					zz = z - (int) counterext + 1;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 1, 0.2);
					
					xx = x;
					yy = yy + 1;
					zz = zz;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x;
					yy = yy;
					zz = zz - 1;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
				}

				if (counterext > 3) {
					randomiser2 = 1;
					while (randomiser2 <= (counterext * 0.7)) {
						//"Bushifier" blocks:
						xx = x + (int) randomiser2;
						yy = y + (int) counter;
						zz = z - (int) randomiser2;
						if (Math.random() > 0.5) {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
						}
						else {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						}
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
						randomiser2 = randomiser2 + 1;
					}
				}
				
				//Actual sub-branches:
				if ((counterext > 5) && (Math.random() >= 0.3)) {
					
					randomiser2 = 0;
					if (Math.random() > 0.5) {
						randomiser2 = 1;
					}

					xx = x + 1;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x + 2;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					if (randomiser2 == 1) {
						xx = x + 3;
						yy = y + (int) counter;
						zz = z - 3 - ((int) counterext - 6);
						ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					}
					
					xx = x + 3 + (int) randomiser2;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);

					xx = x + 3 + (int) randomiser2;
					yy = y + (int) counter;
					zz = z - 4 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
				}


				if (counterext > 3) {
					randomiser2 = 1;
					while (randomiser2 <= (counterext * 0.7)) {
						//"Bushifier" blocks:
						xx = x - (int) randomiser2;
						yy = y + (int) counter;
						zz = z - (int) randomiser2;
						if (Math.random() > 0.5) {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
						}
						else {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						}
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
						randomiser2 = randomiser2 + 1;
					}
				}
				
				//Actual sub-branches:
				if ((counterext > 5) && (Math.random() >= 0.3)) {
					
					randomiser2 = 0;
					if (Math.random() > 0.5) {
						randomiser2 = 1;
					}

					xx = x - 1;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x - 2;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					if (randomiser2 == 1) {
						xx = x - 3;
						yy = y + (int) counter;
						zz = z - 3 - ((int) counterext - 6);
						ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					}
					
					xx = x - 3 - (int) randomiser2;
					yy = y + (int) counter;
					zz = z - 3 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);

					xx = x - 3 - (int) randomiser2;
					yy = y + (int) counter;
					zz = z - 4 - ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
				
				}

				//South
				counterext = 1;
				randomiser = Math.random() / 5;
				while (counterext <= (1 + randomiser) * ((((((TrunkHeight - counter) / TrunkHeight) * (TrunkHeight / 6))	* ((counter / TrunkHeight) * (TrunkHeight / 6))) * 0.86))) {
					xx = x;
					yy = y + (int) counter;
					zz = z + (int) counterext;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.2);

					counterext = counterext + 1;
					}

				
				//Add a bent end sometimes, rarer the higher up we go:
				if ((counterext>1) && (Math.random() * (7/counter) >= 0.2)) {
					xx = x;
					yy = y + (int) counter;
					zz = z + (int) counterext - 1;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 1, 0.2);
					
					xx = x;
					yy = yy + 1;
					zz = zz;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x;
					yy = yy;
					zz = zz + 1;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
				}

				if (counterext > 3) {
					randomiser2 = 1;
					while (randomiser2 <= (counterext * 0.7)) {
						//"Bushifier" blocks:
						xx = x + (int) randomiser2;
						yy = y + (int) counter;
						zz = z + (int) randomiser2;
						if (Math.random() > 0.5) {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
						}
						else {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						}
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
						randomiser2 = randomiser2 + 1;
					}
				}
				
				//Actual sub-branches:
				if ((counterext > 5) && (Math.random() >= 0.3)) {

					randomiser2 = 0;
					if (Math.random() > 0.5) {
						randomiser2 = 1;
					}

					xx = x + 1;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x + 2;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					if (randomiser2 == 1) {
						xx = x + 3;
						yy = y + (int) counter;
						zz = z + 3 + ((int) counterext - 6);
						ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					}
					
					
					xx = x + 3 + (int) randomiser2;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);

					xx = x + 3 + (int) randomiser2;
					yy = y + (int) counter;
					zz = z + 4 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);

//					if (Math.random() > 0.2) {
//						xx = x + 3 + (int) randomiser2;
//						yy = y + (int) counter;
//						zz = z + 5 + ((int) counterext - 6);
//						ProcedureTreeLeaf.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block);
//						}
				}

				if (counterext > 3) {
					randomiser2 = 1;
					while (randomiser2 <= (counterext * 0.7)) {
						//"Bushifier" blocks:
						xx = x - (int) randomiser2;
						yy = y + (int) counter;
						zz = z + (int) randomiser2;
						if (Math.random() > 0.5) {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
						}
						else {
							ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
						}
						//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
						randomiser2 = randomiser2 + 1;
					}
				}
				
				//Actual sub-branches:
				if ((counterext > 5) && (Math.random() >= 0.3)) {
					
					randomiser2 = 0;
					if (Math.random() > 0.5) {
						randomiser2 = 1;
					}

					xx = x - 1;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					xx = x - 2;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
					if (randomiser2 == 1) {
						xx = x - 3;
						yy = y + (int) counter;
						zz = z + 3 + ((int) counterext - 6);
						ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					}
					
					
					xx = x - 3 - (int) randomiser2;
					yy = y + (int) counter;
					zz = z + 3 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);

					xx = x - 3 - (int) randomiser2;
					yy = y + (int) counter;
					zz = z + 4 + ((int) counterext - 6);
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.WEST);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
				}

				//West
				counterext = 1;
				randomiser = Math.random() / 5;
				while (counterext <= (1 + randomiser) * ((((((TrunkHeight - counter) / TrunkHeight) * (TrunkHeight / 6))	* ((counter / TrunkHeight) * (TrunkHeight / 6))) * 0.86))) {
					xx = x - (int) counterext;;
					yy = y + (int) counter;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.2);

					counterext = counterext + 1;
					}
				

				
				//Add a bent end sometimes, rarer the higher up we go:
				if ((counterext>1) && (Math.random() * (7/counter) >= 0.2)) {
					xx = x - (int) counterext + 1;
					yy = y + (int) counter;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 1, 0.2);
					xx = xx;
					yy = yy + 1;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					xx = xx - 1;
					yy = yy;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
				}
					

				//East
				counterext = 1;
				randomiser = Math.random() / 5;
				while (counterext <= (1 + randomiser) * ((((((TrunkHeight - counter) / TrunkHeight) * (TrunkHeight / 6))	* ((counter / TrunkHeight) * (TrunkHeight / 6))) * 0.86))) {
					xx = x +(int) counterext;;
					yy = y + (int) counter;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.2);

					counterext = counterext + 1;
				}

				
				//Add a bent end sometimes, rarer the higher up we go:
				if ((counterext>1) && (Math.random() * (7/counter) >= 0.2)) {
					xx = x + (int) counterext - 1;
					yy = y + (int) counter;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 1, 0.2);
					xx = xx;
					yy = yy + 1;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.SOUTH);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					xx = xx +1;
					yy = yy;
					zz = z;
					ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLog.block, EnumFacing.UP);
					//ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockGinkgoitesLeaves.block, 2, 0.45);
					
				}
				
				counter = counter + 3 + Math.round(Math.random());
			
			}

			//Droped leaves replacement in a circle:
			int leafLitterRadius = 3 + world.rand.nextInt(2);

			int yct = -2;
			while (yct <= 1) {
				int xct = -(leafLitterRadius -1);
				while (xct <= leafLitterRadius) {
					int zct = -leafLitterRadius;
					while (zct <= (leafLitterRadius-1)) {

						if (Math.pow((int) Math.abs(xct), 2) + Math.pow((int) Math.abs(zct), 2) <= Math.pow((int) leafLitterRadius, 2)) {
							if (world.canSeeSky(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct))
									|| world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.SNOW
									|| world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.CRAFTED_SNOW) {
								if ((world.isAirBlock(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct))
										|| world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.SNOW
										|| world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.CRAFTED_SNOW)
										&& ((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getBlock() != BlockLeafLitter.block)
										&& (((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.GROUND)
											|| ((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.GRASS)
											|| ((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.SAND))
								) {
									if (world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.SNOW
											|| world.getBlockState(new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct)).getMaterial() == Material.CRAFTED_SNOW) {
										Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x + xct, (int) y + yct, (int) z + zct), BlockLeafLitter.block.getDefaultState(), 3);
									}
									else {
										Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x + xct, (int) y + yct + 1, (int) z + zct), BlockLeafLitter.block.getDefaultState(), 3);
									}
								}
							}
							else {
								if ((((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.GROUND)
										|| ((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.GRASS)
										|| ((world.getBlockState(new BlockPos((int) x + xct, (int) y + yct, (int) z + zct))).getMaterial() == Material.SAND))) {
									{
										BlockPos _bp = new BlockPos((int) x + xct, (int) y + yct, (int) z + zct);
										Functions.setBlockStateAndCheckForDoublePlant(world,_bp, BlockLeafLitter.block.getDefaultState(), 3);
									}
								}
							}
						}
						zct = zct + 1;
					}
					xct = xct + 1;
				}
				yct = yct + 1;
			}

		}	
	}
}