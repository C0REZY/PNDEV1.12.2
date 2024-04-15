package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCaihong;
import net.lepidodendron.entity.model.entity.ModelCaihong;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCaihong extends RenderLiving<EntityPrehistoricFloraCaihong> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/caihong.png");

    public static float getScaler() {
        return 0.185f;
    }

    public RenderCaihong(RenderManager mgr) {
        super(mgr, new ModelCaihong(), 0.0f);
    }

//    @Override
//    public void doRender(EntityPrehistoricFloraCaihong entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        try {
//            StackTraceElement[] elements = new Throwable().getStackTrace();
//            String  callerClass = elements[4].getClassName();
//            if (callerClass.equalsIgnoreCase("vazkii.patchouli.client.book.page.PageEntity")) {
//                GlStateManager.pushMatrix();
//                GlStateManager.disableCull();
//                GlStateManager.enableAlpha();
//                boolean flag = this.setDoRenderBrightness(entity, partialTicks);
//                if (!this.bindEntityTexture(entity)) {
//                    return;
//                }
//                RenderDisplays.modelCaihongBook.renderStaticBook(this.prepareScale(entity, partialTicks));
//                if (flag) {
//                    this.unsetBrightness();
//                }
//                GlStateManager.depthMask(true);
//                GlStateManager.disableRescaleNormal();
//                GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//                GlStateManager.enableTexture2D();
//                GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
//                GlStateManager.enableCull();
//                GlStateManager.popMatrix();
//            } else {
//                super.doRender(entity, x, y, z, entityYaw, partialTicks);
//            }
//        }
//        catch (Exception e ) {
//            //Do nothing
//        }
//    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCaihong entity) {
        return RenderCaihong.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCaihong entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);

        switch (entityLiving.getClimbFacing()) {
            case DOWN:
            default:
                break;

            case EAST: case SOUTH: case WEST: case NORTH:
                GlStateManager.translate(0.0F, 0.05F, -0.1775F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;

            case UP:
                GlStateManager.translate(0.0F, 0.5F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }

    }


    @Override
    protected void preRenderCallback(EntityPrehistoricFloraCaihong entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.15F;
    }

}