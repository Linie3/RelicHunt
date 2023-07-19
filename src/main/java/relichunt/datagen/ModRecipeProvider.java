package relichunt.datagen;


import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import relichunt.init.ItemInit;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.ORACLE_OF_TRUE_SIGHT.get())
                .pattern("CSC")
                .pattern("SDS")
                .pattern("CSC")
                .define('C', Items.COPPER_INGOT)
                .define('S', ItemInit.ESSENCE_OF_SIGHT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_vision", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ItemInit.ESSENCE_OF_SIGHT.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.SHIELD_OF_RESILIENCE.get())
                .pattern("ITI")
                .pattern("TDT")
                .pattern("ITI")
                .define('I', Items.IRON_INGOT)
                .define('T', ItemInit.ESSENCE_OF_TOUGHNESS.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_vision", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ItemInit.ESSENCE_OF_TOUGHNESS.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.NECKLACE_OF_SILENCE.get())
                .pattern("WEW")
                .pattern("ESE")
                .pattern("WEW")
                .define('W', ItemTags.WOOL)
                .define('E', Items.ECHO_SHARD)
                .define('S', Items.SCULK_SENSOR)
                .unlockedBy("has_vision", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.ECHO_SHARD).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.HEART_OF_VIVIDNESS.get())
                .pattern("GVG")
                .pattern("VAV")
                .pattern("GVG")
                .define('G', Items.GOLD_INGOT)
                .define('V', ItemInit.ESSENCE_OF_VITALITY.get())
                .define('A', Items.APPLE)
                .unlockedBy("has_vision", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.GOLD_INGOT).build()))
                .save(pWriter);
    }
}