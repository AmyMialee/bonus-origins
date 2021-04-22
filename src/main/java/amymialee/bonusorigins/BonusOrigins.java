package amymialee.bonusorigins;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class BonusOrigins implements ModInitializer {
    public static final String MODID = "bonusorigins";
    public static Identifier mask = new Identifier("flame_on");

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(mask, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> {
            server.execute(() -> {
                playerEntity.setFireTicks(100);
            });
        });

    }
}
