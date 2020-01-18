import com.google.common.collect.ImmutableSet;
import com.szb.binlog.model.BaseChangedRow;
import com.szb.binlog.model.UpdatedRow;
import com.szb.binlog.util.BinlogResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.collections4.MapUtils.*;

/**
 * @author zhangzhewei
 */
public class PersonBinlogResolver implements BinlogResolver {

    private Set<String> tableNamePrefixes = ImmutableSet.of("person");

    private static final Logger logger = LoggerFactory.getLogger(PersonBinlogResolver.class);

    @Override
    public void onUpdate(BaseChangedRow row) throws Exception {

        Map<String, Serializable> affectedRow = row.getAffectedRow();
        String id = getString(affectedRow, "id");
        String code = getString(affectedRow, "CODE");
        String name = getString(affectedRow, "NAME");
        logger.info("szb:{}-{}-{}", id, code, name);
        switch (row.getType()) {
            case INSERT:
                logger.info("szb:insert");
                break;
            case UPDATE:
                Map<String, Serializable> before = ((UpdatedRow) row).getBefore();
                String idBefore = getString(before, "id");
                String codeBefore = getString(before, "CODE");
                String nameBefore = getString(before, "NAME");
                logger.info("szb:{}-{}-{}", idBefore, codeBefore, nameBefore);
                logger.info("szb:update");
                break;
            case DELETE:
                logger.info("szb:delete");
                break;
        }
    }

    @Override
    public boolean acceptTableName(String tableName) {
        return tableNamePrefixes.stream().anyMatch(tableName::startsWith);
    }

}
