package com.example.invoicecreatorservice.objects.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Getter
@Setter
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String expirationDate;
    private UUID token;

    public UserSession(int userId){
        this.userId = userId;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.add(Calendar.MINUTE, 40);      // adds 40 minutes
        this.expirationDate = dateFormat.format(cal.getTime());

        this.token = UUID.randomUUID();
    }

    public UserSession() {}
}
