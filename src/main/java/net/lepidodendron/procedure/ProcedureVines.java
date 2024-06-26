package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockSphenophyllales;
import net.lepidodendron.util.Functions;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureVines extends ElementsLepidodendronMod.ModElement {
	public ProcedureVines(ElementsLepidodendronMod instance) {
		super(instance, 200);
	}
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
	
	public static void executeProcedure(int xx, int yy, int zz, World world) {

		int vinecounter = 0;
		
		//North:
		if ((Math.random() > 0.7) && (world.isAirBlock(new BlockPos((int) xx, (int) yy, (int) zz - 1)))) {
			vinecounter = 0;
			while ((world.isAirBlock(new BlockPos((int) xx, (int) (yy - vinecounter), (int) zz - 1))) && ((yy - vinecounter) > 0)) {
				try {
					Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) xx, (int) (yy - vinecounter), (int) zz - 1), BlockSphenophyllales.block.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(false)), 2);
				}
				catch (Exception e) {}
				vinecounter = vinecounter + 1;
			}
		}
		//South:
		if ((Math.random() > 0.7) && (world.isAirBlock(new BlockPos((int) xx, (int) yy, (int) zz + 1)))) {
			vinecounter = 0;
			while ((world.isAirBlock(new BlockPos((int) xx, (int) (yy - vinecounter), (int) zz + 1))) && ((yy - vinecounter) > 0)) {
				try {
				Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) xx, (int) (yy - vinecounter), (int) zz + 1), BlockSphenophyllales.block.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(true)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)), 2);
			}
			catch (Exception e) {}
				vinecounter = vinecounter + 1;
			}
		}
		//East:
		if ((Math.random() > 0.7) && (world.isAirBlock(new BlockPos((int) xx + 1, (int) yy, (int) zz)))) {
			vinecounter = 0;
			while ((world.isAirBlock(new BlockPos((int) xx + 1, (int) (yy - vinecounter), (int) zz))) && ((yy - vinecounter) > 0)) {
				try {
				Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) xx + 1, (int) (yy - vinecounter), (int) zz), BlockSphenophyllales.block.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(true)), 2);
			}
			catch (Exception e) {}
				vinecounter = vinecounter + 1;
			}
		}
		//West:
		if ((Math.random() > 0.7) && (world.isAirBlock(new BlockPos((int) xx - 1, (int) yy, (int) zz)))) {
			vinecounter = 0;
			while ((world.isAirBlock(new BlockPos((int) xx - 1, (int) (yy - vinecounter), (int) zz))) && ((yy - vinecounter) > 0)) {
				try {
				Functions.setBlockStateAndCheckForDoublePlant(world,new BlockPos((int) xx - 1, (int) (yy - vinecounter), (int) zz), BlockSphenophyllales.block.getDefaultState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)), 2);
			}
			catch (Exception e) {}
				vinecounter = vinecounter + 1;
			}
		}
		
	}
	
}	
