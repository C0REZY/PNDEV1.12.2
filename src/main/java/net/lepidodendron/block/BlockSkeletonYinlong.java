
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.base.BlockSkeletonBase;
import net.lepidodendron.entity.EntityPrehistoricFloraDollocaris;
import net.lepidodendron.entity.EntityPrehistoricFloraYinlong;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockSkeletonYinlong extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:skeleton_yinlong")
	public static final Block block = null;
	public BlockSkeletonYinlong(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.skeleton_yinlong);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("skeleton_yinlong"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()).setMaxStackSize(64));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileEntitySkeletonYinlong.class, "lepidodendron:tileentityskeleton_yinlong");
		OreDictionary.registerOre("bone", BlockSkeletonYinlong.block);
		OreDictionary.registerOre("mobdnaPNlepidodendron:prehistoric_flora_yinlong", BlockSkeletonYinlong.block);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:entities/skeleton_yinlong", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockSkeletonBase.FACING).build());
	}

	public static class BlockCustom extends BlockSkeletonBase {

		public BlockCustom() {
			setTranslationKey("pf_skeleton_yinlong");
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new TileEntitySkeletonYinlong();
		}

		public TileEntitySkeletonYinlong createNewTileEntity(World worldIn, int meta) {
			return new TileEntitySkeletonYinlong();
		}

		@Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);

			int newStage = getStage(worldIn, pos) + 1;
			if (playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(this)) {
				if (!worldIn.isRemote) {
					TileEntity tileEntity = worldIn.getTileEntity(pos);
					IBlockState blockstate = worldIn.getBlockState(pos);
					if (tileEntity != null) {
						if (tileEntity instanceof TileEntitySkeletonYinlong) {
							if (((TileEntitySkeletonYinlong) tileEntity).getStages() >= newStage) {
								tileEntity.getTileData().setInteger("stage", newStage);
								if (!playerIn.isCreative()) {
									playerIn.getHeldItemMainhand().shrink(1);
								}
								worldIn.notifyBlockUpdate(pos, blockstate, blockstate, 3);
								SoundEvent soundevent = SoundEvents.ENTITY_SKELETON_STEP;
								((WorldServer) playerIn.getEntityWorld()).playSound(null, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
								return true;
							}
						}
					}
				}
				return true;
			}

			int newRotation = getRotation(worldIn, pos) + 15;
			if (newRotation >= 360) {newRotation = 0;}

			if (!worldIn.isRemote) {
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				IBlockState blockstate = worldIn.getBlockState(pos);
				if (tileEntity != null) {
					tileEntity.getTileData().setInteger("rotation", newRotation);
				}
				worldIn.notifyBlockUpdate(pos, blockstate, blockstate, 3);
				SoundEvent soundevent = SoundEvents.ENTITY_SKELETON_HURT;
				((WorldServer) playerIn.getEntityWorld()).playSound(null, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}

			return true;
		}

	}

	public static class TileEntitySkeletonYinlong extends TileEntity {

		int stages = 5;

		public int getStages() {
			return this.stages;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public double getMaxRenderDistanceSquared() {
			double h = EntityPrehistoricFloraYinlong.getRenderDistanceWeight();
			double hh = EntityPrehistoricFloraDollocaris.getRenderDistanceWeight();
			return Math.pow(EntityPrehistoricFloraYinlong.getRenderDistanceWeight() * 64, 2);
		}

		@Override
		public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
		{
			return (oldState.getBlock() != newSate.getBlock());
		}

		@Override
		public SPacketUpdateTileEntity getUpdatePacket() {
			return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}

		@Override
		public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
			this.readFromNBT(pkt.getNbtCompound());
		}

		@Override
		public void handleUpdateTag(NBTTagCompound tag) {
			this.readFromNBT(tag);
		}

	}
}