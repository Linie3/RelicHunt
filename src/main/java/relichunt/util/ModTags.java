package relichunt.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import relichunt.RelicHunt;

public class ModTags {
    public static class Items {

        public static final TagKey<Item> RELICHUNT_ESSENCES = tag("relichunt_essences");
        public static final TagKey<Item> RELICHUNT_RELICS = tag("relichunt_relics");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RelicHunt.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RelicHunt.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
