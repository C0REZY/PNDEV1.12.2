package net.lepidodendron.procedure;


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockCycadopterisLog;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenCycadopteris extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenCycadopteris(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenCycadopteris!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenCycadopteris!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenCycadopteris!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenCycadopteris!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double TreeHeight = 0;
		double counter = 0;
		
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
			
			//Tree height: 1-6 blocks
			TreeHeight = 1 + Math.round(Math.random() * 4);

			Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) x, (int) y, (int) z), BlockCycadopterisLog.block.getDefaultState(), 3);

			Object2ObjectOpenHashMap<String, Object> $_dependencies = new Object2ObjectOpenHashMap<>();
			$_dependencies.put("x", x);
			$_dependencies.put("y", y + 1);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			$_dependencies.put("TreeHeight", TreeHeight);
			$_dependencies.put("counter", counter);
			ProcedureWorldGenCycadopterisBranch.executeProcedure($_dependencies);
				
		}
	}
}