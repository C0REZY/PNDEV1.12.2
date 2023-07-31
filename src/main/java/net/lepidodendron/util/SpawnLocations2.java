package net.lepidodendron.util;

import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;

public class SpawnLocations2 implements IComponentProcessor {

    String mob;

    @Override
    public void setup(IVariableProvider<String> iVariableProvider) {
        this.mob = iVariableProvider.get("mob");
    }

    @Override
    public String process(String s) {
        String biomeList = SpawnLocations1.getBiomeList(this.mob, 15, 32);
        if (biomeList != null) {
            if (!biomeList.equalsIgnoreCase("")) {
                return biomeList;
            }
        }
        return "";
    }
}
