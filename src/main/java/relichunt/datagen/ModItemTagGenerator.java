package relichunt.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import relichunt.RelicHunt;
import relichunt.init.ItemInit;
import relichunt.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, RelicHunt.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Add Item Tags here
        this.tag(ModTags.Items.RELICHUNT_ESSENCES)
                .add(Items.ECHO_SHARD).add(ItemInit.VISION_SHARD.get()).add(ItemInit.EXPERIENCE_SHARD.get()).add(ItemInit.ESSENCE_OF_TOUGHNESS.get());

        this.tag(ModTags.Items.RELICHUNT_RELICS)
                .add(ItemInit.HEART_OF_RESILIENCE.get()).add(ItemInit.NECKLACE_OF_SILENCE.get()).add(ItemInit.ORACLE_OF_TRUE_SIGHT.get());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}