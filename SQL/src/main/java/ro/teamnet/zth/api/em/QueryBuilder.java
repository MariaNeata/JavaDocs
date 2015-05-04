package ro.teamnet.zth.api.em;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MN on 4/29/2015.
 */
public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<>();

    public QueryBuilder() {

    }

    public QueryBuilder addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery() {
        StringBuilder select = new StringBuilder();
        select.append("Select ");
        for (ColumnInfo i : queryColumns) {
            select.append(tableName + "." + i.getDbName());
            select.append(", ");
        }
        select.delete(select.lastIndexOf(","), (select.lastIndexOf(",") + 1));
        select.append(" from " + tableName);

        select.append(" where ");
        for (Condition i : conditions) {
            select.append(i.getColumnName() + "=" + i.getValue());
            select.append(" and ");
        }
        select.delete(select.lastIndexOf("and"), (select.lastIndexOf("and") + 4));
        return select.toString();
    }


    private String createDeleteQuery() {
        StringBuilder delete = new StringBuilder();
        delete.append("DELETE FROM ");
        delete.append(tableName);
        delete.append(" where ");

        for (Condition i : conditions) {
            delete.append(i.getColumnName() + "=" + i.getValue());
            delete.append(" and ");
        }
        delete.delete(delete.lastIndexOf("and"), (delete.lastIndexOf("and") + 4));
        return delete.toString();


    }

    private String createUpdateQuery() {
        StringBuilder update = new StringBuilder();
        update.append("UPDATE " + tableName + " SET ");
        for (ColumnInfo i : queryColumns) {
            update.append(i.getColumnName() + "=" + i.getValue());
            update.append(" and ");
        }
        update.delete(update.lastIndexOf("and"), (update.lastIndexOf("and") + 3));
        update.append("WHERE ");
        for (Condition i : conditions) {
            update.append(i.getColumnName() + "=" + i.getValue());
            update.append(" and ");
        }
        update.delete(update.lastIndexOf("and"), (update.lastIndexOf("and") + 3));
        return update.toString();
    }


    private String createInsertQuery() {
        StringBuilder insert = new StringBuilder();
        insert.append("Insert into ").append(tableName).append(" (");
        for (ColumnInfo i : queryColumns) {
            insert.append(i.getColumnName()).append(", ");
        }
        insert.delete(insert.lastIndexOf(","), (insert.lastIndexOf(",") + 1));
        insert.append(") values ");
        for (ColumnInfo i : queryColumns) {
            insert.append(i.getValue()).append(", ");
        }
        insert.delete(insert.lastIndexOf(","), (insert.lastIndexOf(",") + 1));
        return insert.toString();
    }


    public String createQuery() {
        String s = null;
        if (this.queryType.equals(QueryType.SELECT)) s = createSelectQuery();
        if (this.queryType.equals(QueryType.DELETE)) s = createDeleteQuery();
        if (this.queryType.equals(QueryType.UPDATE)) s = createUpdateQuery();
        if (this.queryType.equals(QueryType.INSERT)) s = createInsertQuery();
        return s;
    }

  /*  private String getValueForQuery(Object value) {
        if (value == null){
            return null;
        }
        if (value instanceof String){
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }*/
}


