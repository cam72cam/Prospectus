package cam72cam.prospectus;

import net.minecraftforge.common.config.Config.Comment;

@net.minecraftforge.common.config.Config(modid=Prospectus.MODID)
public class Config {


    @Comment({"Should Prospectus create tools from Thermal Foundation metals?"})
    public static boolean loadTF = true;

    @Comment({"Radius for prospecting pick to scan for ores [range 1 ~ 32, default 12]"})
    public static int globalRadius = 12;

    @Comment({"Should the prospecting pick scan for ore blocks in the stone above it?"})
    public static boolean shouldScanAbove = false;

    @Comment({"Accuracy of stone pick [range 1 ~ 100, default 5]"})
    public static int stoneAcc = 5;
    @Comment({"Accuracy of iron pick [range 1 ~ 100, default 20]"})
    public static int ironAcc = 20;
    @Comment({"Accuracy of diamond pick [range 1 ~ 100, default 60]"})
    public static int diamondAcc = 60;

    @Comment({"Accuracy of copper pick [range 1 ~ 100, default 10]"})
    public static int copperAcc = 10;
    @Comment({"Accuracy of tin pick [range 1 ~ 100, default 15]"})
    public static int tinAcc = 15;
    @Comment({"Accuracy of bronze pick [range 1 ~ 100, default 35]"})
    public static int bronzeAcc = 35;
    @Comment({"Accuracy of invar pick [range 1 ~ 100, default 65]"})
    public static int invarAcc = 65;
    @Comment({"Accuracy of steel pick [range 1 ~ 100, default 80]"})
    public static int steelAcc = 80;

    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a small sample [range 1 ~ 100, default 1]"})
    public static int traceMin = 1;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a medium sample [range 1 ~ 100, default 2]"})
    public static int smallMin = 2;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a large sample [range 1 ~ 100, default 4]"})
    public static int mediumMin = 4;
    @Comment({"The amount of ore (In percent of nearby stone) to qualify as a motherload [range 1 ~ 100, default 6]"})
    public static int largeMin = 6;

}