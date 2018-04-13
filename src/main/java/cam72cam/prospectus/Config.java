package cam72cam.prospectus;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config {

    private static final String GENERAL = "General Options";
    private static final String ACC = "The accuracy of each tool material";

    // Config options:
    public static boolean loadTF;
    public static int globalRadius;

    public static int stoneAcc;
    public static int ironAcc;
    public static int diamondAcc;

    public static int copperAcc;
    public static int tinAcc;
    public static int bronzeAcc;
    public static int invarAcc;
    public static int steelAcc;

    public static int traceMin;
    public static int smallMin;
    public static int mediumMin;
    public static int largeMin;

    // This will create the config if it doesn't exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = Prospectus.config;
        try {
            cfg.load();
            initConfig(cfg);
        } catch (Exception problem) {
            Prospectus.logger.log(Level.ERROR, "Problem loading config file!", problem);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initConfig(Configuration cfg) {

        loadTF = cfg.getBoolean("tf_tools",GENERAL,true,"If Thermal Foundation is installed, should Prospectus add tools for TF's metals?");

        globalRadius = cfg.getInt("Radius to search",GENERAL,12,1,32,"When prospecting, the pick will search up to this radius horizontally and vertically");

        traceMin = cfg.getInt("Max ore for trace:",GENERAL,1,1,100,"The amount of ore (in percent of nearby stone) to qualify as a small sample");
        smallMin = cfg.getInt("Max ore for small:",GENERAL,2,1,100,"The amount of ore (in percent of nearby stone) to qualify as a medium sample");
        mediumMin = cfg.getInt("Max ore for med:",GENERAL,4,1,100,"The amount of ore (in percent of nearby stone) to qualify as a large sample");
        largeMin = cfg.getInt("Max ore for large:",GENERAL,6,1,100,"The amount of ore (in percent of nearby stone) to qualify as a motherload");

        stoneAcc = cfg.getInt("Stone Accuracy",ACC,5,1,100,"");
        ironAcc = cfg.getInt("Iron Accuracy",ACC,20,1,100,"");
        diamondAcc = cfg.getInt("Diamond Accuracy",ACC,60,1,100,"");

        copperAcc = cfg.getInt("Copper Accuracy",ACC,10,1,100,"");
        tinAcc = cfg.getInt("Tin Accuracy",ACC,15,1,100,"");
        bronzeAcc = cfg.getInt("Bronze Accuracy",ACC,35,1,100,"");
        invarAcc = cfg.getInt("Invar Accuracy",ACC,65,1,100,"");
        steelAcc = cfg.getInt("Steel Accuracy",ACC,80,1,100,"");

    }
}