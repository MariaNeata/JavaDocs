package ro.teamnet.zth.api.database;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by MN on 4/28/2015.
 */
public class DBManagerTest {
    @Test
    public void testConnectionMethod() {
        assertNotNull(DBManager.getConnection());
    }

}
