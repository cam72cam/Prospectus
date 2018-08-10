package cam72cam.prospectus;

import net.minecraftforge.common.config.Config.*;

import static cam72cam.prospectus.Prospectus.MODID;

@net.minecraftforge.common.config.Config(modid= MODID)
public class Config {

    @Comment({"Radius for prospecting pick to scan for ores [default 12]"})
    @RangeInt(min = 1, max = 128)
    public static int globalRadius = 12;

    @Comment({"Should the prospecting pick scan for ore blocks in the stone above it?"})
    public static boolean shouldScanAbove = false;

    @Comment({"Upsidown pickaxe recipe or slightly bent pickaxe recipe?"})
    public static boolean useUpsidownRecipe = true;

    @Comment({"Accuracy of stone pick [default 5]"})
    @RangeInt(min = 1, max = 100)
    public static int WOOD_ACC = 5;

    @Comment({"Accuracy of stone pick [default 5]"})
    @RangeInt(min = 1, max = 100)
    public static int STONE_ACC = 5;

    @Comment({"Accuracy of iron pick [default 20]"})
    @RangeInt(min = 1, max = 100)
    public static int IRON_ACC = 20;

    @Comment({"Accuracy of stone pick [default 5]"})
    @RangeInt(min = 1, max = 100)
    public static int GOLD_ACC = 15;

    @Comment({"Accuracy of diamond pick [default 60]"})
    @RangeInt(min = 1, max = 100)
    public static int DIAMOND_ACC = 50;

    @Comment({"Accuracy of copper pick [default 10]"})
    @RangeInt(min = 1, max = 100)
    public static int COPPER_ACC = 10;

    @Comment({"Accuracy of tin pick [default 15]"})
    @RangeInt(min = 1, max = 100)
    public static int TIN_ACC = 15;

    @Comment({"Accuracy of lead pick [default 10]"})
    @RangeInt(min = 1, max = 100)
    public static int LEAD_ACC = 20;

    @Comment({"Accuracy of silver pick [default 15]"})
    @RangeInt(min = 1, max = 100)
    public static int SILVER_ACC = 15;

    @Comment({"Accuracy of silver pick [default 15]"})
    @RangeInt(min = 1, max = 100)
    public static int ALUMINUM_ACC = 25;

    @Comment({"Accuracy of bronze pick [default 35]"})
    @RangeInt(min = 1, max = 100)
    public static int BRONZE_ACC = 35;

    @Comment({"Accuracy of invar pick [default 65]"})
    @RangeInt(min = 1, max = 100)
    public static int INVAR_ACC = 45;

    @Comment({"Accuracy of steel pick [default 80]"})
    @RangeInt(min = 1, max = 100)
    public static int STEEL_ACC = 60;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a small sample [default 1]"})
    @RangeInt(min = 1, max = 100)
    public static int traceMin = 1;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a medium sample [default 2]"})
    @RangeInt(min = 1, max = 100)
    public static int smallMin = 2;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a large sample [default 4]"})
    @RangeInt(min = 1, max = 100)
    public static int mediumMin = 4;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a motherload [default 6]"})
    @RangeInt(min = 1, max = 100)
    public static int largeMin = 6;

    @Comment("List of additional blocks to detect. Use the format 'modid:registryname'")
    public static String[] ORES = {};

}