package com.medicalCabinet.rest.mvc;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.DoctorService;
import com.medicalCabinet.core.service.SecretaryService;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.rest.exceptions.BadRequestException;
import com.medicalCabinet.rest.resource.*;
import com.medicalCabinet.rest.resource.asm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrada on 4/24/2017.
 */
@Controller
@RequestMapping("/rest/doctors-patients")
@PreAuthorize("hasAuthority('SECRETARY')or hasAuthority('DOCTOR')")
public class DoctorPatientController {

    @Autowired
    SecretaryService service;

    public SecretaryService getService() {
        return service;
    }

    public void setService(SecretaryService service) {
        this.service = service;
    }

    @RequestMapping("/test")
    public String test()
    {
        return "view";
    }

    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public ResponseEntity<AppointmentResource> getAppointment(@RequestParam("id") Long appointmentId) {
        Appointment app = service.getAppointmentById(appointmentId);
        if(app != null)
        {
            AppointmentResource res = new AppointmentResourceAsm().toResource(app);
            return new ResponseEntity<AppointmentResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<AppointmentResource>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/history/{historyId}", method = RequestMethod.GET)
    public ResponseEntity<MedicalHistoryResource> getMedicalHistory(@PathVariable Long historyId) {
        MedicalHistory medicalHistory = service.getMedicalHistoryById(historyId);
        if(medicalHistory !=null)
        {
            MedicalHistoryResource res = new MedicalHistoryResourceAsm().toResource(medicalHistory);
            return new ResponseEntity<MedicalHistoryResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MedicalHistoryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-appointments", method = RequestMethod.GET)
    public ResponseEntity<AppointmentListResource> getAllAppointments() {
        AppointmentList list = service.getAllAppointments();
        if(list!=null)
        {
            AppointmentListResource res = new AppointmentListResourceAsm().toResource(list);
            return new ResponseEntity<AppointmentListResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<AppointmentListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-history", method = RequestMethod.GET)
    public ResponseEntity<MedicalHistoryListResource> getAllHistory() {

        MedicalHistoryList list= service.getAllMedicalHistories();
        if(list !=null)
        {
            MedicalHistoryListResource res = new MedicalHistoryListResourceAsm().toResource(list);
            return new ResponseEntity<MedicalHistoryListResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MedicalHistoryListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-appointments-for-doctor", method = RequestMethod.GET)
    public ResponseEntity<AppointmentListResource> getDoctorsAppointmentsById (@RequestParam ("id") Long doctorId) {
        AppointmentList apps = service.getAllAppointmentsForDoctor(doctorId);
        if(apps != null)
        {
            AppointmentListResource res = new AppointmentListResourceAsm().toResource(apps);
            return new ResponseEntity<AppointmentListResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<AppointmentListResource>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/appointment/delete",method= RequestMethod.POST)
    public ResponseEntity<AppointmentResource> deleteAppointment(@RequestBody AppointmentResource sentAppointment)
    {
        Appointment app = service.deleteAppointment(sentAppointment.getRid());
        if( app!=null)
        {
            AppointmentResource res = new AppointmentResourceAsm().toResource(app);
            return new ResponseEntity<AppointmentResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AppointmentResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/appointment",method= RequestMethod.POST)
    public ResponseEntity<AppointmentResource> addAppointment(@RequestBody AppointmentResource sentAppointment)
    {
            Appointment app = service.createAppointment(sentAppointment.toAppointment());
            if (app != null) {
                AppointmentResource res = new AppointmentResourceAsm().toResource(app);
                return new ResponseEntity<AppointmentResource>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<AppointmentResource>(HttpStatus.NOT_FOUND);
            }

    }

    @RequestMapping(value="/appointment/update",method= RequestMethod.POST)
    public ResponseEntity<AppointmentResource> rescheduleAppointment(@RequestBody AppointmentResource sentAppointment)
    {
        Appointment app = service.updateAppointment(sentAppointment.getRid(),sentAppointment.toAppointment());
        if( app!=null)
        {
            AppointmentResource res = new AppointmentResourceAsm().toResource(app);
            return new ResponseEntity<AppointmentResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AppointmentResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/history",method= RequestMethod.POST)
    public ResponseEntity<MedicalHistoryResource> addMedicalHistory(@RequestBody MedicalHistoryResource sentMedicalHistory)
    {
        MedicalHistory med = service.addMedicalHistory(sentMedicalHistory.toMedicalHistory(),sentMedicalHistory.getPatient());
        if( med !=null)
        {
            MedicalHistoryResource res = new MedicalHistoryResourceAsm().toResource(med);
            return new ResponseEntity<MedicalHistoryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<MedicalHistoryResource>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{notificationId}", method = RequestMethod.GET)
    public ResponseEntity<NotificationResource> getNotification(@PathVariable Long notificationId) {
        Notification note = service.getNotificationById(notificationId);
        if(note != null)
        {
            NotificationResource res = new NotificationResourceAsm().toResource(note);
            return new ResponseEntity<NotificationResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<NotificationResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-notifications", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<NotificationListResource> getAllNotifications() {
        NotificationList list = service.getAllNotifications();
        if(list != null)
        {
            NotificationListResource res = new NotificationListResourceAsm().toResource(list);
            return new ResponseEntity<NotificationListResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<NotificationListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/notification/delete",method= RequestMethod.POST)
    public ResponseEntity<NotificationResource> deleteNotification(@RequestBody NotificationResource sentNotification)
    {
        Notification note = service.deleteNotificationById(sentNotification.getRid());
        if( note !=null)
        {
            NotificationResource res = new NotificationResourceAsm().toResource(note);
            return new ResponseEntity<NotificationResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<NotificationResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/notification",method= RequestMethod.POST)
    public ResponseEntity<NotificationResource> addNotification(@RequestBody NotificationResource sentNotification)
    {
        Notification note = service.createNotification(sentNotification.toNotification());
        if( note !=null)
        {
            NotificationResource res = new NotificationResourceAsm().toResource(note);
            return new ResponseEntity<NotificationResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<NotificationResource>(HttpStatus.NOT_FOUND);
        }
    }
}
