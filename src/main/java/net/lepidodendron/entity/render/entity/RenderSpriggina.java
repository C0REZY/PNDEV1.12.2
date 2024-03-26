package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPhantaspis;
import net.lepidodendron.entity.EntityPrehistoricFloraSpriggina;
import net.lepidodendron.entity.EntityPrehistoricFloraStensioella;
import net.lepidodendron.entity.model.entity.ModelSpriggina;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSpriggina extends RenderLiving<EntityPrehistoricFloraSpriggina> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/spriggina.png");
    public static float getScaler() {
        return 0.35F;
    }

    public RenderSpriggina(RenderManager mgr) {
        super(mgr, new ModelSpriggina(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraSpriggina entity) {
        return RenderSpriggina.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraSpriggina entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraSpriggina entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}