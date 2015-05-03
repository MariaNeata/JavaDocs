package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Location;

/**
 * Created by MN on 4/30/2015.
 */
public class EntityManagerImplTest {
    @Test
    public  void testFindByIdMethod(){
        EntityManagerImpl entityManager=new EntityManagerImpl();
       Location location = entityManager.findById(Location.class,1100);


    }

}

