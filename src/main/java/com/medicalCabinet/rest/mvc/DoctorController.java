package com.medicalCabinet.rest.mvc;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.service.DoctorService;
import com.medicalCabinet.core.service.util.DoctorList;
import com.medicalCabinet.rest.exceptions.ConflictException;
import com.medicalCabinet.rest.resource.AppointmentResource;
import com.medicalCabinet.rest.resource.DoctorListResource;
import com.medicalCabinet.rest.resource.DoctorResource;
import com.medicalCabinet.rest.resource.NotificationResource;
import com.medicalCabinet.rest.resource.asm.DoctorListResourceAsm;
import com.medicalCabinet.rest.resource.asm.DoctorResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

/**
 * Created by Andrada on 4/25/2017.
 */
@Controller
@RequestMapping("/rest/doctors")
@PreAuthorize("hasAuthority('SECRETARY')or hasAuthority('DOCTOR')")
public class DoctorController {

    @Autowired
    private DoctorService service ;

    public DoctorService getService() {
        return service;
    }

    public void setService(DoctorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{doctorId}", method = RequestMethod.GET)
    public ResponseEntity<DoctorResource> getDoctor(@PathVariable Long doctorId) {
        Doctor doc = service.getDoctorById(doctorId);
        if(doc != null)
        {
            DoctorResource res = new DoctorResourceAsm().toResource(doc);
            return new ResponseEntity<DoctorResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<DoctorResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-doctors", method = RequestMethod.GET)
    public ResponseEntity<DoctorListResource> getAllDoctors() {
        DoctorList docs = service.getAllDoctors();
        if (docs != null)
        {
            DoctorListResource res = new DoctorListResourceAsm().toResource(docs);
            return new ResponseEntity<DoctorListResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<DoctorListResource>(HttpStatus.NOT_FOUND);
        }

    }




}
