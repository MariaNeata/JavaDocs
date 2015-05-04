package ro.teamnet.zth.api.em;

import com.mysql.jdbc.Connection;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import sun.security.jca.GetInstance;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MN on 4/29/2015.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, int id) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> tableColumns = EntityUtils.getColumns(entityClass);
        List<Field> fieldsByAnnotations = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);
        Condition condObj = new Condition();
        condObj.setColumnName(fieldsByAnnotations.get(0).getAnnotation(Id.class).name());
        condObj.setValue(id);
        QueryBuilder QBobj = new QueryBuilder();
        QBobj.addCondition(condObj);
        QBobj.addQueryColumns(tableColumns);
        QBobj.setTableName(tableName);
        QueryType qt = QueryType.SELECT;
        QBobj.setQueryType(qt);
        String query = QBobj.createQuery();
        Statement statement = null;
        T t = null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) try {
                t = entityClass.newInstance();
                for (ColumnInfo column : tableColumns) {
                    try {
                        Field f = entityClass.getDeclaredField(column.getColumnName());
                        f.setAccessible(true);
                        f.set(t, EntityUtils.castFromSqlType(column.getValue(), column.getColumnType()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return t;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        //	create a connection to DB;
        Connection con = DBManager.getConnection();
        //-	get table name;
        String tableName = EntityUtils.getTableName(entityClass);
        //-	get table columns;
        List<ColumnInfo> tableColumns = EntityUtils.getColumns(entityClass);
        //-	create a QueryBuilder object  where you set the name of table, query type and columns;
        QueryBuilder QBobj = new QueryBuilder();
        QBobj.addQueryColumns(tableColumns);
        QBobj.setTableName(tableName);
        QueryType qt = QueryType.SELECT;
        QBobj.setQueryType(qt);
        //-	call createQuery() method from QueryBuilder.java;
        String query = QBobj.createQuery();
        // -create a Statement object and execute the query;
        Statement statement = null;

        List<T> list = new ArrayList<>();

        try { statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            T t = entityClass.newInstance();
            while (resultSet.next()) {

                for (ColumnInfo column : tableColumns) {
                    Field f = t.getClass().getDeclaredField(column.getColumnName());
                    f.setAccessible(true);
                    f.set(t, EntityUtils.castFromSqlType(column.getValue(), column.getColumnType()));
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public <T> T insert(T entity) {
        int lastId;
        //	create a connection to DB;
        Connection con = DBManager.getConnection();
        //-	get table name;
        String tableName = EntityUtils.getTableName(entity.getClass());
        //-	get table columns;
        List<ColumnInfo> tableColumns = EntityUtils.getColumns(entity.getClass());
        //-	set column value with the value from entity;
        for (ColumnInfo column : tableColumns) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        //-	create a QueryBuilder object  where you set the name of table, query type and columns;
        QueryBuilder QBobj = new QueryBuilder();
        QBobj.addQueryColumns(tableColumns);
        QBobj.setTableName(tableName);
        QueryType qt = QueryType.INSERT;
        QBobj.setQueryType(qt);
        //-	call createQuery() method from QueryBuilder.java;
        String query = QBobj.createQuery();
        Statement statement = null;

        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT last_inserted_id()");
            resultSet.next();
            lastId = resultSet.getInt(1);
            resultSet.close();
            return (T) findById(entity.getClass(), lastId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T update(T entity) {
        //	create a connection to DB;
        Connection con = DBManager.getConnection();
        //-	get table name;
        String tableName = EntityUtils.getTableName(entity.getClass());
        //-	get table columns;
        List<ColumnInfo> tableColumns = EntityUtils.getColumns(entity.getClass());
        //-	set column value with the value from entity;
        for (ColumnInfo column : tableColumns) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // -	create a Condition object where you need to set id value which will be updated;
        Condition condObj = new Condition();
        condObj.setColumnName(tableColumns.get(0).getDbName());
        condObj.setValue(tableColumns.get(0).getValue());
        //-	create a QueryBuilder object  where you set the name of table, query type and columns;
        QueryBuilder QBobj = new QueryBuilder();
        QBobj.addQueryColumns(tableColumns);
        QBobj.setTableName(tableName);
        QBobj.addCondition(condObj);
        QueryType qt = QueryType.UPDATE;
        QBobj.setQueryType(qt);
        //-	call createQuery() method from QueryBuilder.java;
        String query = QBobj.createQuery();





        try {Statement   statement = con.createStatement();
            statement.executeUpdate(query);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object entity) {
        //	create a connection to DB;
        Connection con = DBManager.getConnection();
        //-	get table name;
        String tableName = EntityUtils.getTableName(entity.getClass());
        //-	get table columns;
        List<ColumnInfo> tableColumns = EntityUtils.getColumns(entity.getClass());
        //-	set column value with the value from entity;
        for (ColumnInfo column : tableColumns) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // -	create a Condition object where you need to set id value which will be updated;
        Condition condObj = new Condition();
        condObj.setColumnName(tableColumns.get(0).getDbName());
        condObj.setValue(tableColumns.get(0).getValue());
        //-	create a QueryBuilder object  where you set the name of table, query type and columns;
        QueryBuilder QBobj = new QueryBuilder();
        QBobj.addQueryColumns(tableColumns);
        QBobj.setTableName(tableName);
        QueryType qt = QueryType.DELETE;
        QBobj.addCondition(condObj);
        QBobj.setQueryType(qt);
        //-	call createQuery() method from QueryBuilder.java;
        String query = QBobj.createQuery();
        Statement statement = null;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
