package cam72cam.prospectus;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Prospectus.MODID, version = Prospectus.VERSION, dependencies = "after:thermalfoundation")
public class Prospectus
{
    public static final String MODID = "prospectus";
    public static final String VERSION = "0.2";
    public static Item ITEM_PROSPECTOR_STONE;
    public static Item ITEM_PROSPECTOR_IRON;
    public static Item ITEM_PROSPECTOR_DIAMOND;

    public static Item ITEM_PROSPECTOR_TF_COPPER;
    public static Item ITEM_PROSPECTOR_TF_TIN;
    public static Item ITEM_PROSPECTOR_TF_BRONZE;
    public static Item ITEM_PROSPECTOR_TF_STEEL;
    public static Item ITEM_PROSPECTOR_TF_INVAR;

    public static boolean addTETools;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(this);

        // Vanilla Tools (Stone, Iron, Diamond)
        ITEM_PROSPECTOR_STONE = new ProspectorPickItem(ToolMaterial.STONE, Config.globalRadius, Config.stoneAcc);
        ITEM_PROSPECTOR_IRON = new ProspectorPickItem(ToolMaterial.IRON, Config.globalRadius, Config.ironAcc);
        ITEM_PROSPECTOR_DIAMOND = new ProspectorPickItem(ToolMaterial.DIAMOND, Config.globalRadius, Config.diamondAcc);

        // Thermal Foundation Tools (Copper, Tin, Bronze, Invar, Steel)
        addTETools = Loader.isModLoaded("thermalfoundation") && Config.loadTF;
    	if(addTETools){
    	    ITEM_PROSPECTOR_TF_COPPER = new ProspectorPickItem(ToolMaterial.valueOf("TF:COPPER"), Config.globalRadius, Config.copperAcc);
    	    ITEM_PROSPECTOR_TF_TIN = new ProspectorPickItem(ToolMaterial.valueOf("TF:TIN"), Config.globalRadius, Config.tinAcc);
    	    ITEM_PROSPECTOR_TF_BRONZE = new ProspectorPickItem(ToolMaterial.valueOf("TF:BRONZE"), Config.globalRadius, Config.bronzeAcc);
    	    ITEM_PROSPECTOR_TF_STEEL = new ProspectorPickItem(ToolMaterial.valueOf("TF:STEEL"), Config.globalRadius, Config.steelAcc);
    	    ITEM_PROSPECTOR_TF_INVAR = new ProspectorPickItem(ToolMaterial.valueOf("TF:INVAR"), Config.globalRadius, Config.invarAcc);
        }
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                ITEM_PROSPECTOR_STONE,
                ITEM_PROSPECTOR_IRON,
                ITEM_PROSPECTOR_DIAMOND
        );
    	if(addTETools){
            event.getRegistry().registerAll(
                    ITEM_PROSPECTOR_TF_COPPER,
                    ITEM_PROSPECTOR_TF_BRONZE,
                    ITEM_PROSPECTOR_TF_TIN,
                    ITEM_PROSPECTOR_TF_INVAR,
                    ITEM_PROSPECTOR_TF_STEEL
            );
        }
    }
    
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_STONE, 0, new ModelResourceLocation(ITEM_PROSPECTOR_STONE.getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_IRON, 0, new ModelResourceLocation(ITEM_PROSPECTOR_IRON.getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_DIAMOND, 0, new ModelResourceLocation(ITEM_PROSPECTOR_DIAMOND.getRegistryName(), "inventory"));

    	if(addTETools){
    	    ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_TF_COPPER,0,new ModelResourceLocation(ITEM_PROSPECTOR_TF_COPPER.getRegistryName(),"inventory"));
    	    ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_TF_TIN,0,new ModelResourceLocation(ITEM_PROSPECTOR_TF_TIN.getRegistryName(),"inventory"));
    	    ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_TF_BRONZE,0,new ModelResourceLocation(ITEM_PROSPECTOR_TF_BRONZE.getRegistryName(),"inventory"));
    	    ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_TF_INVAR,0,new ModelResourceLocation(ITEM_PROSPECTOR_TF_INVAR.getRegistryName(),"inventory"));
    	    ModelLoader.setCustomModelResourceLocation(ITEM_PROSPECTOR_TF_STEEL,0,new ModelResourceLocation(ITEM_PROSPECTOR_TF_STEEL.getRegistryName(),"inventory"));
        }
    }
}
