package bikerboys.hiddendoors.block;

import bikerboys.hiddendoors.HiddenDoors;
import bikerboys.hiddendoors.block.custom.entity.customconverter;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
public class ModBlocks {

    public static void initialize() {}

    public static final Block ITEM_CONVERTER = register(
            new customconverter(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS)),
            "item_converter",true);

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = Identifier.of(HiddenDoors.MOD_ID, name);
        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }
}