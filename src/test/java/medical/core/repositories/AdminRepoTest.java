package medical.core.repositories;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.repositories.AdminServiceRepo;
import com.medicalCabinet.core.service.AdminService;
import com.medicalCabinet.core.service.util.UserList;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Andrada on 4/25/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@WebAppConfiguration
@Rollback(true)
public class AdminRepoTest {

    @Autowired
    private AdminService service;

    @Before
    @Rollback(true)
    public void setUp()
    {
        User user= new User();
        user.setUsername("grey123");
        user.setSecretary(false);
        user.setPassword("1234");
        user.setName("meredith grey");
        user.setEmail("meredithgrey@yahoo.com");
        user.setDoctor(true);
        user.setAddress("Seattle");
        user.setAdmin(false);
        service.createUser(user);
    }

    @Test
    @Rollback(true)
    public void createUserTest()
    {
        User user = new User();
        user.setUsername("grey1234");
        user.setSecretary(false);
        user.setPassword("1234");
        user.setName("meredith grey");
        user.setEmail("meredithgrey@yahoo1.com");
        user.setDoctor(true);
        user.setAddress("Seattle");
        user.setAdmin(false);
        service.createUser(user);
        User usr = service.findUserByUsername("grey1234");
        assertNotEquals(usr,null);
    }

    @Test
    @Rollback(true)
    public void createDoctorTest()
    {
        Doctor doc = new Doctor();
        doc.setSpeciality("General surgeon");
        User usr=service.findUserByUsername("grey123");
        Doctor doct= service.createDoctor(usr.getId(),doc);
        assertNotEquals(doct,null);

    }


    @Test
    public void getUserByUsernameTest()
    {
        User usr =  service.findUserByUsername("grey123");
        assertNotEquals(usr,null);
    }
    @Test
    @Rollback(true)
    public void deleteUserTest()
    {
        User user = new User();
        user.setUsername("grey12345");
        user.setSecretary(false);
        user.setPassword("1234");
        user.setName("meredith grey");
        user.setEmail("meredithgrey1@yahoo.com");
        user.setDoctor(true);
        user.setAddress("Seattle");
        user.setAdmin(false);
        service.createUser(user);
        User usr =  service.findUserByUsername("grey12345");
        service.deleteUser(usr.getId());
        usr = service.findUserByUsername("grey12345");
        assertEquals(usr,null);
    }

    @Test
    @Rollback(true)
    public void getAllUsersTest()
    {
        UserList users = service.getAllUsers();
        assertNotEquals(users,null);
    }

    @After
    @Rollback(true)
    public void tearDown()
    {
        service.deleteUser(service.findUserByUsername("grey123").getId());
    }
}
