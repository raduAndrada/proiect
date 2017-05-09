package com.medicalCabinet.rest.mvc;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.SecretaryService;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.PatientList;
import com.medicalCabinet.rest.resource.*;
import com.medicalCabinet.rest.resource.asm.MedicalHistoryListResourceAsm;
import com.medicalCabinet.rest.resource.asm.PatientListResourceAsm;
import com.medicalCabinet.rest.resource.asm.PatientResourceAsm;
import com.medicalCabinet.rest.resource.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by Andrada on 4/25/2017.
 */
@Controller
@RequestMapping("/rest/patients")
@PreAuthorize("hasAuthority('SECRETARY')or hasAuthority('DOCTOR')")
public class PatientController {

    @Autowired
    SecretaryService service;

    public SecretaryService getService() {
        return service;
    }

    public void setService(SecretaryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{patientId}", method = RequestMethod.GET)
    public ResponseEntity<PatientResource> getPatient(@PathVariable Long patientId) {
        Patient pat = service.getPatientById(patientId);
        if(pat != null)
        {
            PatientResource res = new PatientResourceAsm().toResource(pat);
            return new ResponseEntity<PatientResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<PatientResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/medicalHistory", method = RequestMethod.GET)
    public ResponseEntity<MedicalHistoryListResource> getPatientsMedicalHistory (@RequestParam ("id") Long patientId) {
        MedicalHistoryList med = service.getMedicalHistoryForPatient(patientId);
        if(med != null)
        {
            MedicalHistoryListResource res = new MedicalHistoryListResourceAsm().toResource(med);
            return new ResponseEntity<MedicalHistoryListResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MedicalHistoryListResource>(HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/all-patients", method = RequestMethod.GET)
    public ResponseEntity<PatientListResource> getAllPatients() {
        PatientList patientList = service.getAllPatients();
        if(patientList !=null)
        {
            PatientListResource res = new PatientListResourceAsm().toResource(patientList);
            return new ResponseEntity<PatientListResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<PatientListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PatientResource> sentPatient(
            @RequestBody PatientResource sentPatient
    ){

        Patient createdPatient = service.createPatient(sentPatient.toPatient());
        PatientResource res = new PatientResourceAsm().toResource(createdPatient);
        HttpHeaders headers =new HttpHeaders();
        headers.setLocation(URI.create(res.getLink("self").getHref()));
        return new ResponseEntity<PatientResource>(res,headers, HttpStatus.CREATED);


    }
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public ResponseEntity<PatientResource> updatePatient(@RequestBody PatientResource sentPatient)
    {
        Patient pat = service.updatePatient(sentPatient.toPatient());
        if( pat !=null)
        {
            PatientResource res = new PatientResourceAsm().toResource(pat);
            return new ResponseEntity<PatientResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PatientResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/delete",method= RequestMethod.POST)
    public ResponseEntity<PatientResource> deletePatient(@RequestBody PatientResource sentPatient)
    {
        Patient pat = service.deletePatient(sentPatient.getRid());
        if( pat !=null)
        {
            PatientResource res = new PatientResourceAsm().toResource(pat);
            return new ResponseEntity<PatientResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PatientResource>(HttpStatus.NOT_FOUND);
        }
    }


}
