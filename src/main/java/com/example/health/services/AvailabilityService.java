package com.example.health.services;

import com.example.health.entities.Availability;
import com.example.health.entities.AvailabilityDoc;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AvailabilityService {
    public boolean existAvailabilityBetween(Availability availability, LocalDate startDate, LocalDate endDate) {
        List<AvailabilityDoc> availabilityDocs = availability.getAvailabilityDocs();
        for (AvailabilityDoc availabilityDoc : availabilityDocs) {
            LocalDate startDatePossible = availabilityDoc.getAvailability().getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endOfDatePossible = availabilityDoc.getAvailability().getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (startDatePossible.isBefore(startDate) && endDate.isAfter(startDate) ||
                    (endOfDatePossible.isAfter(startDate) && startDatePossible.isBefore(endDate)) ||
                    (startDatePossible.isEqual(startDate) || endOfDatePossible.isEqual(endDate))) {
                return true;
            }
        }
        return false;
    }
}
