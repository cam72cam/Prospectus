package cam72cam.prospectus;

import net.minecraftforge.common.config.Config.Comment;

@net.minecraftforge.common.config.Config(modid=Prospectus.MODID)
public class Config {

    @Comment({"Radius for prospecting pick to scan for ores [range 1 ~ 32, default 12]"})
    public static int globalRadius = 12;

    @Comment({"Should the prospecting pick scan for ore blocks in the stone above it?"})
    public static boolean shouldScanAbove = false;

    @Comment({"Accuracy of stone pick [range 1 ~ 100, default 5]"})
    public static int WOOD_ACC = 5;
    @Comment({"Accuracy of stone pick [range 1 ~ 100, default 5]"})
    public static int STONE_ACC = 5;
    @Comment({"Accuracy of iron pick [range 1 ~ 100, default 20]"})
    public static int IRON_ACC = 20;
    @Comment({"Accuracy of stone pick [range 1 ~ 100, default 5]"})
    public static int GOLD_ACC = 15;
    @Comment({"Accuracy of diamond pick [range 1 ~ 100, default 60]"})
    public static int DIAMOND_ACC = 50;

    @Comment({"Accuracy of copper pick [range 1 ~ 100, default 10]"})
    public static int COPPER_ACC = 10;
    @Comment({"Accuracy of tin pick [range 1 ~ 100, default 15]"})
    public static int TIN_ACC = 15;
    @Comment({"Accuracy of lead pick [range 1 ~ 100, default 10]"})
    public static int LEAD_ACC = 20;
    @Comment({"Accuracy of silver pick [range 1 ~ 100, default 15]"})
    public static int SILVER_ACC = 15;
    @Comment({"Accuracy of silver pick [range 1 ~ 100, default 15]"})
    public static int ALUMINUM_ACC = 25;
    @Comment({"Accuracy of bronze pick [range 1 ~ 100, default 35]"})
    public static int BRONZE_ACC = 35;
    @Comment({"Accuracy of invar pick [range 1 ~ 100, default 65]"})
    public static int INVAR_ACC = 45;
    @Comment({"Accuracy of steel pick [range 1 ~ 100, default 80]"})
    public static int STEEL_ACC = 60;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a small sample [range 1 ~ 100, default 1]"})
    public static int traceMin = 1;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a medium sample [range 1 ~ 100, default 2]"})
    public static int smallMin = 2;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a large sample [range 1 ~ 100, default 4]"})
    public static int mediumMin = 4;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a motherload [range 1 ~ 100, default 6]"})
    public static int largeMin = 6;

}