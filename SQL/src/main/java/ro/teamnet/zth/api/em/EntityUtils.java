package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MN on 4/28/2015.
 */
public class EntityUtils {
    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        return ((Table) entity.getAnnotation(Table.class)).name();
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> columns = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for (Field f : fields) {
            Column c = ((Column) f.getAnnotation(Column.class));
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(f.getName());
            columnInfo.setColumnType(f.getType());
            if (c != null) {
                columnInfo.setDbName(c.name());
            } else {
                Id id = ((Id) f.getAnnotation(Id.class));
                columnInfo.setDbName(id.name());
                columnInfo.setId(true);
            }
            columns.add(columnInfo);
        }
        return columns;

    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value != null) {
            if (value instanceof BigDecimal) {
                BigDecimal b = (BigDecimal) value;
                return wantedType.equals(Integer.class) ? b.intValue() :
                        wantedType.equals(Long.class) ? b.longValue() :
                                wantedType.equals(Float.class) ? b.floatValue() :
                                        wantedType.equals(Double.class) ? b.doubleValue() : value;
            } else {
                return value;
            }
        }
        return null;

    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> fields = new ArrayList<Field>();
        Field[] fs = clazz.getDeclaredFields();
        for (Field i : fs)
            if (i.getAnnotation(annotation) != null)
                fields.add(i);
        return fields;
    }
/*
*   public static Object getSqlValue(Object object) throws IllegalAccessException {
        if(object == null) {
            return null;
        }
        if(object.getClass().getAnnotation(Table.class) != null) {
            Field idField = getFieldsByAnnotations(object.getClass(), Id.class).get(0);
            idField.setAccessible(true);
            return idField.get(object);
        } else {
            return object;
        }
    }*/

}
