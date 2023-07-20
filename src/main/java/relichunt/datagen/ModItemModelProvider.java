package relichunt.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import relichunt.RelicHunt;
import relichunt.init.ItemInit;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RelicHunt.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemInit.ESSENCE_OF_SIGHT);
        simpleItem(ItemInit.ESSENCE_OF_VITALITY);
        simpleItem(ItemInit.ESSENCE_OF_TOUGHNESS);
        simpleItem(ItemInit.ESSENCE_OF_EXPERIENCE);
        simpleItem(ItemInit.SHIELD_OF_RESILIENCE);
        simpleItem(ItemInit.ORACLE_OF_TRUE_VISION);
        simpleItem(ItemInit.NECKLACE_OF_SILENCE);
        simpleItem(ItemInit.HEART_OF_VIVIDNESS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RelicHunt.MODID,"item/" + item.getId().getPath()));
    }
}