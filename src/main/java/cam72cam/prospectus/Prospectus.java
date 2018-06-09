package cam72cam.prospectus;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.GameData;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = Prospectus.MODID, version = Prospectus.VERSION)
public class Prospectus
{
    public static final String MODID = "prospectus";
    static final String VERSION = "1.2";

    public static ItemProspector prospectorWood;
    public static ItemProspector prospectorStone;
    public static ItemProspector prospectorIron;
    public static ItemProspector prospectorGold;
    public static ItemProspector prospectorDiamond;
    public static ItemProspector prospectorCopper;
    public static ItemProspector prospectorTin;
    public static ItemProspector prospectorLead;
    public static ItemProspector prospectorSilver;
    public static ItemProspector prospectorAluminum;
    public static ItemProspector prospectorBronze;
    public static ItemProspector prospectorSteel;
    public static ItemProspector prospectorInvar;

    public static List<ItemProspector> ITEMS = new ArrayList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @EventHandler
    public void init(FMLInitializationEvent event){
        for(ItemProspector item : ITEMS){
            if(item == null) continue;
            item.sendRecipe();
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        prospectorWood = new ItemProspector(ToolMaterial.WOOD, Config.WOOD_ACC);
        prospectorStone = new ItemProspector(ToolMaterial.STONE, Config.STONE_ACC);
        prospectorIron = new ItemProspector(ToolMaterial.IRON, Config.IRON_ACC);
        prospectorGold = new ItemProspector(ToolMaterial.GOLD, Config.GOLD_ACC);
        prospectorDiamond = new ItemProspector(ToolMaterial.DIAMOND, Config.DIAMOND_ACC);

        if(hasIngot("Copper")) prospectorCopper = new ItemProspector(EnumHelper.addToolMaterial("COPPER",1,180,2.0F,1.0F,1), Config.COPPER_ACC);
        if(hasIngot("Tin")) prospectorTin = new ItemProspector(EnumHelper.addToolMaterial("TIN",0,80,2.0F,1.0F,3), Config.TIN_ACC);
        if(hasIngot("Silver")) prospectorSilver = new ItemProspector(EnumHelper.addToolMaterial("SILVER",0,100,2.0F,1.0F,12), Config.SILVER_ACC);
        if(hasIngot("Lead")) prospectorLead = new ItemProspector(EnumHelper.addToolMaterial("LEAD",1,400,2.0F,1.0F,1), Config.LEAD_ACC);
        if(hasIngot("Aluminum")) prospectorAluminum = new ItemProspector(EnumHelper.addToolMaterial("ALUMINUM",1,320,2.0F,1.0F,6), Config.ALUMINUM_ACC);
        if(hasIngot("Bronze")) prospectorBronze = new ItemProspector(EnumHelper.addToolMaterial("BRONZE",2,800,2.0F,1.0F,3), Config.BRONZE_ACC);
        if(hasIngot("Steel")) prospectorSteel = new ItemProspector(EnumHelper.addToolMaterial("STEEL",3,1400,2.0F,1.0F,6), Config.STEEL_ACC);
        if(hasIngot("Invar")) prospectorInvar = new ItemProspector(EnumHelper.addToolMaterial("INVAR",3,1200,2.0F,1.0F,8), Config.INVAR_ACC);

        for(Item item : ITEMS){
    	    if(item == null) continue;
    	    event.getRegistry().register(item);
        }
    }
    
    @SubscribeEvent
	public void configChanged(OnConfigChangedEvent event) {
		if (event.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
		}
	}
    
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        for(Item item : ITEMS){
    	    if(item.getRegistryName() == null) continue;
    	    ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
        }
    }

    private boolean hasIngot(@Nonnull String name){
        if(!OreDictionary.doesOreNameExist("ingot"+name)) return false;
        List<ItemStack> ingots = OreDictionary.getOres("ingot"+name);
        return !ingots.isEmpty();
    }
    public static void addRecipe(@Nonnull ItemStack output, Object... params) {
        ResourceLocation location = new ResourceLocation(MODID,"recipe_"+output.getDisplayName());
        ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
        recipe.setRegistryName(location);
        GameData.register_impl(recipe);
    }
}
