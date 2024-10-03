package bikerboys.hiddendoors.recipe;

import bikerboys.hiddendoors.HiddenDoors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(HiddenDoors.MOD_ID, ConvertingRecipe.Serializer),
                ConvertingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(HiddenDoors.MOD_ID, ConvertingRecipe.Type.ID),
                ConvertingRecipe.Type.INSTANCE);
    }
}
