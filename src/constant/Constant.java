package constant;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final int UDP_PORT = 7777;

    public static final int TCP_PORT = 6666;

    public static final int RADAR_PORT = 8800;

    public static final int RECORD_PORT = 8888;

    public static final String MULTICAST_IP = "230.0.0.1";

    public static final int CHECKPOINT_HEIGHT = 60;

    public static final int HALF_BLOCK_GAP = 5;

    private static final String WINDOWS_BASE_PATH = "C:\\Users\\DSY\\blockchain\\";

    private static final String MAC_BASE_PATH = "/Users/dingsiye/projects/AeroChain/";

    private static String BASE_PATH = SystemUtils.IS_OS_WINDOWS ? WINDOWS_BASE_PATH : MAC_BASE_PATH;

    public static final String LOG_BASE_PATH = BASE_PATH + File.separator + "log" + File.separator;

    public static List<String> ADDRESS = new ArrayList<>();
    static {
//        ADDRESS.add("192.168.0.124");
//        ADDRESS.add("192.168.0.123");
//        ADDRESS.add("192.168.0.37");
//        address.add("192.168.0.113");
        ADDRESS.add("127.0.0.1");
    }

    public static final String SIMULATE = BASE_PATH + "simulate.txt";
}
