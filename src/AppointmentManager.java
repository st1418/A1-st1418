import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AppointmentManager - manages the collection of all appointments in the system.
 *
 * Responsibilities:
 *   - Store appointments in an ArrayList (dynamic, flexible size)
 *   - Prevent double-booking (same professional at same time)
 *   - Filter appointments by professional ID or patient phone
 *   - Sort appointments by time using Collections.sort() + Comparable
 *   - Cancel appointments by professional+time or by patient phone
 *   - Return meaningful success/failure messages
 *
 * Daily schedule: fixed time slots from 08:00 to 16:00 in 30-minute intervals.
 */
public class AppointmentManager {

    // The collection of all appointments
    private final List<Appointment> appointments;

    // Fixed daily schedule: 08:00 - 16:00 in 30-minute slots
    private static final LocalTime SCHEDULE_START = LocalTime.of(8, 0);
    private static final LocalTime SCHEDULE_END = LocalTime.of(16, 0);
    private static final int SLOT_MINUTES = 30;

    /**
     * Constructor - initializes an empty appointment manager.
     */
    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // ========== ADD ==========

    /**
     * Adds a new appointment to the collection.
     * Rejects if the same professional already has a booking at the same time (double-booking).
     *
     * @param appointment the new appointment to add
     * @return success message, or error message if the appointment cannot be added
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "FAIL: Cannot add null appointment.";
        }

        // Check for double-booking: same professional + same time
        for (Appointment existing : appointments) {
            boolean sameProfessional = existing.getHealthProfessional().equals(
                    appointment.getHealthProfessional());
            boolean sameTime = existing.getTime().equals(appointment.getTime());
            if (sameProfessional && sameTime) {
                return "FAIL: This professional is already booked at " + appointment.getTime() + ".";
            }
        }

        appointments.add(appointment);
        return "SUCCESS: Appointment added for " + appointment.getPatient().getName() +
               " at " + appointment.getTime() + " with " +
               appointment.getHealthProfessional().getName() + ".";
    }

    // ========== VIEW / FILTER ==========

    /**
     * Returns ALL appointments sorted by time (earliest first).
     *
     * @return sorted copy of the appointments list
     */
    public List<Appointment> getAllAppointments() {
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Returns appointments filtered by health professional ID.
     *
     * @param professionalId the ID to filter by
     * @return list of matching appointments (sorted)
     */
    public List<Appointment> viewByProfessionalId(String professionalId) {
        List<Appointment> filtered = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getHealthProfessional().getId().equals(professionalId)) {
                filtered.add(appt);
            }
        }
        Collections.sort(filtered);
        return filtered;
    }

    /**
     * Returns appointments filtered by patient phone number.
     *
     * @param phoneNumber the phone number to filter by
     * @return list of matching appointments (sorted)
     */
    public List<Appointment> viewByPatientPhone(String phoneNumber) {
        List<Appointment> filtered = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getPatient().getMobileNumber().equals(phoneNumber)) {
                filtered.add(appt);
            }
        }
        Collections.sort(filtered);
        return filtered;
    }

    // ========== CANCEL ==========

    /**
     * Cancels an appointment identified by professional ID + appointment time.
     *
     * @param professionalId the professional's ID
     * @param time           the appointment time slot
     * @return success or failure message
     */
    public String cancelByProfessionalAndTime(String professionalId, LocalTime time) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appt = appointments.get(i);
            boolean matchId = appt.getHealthProfessional().getId().equals(professionalId);
            boolean matchTime = appt.getTime().equals(time);
            if (matchId && matchTime) {
                appointments.remove(i);
                return "SUCCESS: Cancelled appointment for " + appt.getPatient().getName() +
                       " with " + appt.getHealthProfessional().getName() + " at " + time + ".";
            }
        }
        return "FAIL: No appointment found for professional ID " + professionalId +
               " at " + time + ".";
    }

    /**
     * Cancels ALL appointments for a patient with the given phone number.
     *
     * @param phoneNumber the patient's mobile number
     * @return success or failure message
     */
    public String cancelByPatientPhone(String phoneNumber) {
        int removed = 0;
        for (int i = appointments.size() - 1; i >= 0; i--) {
            if (appointments.get(i).getPatient().getMobileNumber().equals(phoneNumber)) {
                appointments.remove(i);
                removed++;
            }
        }
        if (removed > 0) {
            return "SUCCESS: Cancelled " + removed + " appointment(s) for patient " + phoneNumber + ".";
        }
        return "FAIL: No appointments found for patient " + phoneNumber + ".";
    }

    // ========== SCHEDULE HELPERS ==========

    /**
     * Returns the fixed daily schedule slots (08:00, 08:30, 09:00, ..., 16:00).
     *
     * @return list of LocalTime slots
     */
    public List<LocalTime> getDailySchedule() {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime current = SCHEDULE_START;
        while (!current.isAfter(SCHEDULE_END)) {
            slots.add(current);
            current = current.plusMinutes(SLOT_MINUTES);
        }
        return slots;
    }

    /**
     * @return total number of appointments currently in the system
     */
    public int getAppointmentCount() {
        return appointments.size();
    }
}
