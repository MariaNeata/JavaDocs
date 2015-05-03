package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by MN on 4/28/2015.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        Department department = new Department();
        String tableName = EntityUtils.getTableName(Department.class);

        assertEquals("Table name should be departments!", "departments", tableName);

    }

}
