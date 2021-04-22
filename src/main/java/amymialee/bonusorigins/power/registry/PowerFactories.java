package amymialee.bonusorigins.power.registry;

import amymialee.bonusorigins.BonusOrigins;
import amymialee.bonusorigins.power.IgnitePower;
import io.github.apace100.origins.power.Active;
import io.github.apace100.origins.power.Power;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.power.PowerTypeReference;
import io.github.apace100.origins.power.factory.PowerFactory;
import io.github.apace100.origins.registry.ModRegistries;
import io.github.apace100.origins.util.HudRender;
import io.github.apace100.origins.util.SerializableData;
import io.github.apace100.origins.util.SerializableDataType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PowerFactories {
    public static final PowerType<Power> POTION_DRINK = new PowerTypeReference(new Identifier(BonusOrigins.MODID, "potion_drink"));
    public static final PowerType<Power> POTION_THROW = new PowerTypeReference(new Identifier(BonusOrigins.MODID, "potion_throw"));

    public static void register() {
        register(new PowerFactory<>(new Identifier(BonusOrigins.MODID, "ignite"),
                new SerializableData()
                        .add("cooldown", SerializableDataType.INT)
                        .add("length", SerializableDataType.INT, 100)
                        .add("sound", SerializableDataType.SOUND_EVENT, null)
                        .add("hud_render", SerializableDataType.HUD_RENDER)
                        .add("key", SerializableDataType.BACKWARDS_COMPATIBLE_KEY, new Active.Key()),
                data ->
                        (type, player) -> {
                            IgnitePower power = new IgnitePower(type, player,
                                    data.getInt("cooldown"),
                                    (HudRender)data.get("hud_render"),
                                    data.getInt("length"),
                                    (SoundEvent)data.get("sound"));
                            power.setKey((Active.Key)data.get("key"));
                            return power;
                        })
                .allowCondition());
    }

    private static void register(PowerFactory serializer) {
        Registry.register(ModRegistries.POWER_FACTORY, serializer.getSerializerId(), serializer);
    }
}
