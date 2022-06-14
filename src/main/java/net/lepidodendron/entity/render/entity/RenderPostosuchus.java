package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPostosuchus;
import net.lepidodendron.entity.model.entity.ModelPostosuchus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPostosuchus extends RenderLiving<EntityPrehistoricFloraPostosuchus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/postosuchus.png");

    public RenderPostosuchus(RenderManager mgr) {
        super(mgr, new ModelPostosuchus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPostosuchus entity) {
        return RenderPostosuchus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPostosuchus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}































