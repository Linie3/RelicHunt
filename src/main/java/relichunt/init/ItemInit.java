package relichunt.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import relichunt.RelicHunt;
import relichunt.entity.CrystalGuardian;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RelicHunt.MODID);

    public static final RegistryObject<Item> ESSENCE_OF_SIGHT = ITEMS.register("essence_of_sight",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE))
    );

    public static final RegistryObject<Item> ORACLE_OF_TRUE_SIGHT = ITEMS.register("oracle_of_true_sight",
            () -> new Item(new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1))
    );

    public static final RegistryObject<Item> NECKLACE_OF_SILENCE = ITEMS.register("necklace_of_silence",
            () -> new Item(new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1))
    );

    public static final RegistryObject<Item> SHIELD_OF_RESILIENCE = ITEMS.register("shield_of_resilience",
            () -> new Item(new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1))
    );

    public static final RegistryObject<Item> HEART_OF_VIVIDNESS = ITEMS.register("heart_of_vividness",
            () -> new Item(new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1))
    );

    public static final RegistryObject<Item> ESSENCE_OF_EXPERIENCE = ITEMS.register("essence_of_experience",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE))
    );

    public static final RegistryObject<Item> ESSENCE_OF_TOUGHNESS = ITEMS.register("essence_of_toughness",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE))
    );

    public static final RegistryObject<Item> ESSENCE_OF_VITALITY = ITEMS.register("essence_of_vitality",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE))
    );
}

