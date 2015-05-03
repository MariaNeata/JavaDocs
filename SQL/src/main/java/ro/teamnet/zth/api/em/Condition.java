package ro.teamnet.zth.api.em;

/**
 * Created by MN on 4/28/2015.
 */
public class Condition {
    private String columnName;
    private Object value;

    public Condition(String cName, Object value) {
        this.columnName = cName;
        this.value = value;
    }

    public Condition() {

    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
