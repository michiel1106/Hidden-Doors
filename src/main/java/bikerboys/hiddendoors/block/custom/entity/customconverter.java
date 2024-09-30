package bikerboys.hiddendoors.block.custom.entity;

import bikerboys.hiddendoors.gui.ConverterScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class customconverter extends Block {
    private static final Text TITLE = Text.literal("Item Converter");

    public customconverter(Settings settings) {
        super(settings);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
            return new ConverterScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos));
        }, TITLE);
    }
}









//public static final MapCodec<CraftingTableBlock> CODEC = createCodec(CraftingTableBlock::new);

//private static final Text TITLE = Text.translatable("container.converter");

//public MapCodec<? extends CraftingTableBlock> getCodec() {return CODEC;}

//public customconverter(AbstractBlock.Settings settings) {super(settings);}

//@Override
//public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//    if (world.isClient) {
//        return ActionResult.SUCCESS;
//    } else {
//        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
//        return ActionResult.CONSUME;
//    }
//}

//@Override
//public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//    return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
//        return new CraftingScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos));
//    }, TITLE);
//}