package bikerboys.hiddendoors.recipe;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ConvertingRecipe implements Recipe<SingleStackRecipeInput> {
    private static Ingredient ingredient;
    private static ItemStack result;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    private static String group;

    public ConvertingRecipe(RecipeType<?> type, RecipeSerializer<?> serializer, String group, Ingredient ingredient, ItemStack result) {
        this.type = type;
        this.serializer = serializer;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
    }

    public RecipeType<?> getType() {
        return this.type;
    }

    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    public String getGroup() {
        return this.group;
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.ingredient);
        return defaultedList;
    }

    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        return this.ingredient.test(singleStackRecipeInput.item());
    }

    public ItemStack craft(SingleStackRecipeInput singleStackRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        return this.result.copy();
    }

    public interface RecipeFactory<T extends ConvertingRecipe> {
        T create(String group, Ingredient ingredient, ItemStack result);

        Object create(Object o, Object o1, Object o2);
    }

    public static class Serializer<T extends ConvertingRecipe> implements RecipeSerializer<T> {
        final ConvertingRecipe.RecipeFactory<T> recipeFactory;
        private final MapCodec<T> codec;
        private final PacketCodec<RegistryByteBuf, T> packetCodec;

        protected Serializer(ConvertingRecipe.RecipeFactory<T> recipeFactory) {
            this.recipeFactory = recipeFactory;
            this.codec = RecordCodecBuilder.mapCodec((instance) -> {
                Products.P3 var10000 = instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter((recipe) -> {
                    return ConvertingRecipe.group;
                }), Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter((recipe) -> {
                    return ConvertingRecipe.ingredient;
                }), ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter((recipe) -> {
                    return ConvertingRecipe.result;
                }));
                Objects.requireNonNull(recipeFactory);
                return var10000.apply(instance, recipeFactory::create);
            });
            PacketCodec var10001 = PacketCodecs.STRING;
            Function var10002 = (recipe) -> {
                return ConvertingRecipe.group;
            };
            PacketCodec var10003 = Ingredient.PACKET_CODEC;
            Function var10004 = (recipe) -> {
                return ConvertingRecipe.ingredient;
            };
            PacketCodec var10005 = ItemStack.PACKET_CODEC;
            Function var10006 = (recipe) -> {
                return ConvertingRecipe.result;
            };
            Objects.requireNonNull(recipeFactory);
            this.packetCodec = PacketCodec.tuple(var10001, var10002, var10003, var10004, var10005, var10006, recipeFactory::create);
        }

        public MapCodec<T> codec() {
            return this.codec;
        }

        public PacketCodec<RegistryByteBuf, T> packetCodec() {
            return this.packetCodec;
        }
    }
}