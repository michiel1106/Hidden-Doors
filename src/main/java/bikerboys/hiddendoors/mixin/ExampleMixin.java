package bikerboys.hiddendoors.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.StonecutterScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(StonecutterScreenHandler.class)
public class ExampleMixin {

//dont use this but might as well leave it in

	@Overwrite
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}