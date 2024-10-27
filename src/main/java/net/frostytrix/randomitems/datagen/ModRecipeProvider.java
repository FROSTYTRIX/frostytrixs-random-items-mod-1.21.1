package net.frostytrix.randomitems.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.frostytrix.randomitems.item.ModItemGroups;
import net.frostytrix.randomitems.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        shovelRecipe(exporter, ModItems.SHARP_STONE_SHOVEL, ModItems.SHARP_STONE_SHOVEL_HEAD);
        shovelFlailRecipe(exporter, ModItems.STONE_SHOVEL_FLAIL, ModItems.SHARP_STONE_SHOVEL_HEAD);
    }
    public static void shovelRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input){
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output, 1)
                .input('#', Items.STICK)
                .input('X', input)
                .pattern(" X ")
                .pattern(" # ")
                .pattern(" # ")
                .group("shovel")
                .offerTo(exporter);
    }
    public static void shovelFlailRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input){
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output, 1)
                .input('#', Items.STICK)
                .input('@', Items.CHAIN)
                .input('X', input)
                .pattern(" X ")
                .pattern(" @ ")
                .pattern(" # ")
                .group("shovel_flail")
                .offerTo(exporter);
    }
}
