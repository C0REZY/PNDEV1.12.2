package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPalaeodictyoptera;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerPalaeodictyopteraWing implements LayerRenderer<EntityPrehistoricFloraPalaeodictyoptera>
{
    private final RenderPalaeodictyoptera PalaeodictyopteraRenderer;

//    private static final ModelBase MODEL_DELITZSCHALA = new ModelPalaeodictyopteraSmall();
//    private static final ModelBase MODEL_DUNBARIA = new ModelPalaeodictyopteraMedium();
//    private static final ModelBase MODEL_HOMOIOPTERA = new ModelPalaeodictyopteraLarge();
//    private static final ModelBase MODEL_HOMALONEURA = new ModelPalaeodictyopteraMedium();
//    private static final ModelBase MODEL_LITHOMANTIS = new ModelPalaeodictyopteraMedium();
//    private static final ModelBase MODEL_LYCOCERCUS = new ModelPalaeodictyopteraMedium();
//    private static final ModelBase MODEL_SINODUNBARIA = new ModelPalaeodictyopteraSmall();
//    private static final ModelBase MODEL_STENODICTYA = new ModelPalaeodictyopteraMedium();
//    private static final ModelBase MODEL_MAZOTHAIROS = new ModelPalaeodictyopteraLarge();

    public static final ResourceLocation TEXTURE_DELITZSCHALA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_delitzschala_wing.png");
    public static final ResourceLocation TEXTURE_DUNBARIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_dunbaria_wing.png");
    public static final ResourceLocation TEXTURE_HOMOIOPTERA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_homoioptera_wing.png");
    public static final ResourceLocation TEXTURE_HOMALONEURA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_homaloneura_wing.png");
    public static final ResourceLocation TEXTURE_LITHOMANTIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_lithomantis_wing.png");
    public static final ResourceLocation TEXTURE_LYCOCERCUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_lycocercus_wing.png");
    public static final ResourceLocation TEXTURE_SINODUNBARIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_sinodunbaria_wing.png");
    public static final ResourceLocation TEXTURE_STENODICTYA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_stenodictya_wing.png");
    public static final ResourceLocation TEXTURE_MAZOTHAIROS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeodictyoptera_mazothairos_wing.png");

    public LayerPalaeodictyopteraWing(RenderPalaeodictyoptera PalaeodictyopteraRendererIn)
    {
        this.PalaeodictyopteraRenderer = PalaeodictyopteraRendererIn;
    }

    @Override
    public void doRenderLayer(EntityPrehistoricFloraPalaeodictyoptera entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.isInvisible())
        {
            switch (entitylivingbaseIn.getPNType()) {
                case DELITZSCHALA: default:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_DELITZSCHALA);
                    break;

                case DUNBARIA:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_DUNBARIA);
                    break;

                case HOMALONEURA:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_HOMALONEURA);
                    break;

                case HOMOIOPTERA:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_HOMOIOPTERA);
                    break;

                case LITHOMANTIS:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_LITHOMANTIS);
                    break;

                case LYCOCERCUS:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_LYCOCERCUS);
                    break;

                case SINODUNBARIA:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_SINODUNBARIA);
                    break;

                case STENODICTYA:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_STENODICTYA);
                    break;

                case MAZOTHAIROS:
                    this.PalaeodictyopteraRenderer.bindTexture(TEXTURE_MAZOTHAIROS);
                    break;
            }

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.getEntityModel(entitylivingbaseIn).setModelAttributes(this.PalaeodictyopteraRenderer.getEntityModel(entitylivingbaseIn));
            this.getEntityModel(entitylivingbaseIn).setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
            this.getEntityModel(entitylivingbaseIn).render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
        }
    }

    public ModelBase getEntityModel(EntityPrehistoricFloraPalaeodictyoptera entity) {
        switch (entity.getPNType()) {
            case DELITZSCHALA: default:
                return RenderPalaeodictyoptera.MODEL_DELITZSCHALA;

            case DUNBARIA:
                return RenderPalaeodictyoptera.MODEL_DUNBARIA;

            case HOMALONEURA:
                return RenderPalaeodictyoptera.MODEL_HOMALONEURA;

            case HOMOIOPTERA:
                return RenderPalaeodictyoptera.MODEL_HOMOIOPTERA;

            case LITHOMANTIS:
                return RenderPalaeodictyoptera.MODEL_LITHOMANTIS;

            case LYCOCERCUS:
                return RenderPalaeodictyoptera.MODEL_LYCOCERCUS;

            case SINODUNBARIA:
                return RenderPalaeodictyoptera.MODEL_SINODUNBARIA;

            case STENODICTYA:
                return RenderPalaeodictyoptera.MODEL_STENODICTYA;

            case MAZOTHAIROS:
                return RenderPalaeodictyoptera.MODEL_MAZOTHAIROS;
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
