package exnihiloomnia.blocks.barrels.architecture;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihiloomnia.blocks.barrels.states.BarrelStates;
import exnihiloomnia.blocks.barrels.tileentity.TileEntityBarrel;

public abstract class BarrelState
{
	private static String[] EMPTY_STRING_ARRAY = new String[] {};
	private ArrayList<BarrelLogic> triggers = new ArrayList<BarrelLogic>();
	
	public abstract String getUniqueIdentifier();

	public void activate(TileEntityBarrel barrel)
	{
		boolean triggered = false;

		for (BarrelLogic entry : triggers) 
		{
			triggered = entry.onActivate(barrel);

			if (triggered)
				break;
		}
	}
	
	public void update(TileEntityBarrel barrel)
	{
		boolean triggered = false;

		for (BarrelLogic entry : triggers) 
		{
			triggered = entry.onUpdate(barrel);

			if (triggered)
				break;
		}
	}
	
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item)
	{
		for (BarrelLogic entry : triggers) 
		{
			if (entry.canUseItem(barrel, item))
				return true;
		}

		return false;
	}
	
	public void useItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item)
	{
		for (BarrelLogic entry : triggers) 
		{
			if (entry.canUseItem(barrel, item))
			{
				if (entry.onUseItem(player, barrel, item))
				{
					barrel.getWorld().notifyBlockOfStateChange(barrel.getPos(), barrel.getBlockType());

					break;
				}
			}
		}
	}
	
	public boolean canExtractContents(TileEntityBarrel barrel)
	{
		return false;
	}
	
	public void onExtractContents(TileEntityBarrel barrel)
	{
		barrel.setState(BarrelStates.EMPTY);
	}
	
	public boolean canManipulateFluids(TileEntityBarrel barrel)
	{
		return false;
	}
	
	public int getLuminosity(TileEntityBarrel barrel)
	{
		return 0;
	}

	public void render(TileEntityBarrel barrel, double x, double y, double z) {}
	
	public String[] getWailaBody(TileEntityBarrel barrel)
	{
		return EMPTY_STRING_ARRAY;
	}

	public void addLogic(BarrelLogic logic) 
	{
		if (logic != null)
		{
			triggers.add(logic);
		}
	}

	public void removeLogic(BarrelLogic logic) 
	{
		triggers.remove(logic);
	}
}
