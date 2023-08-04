package net.lepidodendron.util.patchouli;

import net.lepidodendron.entity.render.tile.RenderDisplayWallMount;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;

import java.lang.reflect.Method;

public class Diet implements IComponentProcessor {

    String mob;

    @Override
    public void setup(IVariableProvider<String> iVariableProvider) {
        this.mob = iVariableProvider.get("mob");
    }

    @Override
    public String process(String s) {
        EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(this.mob));
        Class clazz = ee.getEntityClass();
        Method renderMethod = RenderDisplayWallMount.testAndGetMethod(clazz, "getFoodOreDicts", null);
        String[] string = new String[]{};
        String result = "";
        if (renderMethod != null) {
            try {
                EntityLiving entity = (EntityLiving) ee.newInstance(null);
                string = (String[]) renderMethod.invoke(entity, null);
                entity.setDead();
            }
            catch (Exception e) {
            }
        } else {
        }
        if (string.length > 0) {
            for (String element : string) {
                result = result + "$(br)" + I18n.translateToLocal("oredict." + element + ".name").trim();
            }
            return result;
        }
        return "Unknown";
    }

}
