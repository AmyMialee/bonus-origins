package amymialee.bonusorigins;

import amymialee.bonusorigins.power.registry.PowerFactories;
import net.fabricmc.api.ModInitializer;

public class BonusOrigins implements ModInitializer {
    public static final String MODID = "bonusorigins";

    @Override
    public void onInitialize() {
        PowerFactories.register();
    }
}
