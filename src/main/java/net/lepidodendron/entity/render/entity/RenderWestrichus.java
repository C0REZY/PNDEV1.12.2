package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraWestrichus;
import net.lepidodendron.entity.model.entity.ModelWestrichus;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWestrichus extends RenderLivingBaseWithBook<EntityPrehistoricFloraWestrichus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/westrichus.png");

    public RenderWestrichus(RenderManager mgr) {
        super(mgr, new ModelWestrichus(), 0.0f);
    }

    public static float getScaler() {return 0.32F; }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraWestrichus entity) {
        return RenderWestrichus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraWestrichus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraWestrichus entity, float f) {
        float scale = this.getScaler();
        GlStateManager.scale(scale, scale, scale);
    }
}