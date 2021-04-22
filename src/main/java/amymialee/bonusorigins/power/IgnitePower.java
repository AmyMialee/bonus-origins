package amymialee.bonusorigins.power;

import io.github.apace100.origins.power.ActiveCooldownPower;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.util.HudRender;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class IgnitePower extends ActiveCooldownPower {
    private final int length;
    private final SoundEvent soundEvent;

    public IgnitePower(PowerType<?> type, PlayerEntity player, int cooldownDuration, HudRender hudRender, int length, SoundEvent soundEvent) {
        super(type, player, cooldownDuration, hudRender, null);
        this.length = length;
        this.soundEvent = soundEvent;
    }

    @Override
    public void onUse() {
        if(canUse()) {
            ignite();
            use();
        }
    }

    private void ignite() {
        if(soundEvent != null) {
            player.world.playSound(null, player.getX(), player.getY(), player.getZ(), soundEvent, SoundCategory.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        if (!player.world.isClient) {
            player.setFireTicks(length);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, length));
        }
    }
}
