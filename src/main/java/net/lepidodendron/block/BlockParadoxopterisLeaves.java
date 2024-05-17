
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.base.IAdvancementGranter;
import net.lepidodendron.block.base.SeedSporeLeavesBase;
import net.lepidodendron.util.CustomTrigger;
import net.lepidodendron.util.ModTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockParadoxopterisLeaves extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:paradoxopteris_leaves_worldgen")
	public static final Block block = null;
	public BlockParadoxopterisLeaves(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.paradoxopteris_leaves);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("paradoxopteris_leaves_worldgen"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
		//		new ModelResourceLocation("lepidodendron:paradoxopteris_leaves_worldgen", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY).build());
	}
	
	public static class BlockCustom extends SeedSporeLeavesBase implements IAdvancementGranter {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public BlockCustom() {
			//super(Material.LEAVES);
			setTranslationKey("pf_paradoxopteris_leaves_worldgen");
			setSoundType(SoundType.PLANT);
			setHardness(0.2F);
			setResistance(0.2F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(null);
			this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true).withProperty(FACING, EnumFacing.UP));
		}

		@Nullable
		@Override
		public CustomTrigger getModTrigger() {
			return ModTriggers.CLICK_PARADOXOPTERIS;
		}

		@Override
		public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
			return true;
		}

		@Nullable
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
		{
			return NULL_AABB;
		}

		@Override
		public BlockPlanks.EnumType getWoodType(int meta) {
			return null;
		}
		
		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockParadoxopterisLeavesPlaceable.block, (int) (1)));
		}

		@SideOnly(Side.CLIENT)
		@Override
    	public BlockRenderLayer getRenderLayer()
		{
			return BlockRenderLayer.CUTOUT;
		}
		
		@Override
		public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
			return layer == BlockRenderLayer.CUTOUT_MIPPED;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
		
		public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{CHECK_DECAY, DECAYABLE, FACING});
		}

		@Override
		public IBlockState withRotation(IBlockState state, Rotation rot) {
			return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing) state.getValue(FACING)).getIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
				EntityLivingBase placer) {
				return this.getDefaultState().withProperty(FACING, facing);
		}

		@Override
		public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 100;
		}

		@Override
		public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 60;
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.FOLIAGE;
		}
		
		@Override
		protected int getSaplingDropChance(IBlockState state) {
			return 10;
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			if (LepidodendronConfig.doPropagation) {
				// Drop air and use the spores method instead:
				return new ItemStack(Blocks.AIR, (int) (1)).getItem();
			}
			else {
				return Item.getItemFromBlock(BlockParadoxopterisSapling.block);
			}
		}

		@Override
		public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        	return false;
    	}
		
		@Override
		protected boolean canSilkHarvest()
	    {
	        return true;
	    }

	    @Override
        public ItemStack getSilkTouchDrop(IBlockState state)  {
            return new ItemStack(BlockParadoxopterisLeavesPlaceable.block, (int) (1));
        }

	    @Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockParadoxopterisLeavesPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
		{
			if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue())
				{
				if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

				//Test the orientation of this block and then check if it is still connected:
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.NORTH) {
					Block block = worldIn.getBlockState(pos.south()).getBlock();
					boolean isShoot = false;
					if (block == BlockParadoxopterisLeaves.block
						|| block == BlockParadoxopterisLeavesPlaceable.block) {
						isShoot = worldIn.getBlockState(pos.south()).getValue(FACING) == EnumFacing.UP;
					}
					if ((!isShoot) && block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
				
				
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.SOUTH) {
					Block block = worldIn.getBlockState(pos.north()).getBlock();
					boolean isShoot = false;
					if (block == BlockParadoxopterisLeaves.block
							|| block == BlockParadoxopterisLeavesPlaceable.block) {
						isShoot = worldIn.getBlockState(pos.north()).getValue(FACING) == EnumFacing.UP;
					}
					if ((!isShoot) && block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
			
				
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.EAST) {
					Block block = worldIn.getBlockState(pos.west()).getBlock();
					boolean isShoot = false;
					if (block == BlockParadoxopterisLeaves.block
							|| block == BlockParadoxopterisLeavesPlaceable.block) {
						isShoot = worldIn.getBlockState(pos.west()).getValue(FACING) == EnumFacing.UP;
					}
					if ((!isShoot) && block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
				
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.WEST) {
					Block block = worldIn.getBlockState(pos.east()).getBlock();
					boolean isShoot = false;
					if (block == BlockParadoxopterisLeaves.block
							|| block == BlockParadoxopterisLeavesPlaceable.block) {
						isShoot = worldIn.getBlockState(pos.east()).getValue(FACING) == EnumFacing.UP;
					}
					if ((!isShoot) && block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
				
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.UP) {
					Block block = worldIn.getBlockState(pos.down()).getBlock();
					if (block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
				
				if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.DOWN) {
					Block block = worldIn.getBlockState(pos.up()).getBlock();
					if (block != BlockParadoxopterisLog.block)
						{
							worldIn.setBlockToAir(pos);
							if ((Math.random() >= 0.9) && (!LepidodendronConfig.doPropagation)) {
									//Spawn another sapling:
									if (!worldIn.isRemote) {
										EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockParadoxopterisSapling.block, (int) (1)));
										entityToSpawn.setPickupDelay(10);
										worldIn.spawnEntity(entityToSpawn);
									}
								}
						}
					}
			}
			
		}

		
		@Override
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }

		@Override
	    public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	    {
	        return true;
	    }

		@Deprecated
		public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos)
		{
			long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
			return new Vec3d(0.0, ((double)((float)(i >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D, 0.0);

		}

		@Override
		public Block planted() {
			return BlockParadoxopterisSapling.block;
		}

		@Override
		public int offsetY() {
			return 1;
		}
	}
}