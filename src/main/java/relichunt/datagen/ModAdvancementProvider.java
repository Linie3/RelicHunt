package relichunt.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import relichunt.RelicHunt;
import relichunt.init.ItemInit;
import relichunt.util.ModTags;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ItemInit.VISION_SHARD.get()),
                        Component.literal("The essence of Everything"), Component.literal("What will be uncovered about the Ancient powers?"),
                        new ResourceLocation(RelicHunt.MODID, "textures/block/extractor.png"), FrameType.TASK,
                        true, true, false))
                .addCriterion("has_essence", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.RELICHUNT_ESSENCES).build()))
                .save(saver, new ResourceLocation(RelicHunt.MODID, "relichunt"), existingFileHelper);
    }
}
