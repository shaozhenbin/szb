package com.szb.binlog.model;


import com.github.shyiko.mysql.binlog.event.deserialization.ColumnType;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author zhangzhewei
 */
public class InsertedRow extends BaseChangedRow {

    public InsertedRow(String database, String tableName, long timestamp,
                       Supplier<Map<String, Serializable>> after, Supplier<Map<String, ColumnType>> columnType,
                       Supplier<Set<String>> unsignedColumns) {
        super(database, tableName, timestamp, after, columnType, unsignedColumns);
    }

    @Override
    public BinlogType getType() {
        return BinlogType.INSERT;
    }
}
