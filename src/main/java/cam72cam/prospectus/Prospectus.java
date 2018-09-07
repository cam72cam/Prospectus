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
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.GameData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

@Mod(modid = Prospectus.MODID, version = Prospectus.VERSION, dependencies = Prospectus.DEPENDENCIES)
public class Prospectus
{
    static final String MODID = "prospectus";
    static final String VERSION = "1.6";
    static final String DEPENDENCIES = "required-after:forge@[14.23.2.2611,15.0.0.0);";

    private static List<ItemProspector> ITEMS;
    private static Set<ResourceLocation> ORES;

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

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        reloadOreList();
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        ITEMS = new ArrayList<>();

        ITEMS.add(new ItemProspector(ToolMaterial.WOOD, Config.WOOD_ACC));
        ITEMS.add(new ItemProspector(ToolMaterial.STONE, Config.STONE_ACC));
        ITEMS.add(new ItemProspector(ToolMaterial.IRON, Config.IRON_ACC));
        ITEMS.add(new ItemProspector(ToolMaterial.GOLD, Config.GOLD_ACC));
        ITEMS.add(new ItemProspector(ToolMaterial.DIAMOND, Config.DIAMOND_ACC));

        if(hasIngot("Copper")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("COPPER",1,180,2.0F,1.0F,1), Config.COPPER_ACC));
        if(hasIngot("Tin")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("TIN",0,80,2.0F,1.0F,3), Config.TIN_ACC));
        if(hasIngot("Silver")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("SILVER",0,100,2.0F,1.0F,12), Config.SILVER_ACC));
        if(hasIngot("Lead")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("LEAD",1,400,2.0F,1.0F,1), Config.LEAD_ACC));
        if(hasIngot("Aluminum")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("ALUMINUM",1,320,2.0F,1.0F,6), Config.ALUMINUM_ACC));
        if(hasIngot("Bronze")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("BRONZE",2,800,2.0F,1.0F,3), Config.BRONZE_ACC));
        if(hasIngot("Steel")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("STEEL",3,1400,2.0F,1.0F,6), Config.STEEL_ACC));
        if(hasIngot("Invar")) ITEMS.add(new ItemProspector(EnumHelper.addToolMaterial("INVAR",3,1200,2.0F,1.0F,8), Config.INVAR_ACC));

        for(Item item : ITEMS){
    	    if(item == null) continue;
    	    event.getRegistry().register(item);
        }
    }
    
    @SubscribeEvent
	public void configChanged(OnConfigChangedEvent event) {
		if (event.getModID().equals(MODID)) {
			ConfigManager.sync(MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
			reloadOreList();
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

    private static void reloadOreList()
    {
        ORES = new HashSet<>();
        // Add ore-dictionary guesses
        for (String s : OreDictionary.getOreNames())
            if(s.length() >= 4 && s.startsWith("ore") && !Character.isLowerCase(s.charAt(3)))
                ORES.addAll(OreDictionary.getOres(s)
                        .stream()
                        .map(x -> x.getItem().getRegistryName())
                        .collect(Collectors.toList()));

        // Remove blacklisted ores
        ORES.removeIf(x -> Arrays.stream(Config.BLACKLIST)
                .anyMatch(y -> x.equals(resLocFromString(y))));

        // Add whitelisted ores
        for (String s : Config.ORES)
            ORES.add(resLocFromString(s));

        // cleanup
        ORES.removeIf(Objects::isNull);
    }

    @Nullable
    private static ResourceLocation resLocFromString(String s)
    {
        int colon = s.indexOf(':');
        if (colon != -1)
            return new ResourceLocation(s.substring(0, colon), s.substring(colon + 1));
        return null;
    }

    static void addRecipe(@Nonnull ItemStack output, Object... params) {
        ResourceLocation location = new ResourceLocation(MODID,"recipe_"+output.getDisplayName());
        ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
        recipe.setRegistryName(location);
        GameData.register_impl(recipe);
    }

    static boolean isBlockWhitelisted(@Nullable ResourceLocation loc)
    {
        return loc != null && ORES.contains(loc);
    }
}
