package bikerboys.hiddendoors.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class ConvertingRecipe extends CuttingRecipe {

    public ConvertingRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(RecipeType.STONECUTTING, RecipeSerializer.STONECUTTING, group, ingredient, result);
    }

    public boolean matches(SingleStackRecipeInput singleStackRecipeInput, World world) {
        return this.ingredient.test(singleStackRecipeInput.item());
    }

    public ItemStack createIcon() {
        return new ItemStack(Blocks.STONECUTTER);
    }
}