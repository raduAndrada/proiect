package com.medicalCabinet.rest.mvc;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.AdminService;
import com.medicalCabinet.core.service.util.UserList;
import com.medicalCabinet.rest.exceptions.ForbiddenException;
import com.medicalCabinet.rest.resource.DoctorListResource;
import com.medicalCabinet.rest.resource.DoctorResource;
import com.medicalCabinet.rest.resource.UserListResource;
import com.medicalCabinet.rest.resource.UserResource;
import com.medicalCabinet.rest.resource.asm.UserListResourceAsm;
import com.medicalCabinet.rest.resource.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Andrada on 4/25/2017.
 */
@Controller
@RequestMapping("/rest/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    AdminService service;

    public AdminService getService() {
        return service;
    }

    public void setService(AdminService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getUser(@PathVariable Long userId) {
        User usr = service.findUserById(userId);
        if(usr != null)
        {
            UserResource res = new UserResourceAsm().toResource(usr);
            return new ResponseEntity<UserResource>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<UserListResource> getAllUsers() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
        }
        UserList list= service.getAllUsers();
        if(list != null)
        {
            UserListResource res = new UserListResourceAsm().toResource(list);
            return new ResponseEntity<UserListResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<UserListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> sentUser(
            @RequestBody UserResource sentUser
    ){
            User usr = sentUser.toUser();
            User createdUser = service.createUser(usr);
            if (sentUser.isDoctor()){
                User user = service.findUserByUsername(sentUser.getUsername());
                Doctor doc = new Doctor();
                doc.setSpeciality(sentUser.getSpeciality());
                service.createDoctor(user.getId(),doc);
            }
            UserResource res = new UserResourceAsm().toResource(createdUser);
            HttpHeaders headers =new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<UserResource>(res,headers, HttpStatus.CREATED);

    }
    @RequestMapping(value="/delete",method= RequestMethod.POST)
    public ResponseEntity<UserResource> deleteUser(@RequestBody UserResource sentUser)
    {
                User user = service.deleteUser(sentUser.getRid());
                if( user!=null)
                {
                UserResource res = new UserResourceAsm().toResource(user);
                return new ResponseEntity<UserResource>(res, HttpStatus.OK);
                } else {
                    return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
                }
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    public ResponseEntity<UserResource> updateRegularUser( @RequestBody UserResource sentUser)
    {

        User user = service.updateUser(sentUser.getRid() ,sentUser.toUser());
        if (user != null) {
            UserResource res = new UserResourceAsm().toResource(user);
            return new ResponseEntity<UserResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(value="/update/patient",method= RequestMethod.POST)
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<UserResource> updatePatientUser( @RequestBody UserResource sentUser)
    {

        User user = service.updateUserByUsername(sentUser.getUsername() ,sentUser.toUser());
        if (user != null) {
            UserResource res = new UserResourceAsm().toResource(user);
            return new ResponseEntity<UserResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
        }

    }



    @RequestMapping(value="/valid" ,method= RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<UserListResource> findUsersByUsernameAndPassword(@RequestParam(value="username", required = false) String username, @RequestParam(value="password", required = false) String password)
    {
        UserList users = service.getAllUsers();
        User usr = service.findUserByUsername(username);
        if (usr != null)
        {
            if(password != null) {
                if(usr.getPassword().equals(password)) users = new UserList();
                users.setUsers(new ArrayList<>());
                users.getUsers().add(usr);
            }else{
                return null;
            }
            if(usr.isPatient())
            {
                Patient pat = service.findPatientByUsername(usr.getUsername());

            }
            UserListResource res = new UserListResourceAsm().toResource(users);
            return new ResponseEntity<UserListResource> (res,HttpStatus.OK);
        }
        else return new ResponseEntity<UserListResource>(HttpStatus.NOT_FOUND);

    }

}
