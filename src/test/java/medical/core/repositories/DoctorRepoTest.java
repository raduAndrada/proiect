package medical.core.repositories;

import com.medicalCabinet.core.service.DoctorService;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Andrada on 4/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@WebAppConfiguration
@Rollback(true)
public class DoctorRepoTest {
    @Autowired
    DoctorService service;

    @Test
    @Rollback(true)
    public void getMedicalHistoryTest()
    {
        MedicalHistoryList list = service.getMedicalHistory(new Long(30L));
        assertNotEquals(list.getMedicalHistories(),null);
    }

}
