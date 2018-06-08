package constant;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String PRE_PREPARE_TAG = "<pre-prepare>";

    public static final String PREPARE_TAG = "<prepare>";

    public static final int PORT = 7777;

    public static final int CHECKPOINT_HEIGHT = 60;

    public static final int BLOCK_GAP = 10;

    private static final String WINDOWS_BASE_PATH = "C:\\Users\\DSY\\blockchain\\";

    private static final String MAC_BASE_PATH = "/Users/dingsiye/projects/AeroChain/";

    private static String BASE_PATH = SystemUtils.IS_OS_WINDOWS ? WINDOWS_BASE_PATH : MAC_BASE_PATH;

    public static final String LOG_BASE_PATH = BASE_PATH + File.separator + "log" + File.separator;

    public static List<String> address = new ArrayList<>();
    static {
        address.add("127.0.0.1");
//        address.add("192.168.0.112");
//        address.add("192.168.0.124");
//        address.add("192.168.0.127");
//        address.add("192.168.0.116");
    }

    public static final String SIMULATE = BASE_PATH + "simulate.txt";
}
