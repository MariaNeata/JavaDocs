package ro.teamnet.zth.api.database;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by MN on 4/28/2015.
 */
public class DBManagerTest {
    @Test
    public void testConnectionMethod() {
        try (Connection connection = DBManager.getConnection()) {
            org.junit.Assert.assertNotNull("Connection was not created", connection);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }




}
