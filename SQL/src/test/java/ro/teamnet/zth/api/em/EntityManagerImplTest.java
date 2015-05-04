package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by MN on 4/30/2015.
 */
public class EntityManagerImplTest {
    EntityManagerImpl entityManager = new EntityManagerImpl();
    static Department dep = new Department();
    @Test
    public void testFindByIdMethod() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Location location = entityManager.findById(Location.class, 1100);

    }

    @Test
    public void aTestInsert() {
        dep.setDepartmentName("test unitar");
        dep = entityManager.insert(dep);

        assertEquals(entityManager.findById(Department.class, dep.getId()), dep);
    }

    @Test
    public void bTestUpdate() {
        dep.setDepartmentName("test unitar2");
        dep = entityManager.update(dep);

        assertEquals(entityManager.findById(Department.class, dep.getId()), dep);
    }

    @Test
    public void cTestDelete() {
        entityManager.delete(dep);
        Department depById = entityManager.findById(Department.class, dep.getId());

        assertNull(depById);
    }

    @Test
    public void dFindAll() {
        List<Department> oldDeps = entityManager.findAll(Department.class);
        //add new dep
        dep.setDepartmentName("test unitar");
        dep = entityManager.insert(dep);
        List<Department> newDeps = entityManager.findAll(Department.class);

        assertEquals(oldDeps.size(), newDeps.size() - 1);
    }

}


