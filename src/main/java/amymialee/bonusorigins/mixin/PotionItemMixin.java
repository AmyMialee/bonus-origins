package amymialee.bonusorigins.mixin;

import amymialee.bonusorigins.power.registry.PowerFactories;
import io.github.apace100.origins.component.OriginComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    public boolean addEffectRedirect(LivingEntity livingEntity, StatusEffectInstance effect) {
        if (PowerFactories.POTION_DRINK.isActive(livingEntity)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, effect.getDuration() * 8,
                    0, true, false, false));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 40,
                    0, true, false, false));
            return livingEntity.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), effect.getDuration() * 8,
                    ((effect.getAmplifier() + 1) * 2) - 1, effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon()));

        } else {
            return livingEntity.addStatusEffect(effect);
        }
    }
}