package relichunt.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import relichunt.RelicHunt;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RelicHunt.MODID);


    public static final RegistryObject<CreativeModeTab> RELIC_HUNT_TAB = CREATIVE_MODE_TABS.register("relic_hunt_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.ORACLE_OF_TRUE_VISION.get()))
                    .title(Component.translatable("creativetab.relic_hunt_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ItemInit.ESSENCE_OF_SIGHT.get());
                        output.accept(ItemInit.ESSENCE_OF_VITALITY.get());
                        output.accept(ItemInit.ESSENCE_OF_TOUGHNESS.get());
                        output.accept(ItemInit.ESSENCE_OF_EXPERIENCE.get());
                        output.accept(ItemInit.ORACLE_OF_TRUE_VISION.get());
                        output.accept(ItemInit.HEART_OF_VIVIDNESS.get());
                        output.accept(ItemInit.SHIELD_OF_RESILIENCE.get());
                        output.accept(ItemInit.NECKLACE_OF_SILENCE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
