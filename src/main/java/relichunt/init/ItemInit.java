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

    public static final RegistryObject<Item> SHARD_OF_TRUE_SIGHT = ITEMS.register("shard_of_true_sight",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .stacksTo(64))
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
}
