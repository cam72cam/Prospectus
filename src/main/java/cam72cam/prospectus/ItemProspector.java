package cam72cam.prospectus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemProspector extends ItemTool {

	private static final String NAME = "item_prospector_";
	private final String VARIANT;
	private final int accuracy;

	ItemProspector(Item.ToolMaterial type, int accuracy) {
		super(type, new HashSet<>());
        
		this.accuracy = accuracy;
		this.VARIANT = type.toString().toLowerCase();

		setUnlocalizedName(Prospectus.MODID + ":" + NAME + VARIANT);
		setRegistryName(new ResourceLocation(Prospectus.MODID, NAME + VARIANT));
		Prospectus.ITEMS.add(this);
	}

	public void sendRecipe(){
		String ingot;
		switch(VARIANT){
			case "wood": ingot = "plankWood"; break;
			case "stone": ingot = "cobblestone"; break;
			case "diamond": ingot = "gemDiamond"; break;
			default:
				ingot = "ingot"+VARIANT.substring(0, 1).toUpperCase() + VARIANT.substring(1);
		}
		if(Config.useUpsidownRecipe)
			Prospectus.addRecipe(new ItemStack(this), " S "," S ","III",'S',"stickWood",'I',ingot);
		else
			Prospectus.addRecipe(new ItemStack(this), " II", "IS ", " S ", 'S', "stickWood", 'I', ingot);
	}
	
	@Override
	@Nonnull
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return EnumActionResult.PASS;
		}
		
		player.getHeldItem(hand).damageItem(1, player);
		
		Map<String, Integer> counts = new HashMap<>();
		int radius = Config.globalRadius;

		int upperbound = Config.shouldScanAbove ? -radius:0;
		
		for (int x = -radius; x < radius; x++) {
			for (int y = upperbound; y < radius; y++) {
				for (int z = -radius; z < radius; z++) {
					if (Math.random() * 100 < accuracy) {
						BlockPos curr = pos.down(y).east(x).north(z);
						IBlockState state = world.getBlockState(curr);
						//String name = state.getBlock().getLocalizedName();
						@SuppressWarnings("deprecation")
						String name = state.getBlock().getItem(world, curr, state).getDisplayName();
						if (! name.toLowerCase().contains("ore")) {
							continue;
						}
						if (!counts.containsKey(name) ) {
							counts.put(name, 0);
						}
						counts.put(name, counts.get(name) + 1);
					}
				}
			}
		}
		if(!counts.keySet().isEmpty()) {
			player.sendMessage(new TextComponentString("Found: "));
			for (String key : counts.keySet()) {
				String val;
				Integer count = counts.get(key);
				double scale = Math.pow(radius, 3) * 0.01; // This now searches based on the % of nearby blocks which are ore
				if (count < Config.traceMin * scale) {
					val = "Traces of ";
				} else if (count < Config.smallMin * scale) {
					val = "A Small Sample of ";
				} else if (count < Config.mediumMin * scale) {
					val = "A Medium Sample of ";
				} else if (count < Config.largeMin * scale) {
					val = "A Large Sample of ";
				} else {
					val = "The Motherload of ";
				}

				player.sendMessage(new TextComponentString("  " + val + key));
			}
		}
		else {
			player.sendMessage(new TextComponentString("Found nothing of interest."));
		}
		
		
		return EnumActionResult.PASS;
	}
}
