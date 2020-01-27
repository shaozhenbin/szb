import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.google.common.collect.Maps;
import com.szb.binlog.util.BinlogCache;
import com.szb.binlog.util.BinlogEventListener;
import com.szb.binlog.util.BinlogResolver;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhewei
 */
public class BinlogTest {

    public static final String HOST_NAME = "39.108.78.239";
    public static final int PORT = 3306;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "123456";
    public static final String SCHEMA = "ptest";

    public static void main(String[] args) throws IOException {
        BinaryLogClient binaryLogClient = new BinaryLogClient(HOST_NAME, PORT, SCHEMA, USER_NAME, PASSWORD);
//        binaryLogClient.set

        BinlogCache binlogCache = new BinlogCache(HOST_NAME, PORT, USER_NAME, PASSWORD,"ptest");

        PersonBinlogResolver personBinlogResolver = new PersonBinlogResolver();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Map<String, BinlogResolver> resolverMap = Maps.newHashMap();
        Map<BinlogResolver, ExecutorService> forceSingleThreadPools = Maps.newHashMap();
        resolverMap.put("person", personBinlogResolver);
        binaryLogClient.registerEventListener(new BinlogEventListener(binlogCache, resolverMap,
                cachedThreadPool, forceSingleThreadPools));
        binaryLogClient.connect();
    }

}
