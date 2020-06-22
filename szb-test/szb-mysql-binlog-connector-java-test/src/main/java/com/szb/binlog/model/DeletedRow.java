package com.szb.binlog.model;

import com.github.shyiko.mysql.binlog.event.deserialization.ColumnType;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;


/**
 * @author zhangzhewei
 */
public class DeletedRow extends BaseChangedRow {

    public DeletedRow(String database, String tableName, long timestamp,
                      Supplier<Map<String, Serializable>> before,
                      Supplier<Map<String, ColumnType>> columnType, Supplier<Set<String>> unsignedColumns) {
        super(database, tableName, timestamp, before, columnType, unsignedColumns);
    }

    @Override
    public BinlogType getType() {
        return BinlogType.DELETE;
    }
}
