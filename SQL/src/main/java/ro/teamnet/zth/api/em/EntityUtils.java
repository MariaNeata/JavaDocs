package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

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

    public static ArrayList<ColumnInfo> getColumns(Class entity) {
        ArrayList<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
        Field[] fields = entity.getDeclaredFields();
        for (Field f : fields) {
            Column c = ((Column) f.getAnnotation(Column.class));
            if (c != null) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnName(f.getName());
                columnInfo.setColumnType(f.getType());
                columnInfo.setDbName(c.name());
                columnInfos.add(columnInfo);
            }
        }
        return columnInfos;

    }

    public static Object castFromSqlType(Object value, Class<?> wantedType) {
        if ((value instanceof BigDecimal) && (wantedType == Integer.class)) {
            BigDecimal b = (BigDecimal) value;
            return b.intValue();
        }
        return value;

    }

    public static ArrayList<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        ArrayList<Field> fields = new ArrayList<Field>();
        Field[] fs = clazz.getDeclaredFields();
        for (Field i : fs)
            if (i.getAnnotation(annotation) != null)
                fields.add(i);
        return fields;
    }
}
