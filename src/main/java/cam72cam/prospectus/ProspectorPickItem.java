package cam72cam.prospectus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ProspectorPickItem extends ItemTool {
	public static final String NAME = "item_prospector_";
	
	private final int radius;
	public final int accuracy;
	
	public ProspectorPickItem(Item.ToolMaterial type, int radius, int accuracy) {
		super(type, new HashSet<Block>());
		setUnlocalizedName(Prospectus.MODID + ":" + NAME + type.toString().toLowerCase());
        setRegistryName(new ResourceLocation(Prospectus.MODID, NAME + type.toString().toLowerCase()));
        
		this.radius = radius; 
		this.accuracy = accuracy;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return EnumActionResult.PASS;
		}
		
		player.getHeldItem(hand).damageItem(1, player);
		
		Map<String, Integer> counts = new HashMap<String, Integer>();
		
		for (int x = -radius; x < radius; x++) {
			for (int y = 0; y < radius; y++) {
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
		
		player.sendMessage(new TextComponentString("Found: "));
		for (String key : counts.keySet()) {
			String val = "";
			Integer count = counts.get(key);
			double scale =  Math.pow(radius / 16, 2) * (accuracy / 5);
			if (count < 5 * scale) {
				val = "A Few ";
			} else if (count < 10 * scale) {
				val = "A Handfull of ";
			} else if (count < 20 * scale) {
				val = "A Bunch of ";
			} else if (count < 30 * scale) {
				val = "A Ton of ";
			} else {
				val = "The Motherload of ";
			}
			
			player.sendMessage(new TextComponentString("  " + val + key));
		}
		
		
		return EnumActionResult.PASS;
	}
}
