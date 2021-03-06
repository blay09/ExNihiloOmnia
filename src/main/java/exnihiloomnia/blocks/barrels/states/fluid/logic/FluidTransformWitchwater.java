package exnihiloomnia.blocks.barrels.states.fluid.logic;

import exnihiloomnia.items.ENOItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.FluidRegistry;
import exnihiloomnia.blocks.barrels.architecture.BarrelLogic;
import exnihiloomnia.blocks.barrels.states.BarrelStates;
import exnihiloomnia.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidTransformWitchwater extends BarrelLogic{
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {

        return item.getItem() == ENOItems.SPORES
                && barrel.getFluid().getFluid() == FluidRegistry.WATER
                && barrel.getFluidAmount() == barrel.getCapacity();

    }

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		if (item.getItem() == ENOItems.SPORES
		    && barrel.getFluid().getFluid() == FluidRegistry.WATER
		    && barrel.getFluidAmount() == barrel.getCapacity())
		{
			barrel.setState(BarrelStates.TRANSFORM_WITCHWATER);
			consumeItem(player, item);
			
			barrel.getWorld().playSound(null, barrel.getPos(), SoundEvents.ENTITY_BOBBER_SPLASH, SoundCategory.BLOCKS, 0.12f, 4.5f);
			
			return true;
		}
		
		return false;
	}
}
