package com.example.health.services;

import com.example.health.dtos.PatientRequestDTO;
import com.example.health.entities.Patient;
import com.example.health.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

package com.example.health.services;

import com.example.health.dtos.PatientRequestDTO;
import com.example.health.entities.Appointment;
import com.example.health.entities.Patient;
import com.example.health.repositories.PatientRepository;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    @Transactional
    public Patient createPatient (PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        return patientRepository.save(patient);
    }
    public void sendWhatsAppReminder(Patient patient, Appointment appointment) {
        String messageBody = "Reminder: You have an appointment with Dr. " + appointment.getDoctor().getName() + "on"
                + appointment.getStartDate().toString();

        Message message = Message.creator(
                new PhoneNumber("whatsapp" + patient.getPhoneNo()),
                new PhoneNumber("whatsapp: +14155238886"),
                messageBody).create();
    }




}
