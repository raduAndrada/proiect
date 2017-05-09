package medical.core.repositories;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.SecretaryService;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.PatientList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Andrada on 4/26/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@WebAppConfiguration
@Rollback(true)
public class SecretaryRepoTest {

    @Autowired
    private SecretaryService service;

    @Before
    @Rollback(true)
    public void setUp()
    {
        Patient pat = new Patient();
        pat.setName("John Doe");
        pat.setEmail("johndoe@gmail.com");
        pat.setCNP("2961019304467");
        pat.setDOB(java.sql.Date.valueOf(("1996-10-19")));

        pat.setAddress("Unknown");
        service.createPatient(pat);
    }


    @Test
    @Rollback(true)
    public void getPatientByIdTest()
    {
        Patient pat =  service.getPatientByCNP("2961019304467");
        assertNotEquals(pat,null);
    }

    @Test
    @Rollback(true)
    public void addMedicalHistoryTest()
    {
        MedicalHistory med = new MedicalHistory();
        med.setPreviousDoctor("grey");
        med.setRecommendations("none");
        med.setTestResults("none");
        med.setDiagnostic("none");
        Patient pat =  service.getPatientByCNP("2961019304467");
        assertNotEquals(service.addMedicalHistory(med,new Long(pat.getId())),null);
    }

    @Test
    @Rollback(true)
    public void getUpdatePatientTest()
    {
        Patient pat =  service.getPatientByCNP("2961019304467");
        pat.setName("Johhny");
        service.updatePatient(pat);
        pat =  service.getPatientByCNP("2961019304467");
        assertEquals(pat.getName(),"Johhny");
    }
    @Test
    @Rollback(true)
    public void getAllUsersTest()
    {
        PatientList patients = service.getAllPatients();
        assertNotEquals(patients,null);
    }



    @Test
    @Rollback(true)
    public void getAllAppointmentsTest()
    {
        AppointmentList list = service.getAllAppointments();
        assertNotEquals(list.getAppointments(),null);
    }


    @After
    @Rollback(true)
    public void tearDown()
    {
        Patient pat =  service.getPatientByCNP("2961019304467");
        service.deletePatient(pat.getId());
    }

}
