package amymialee.bonusorigins.client;

import amymialee.bonusorigins.BonusOrigins;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class BonusOriginsClient implements ClientModInitializer {
    private static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.source_cubed.bind",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.source_cubed.bind"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                ClientPlayNetworking.send(BonusOrigins.mask, PacketByteBufs.create());
            }
        });
    }
}
