package cam72cam.prospectus;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Prospectus.MODID, version = Prospectus.VERSION)
public class Prospectus
{
    public static final String MODID = "prospectus";
    public static final String VERSION = "0.1";
    public static final Item ITEM_PROSPECTOR_STONE = new ProspectorPickItem(ToolMaterial.STONE, 16, 5);
    public static final Item ITEM_PROSPECTOR_IRON = new ProspectorPickItem(ToolMaterial.IRON, 16, 20);
    public static final Item ITEM_PROSPECTOR_DIAMOND = new ProspectorPickItem(ToolMaterial.DIAMOND, 16, 60);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
    	event.getRegistry().register(ITEM_PROSPECTOR_STONE);
    	event.getRegistry().register(ITEM_PROSPECTOR_IRON);
    	event.getRegistry().register(ITEM_PROSPECTOR_DIAMOND);
    }
    
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_STONE, 0, new ModelResourceLocation(ITEM_PROSPECTOR_STONE.getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_IRON, 0, new ModelResourceLocation(ITEM_PROSPECTOR_IRON.getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_DIAMOND, 0, new ModelResourceLocation(ITEM_PROSPECTOR_DIAMOND.getRegistryName(), "inventory"));
    }
}
