import java.time.LocalTime;

/**
 * Appointment - represents a single appointment linking a patient to a
 * health professional at a specific time.
 *
 * An Appointment:
 *   - works with ANY type of HealthProfessional (polymorphism via the base class)
 *   - uses LocalTime for time, which is naturally comparable for sorting
 *   - implements Comparable so Collections.sort() can order by time
 *   - ensures it can never exist in an incomplete state (all-or-nothing constructor)
 */
public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional healthProfessional;
    private final LocalTime time;

    /**
     * Constructor - creates a valid, complete appointment.
     *
     * @param patient             the patient booking the appointment
     * @param healthProfessional  any type of health professional (GP, Specialist, etc.)
     * @param time                the appointment time slot
     * @throws IllegalArgumentException if any parameter is null
     */
    public Appointment(Patient patient, HealthProfessional healthProfessional, LocalTime time) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (healthProfessional == null) {
            throw new IllegalArgumentException("Health professional cannot be null.");
        }
        if (time == null) {
            throw new IllegalArgumentException("Appointment time cannot be null.");
        }
        this.patient = patient;
        this.healthProfessional = healthProfessional;
        this.time = time;
    }

    // ---------- Getters ----------

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHealthProfessional() {
        return healthProfessional;
    }

    public LocalTime getTime() {
        return time;
    }

    // ---------- Comparable ----------

    /**
     * Compares appointments by time (earliest first).
     * This allows Collections.sort() to order appointments chronologically.
     *
     * @param other another appointment
     * @return negative if this is earlier, positive if later, 0 if same time
     */
    @Override
    public int compareTo(Appointment other) {
        return this.time.compareTo(other.time);
    }

    // ---------- Object overrides ----------

    /**
     * Full details including patient info, professional info, and time.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("Appointment Time: ").append(time).append("\n");
        sb.append("----------------------------------------\n");
        sb.append(patient.toString()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append(healthProfessional.toString());
        sb.append("========================================");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Appointment)) return false;
        Appointment other = (Appointment) obj;
        return this.patient.equals(other.patient)
            && this.healthProfessional.equals(other.healthProfessional)
            && this.time.equals(other.time);
    }

    @Override
    public int hashCode() {
        return patient.hashCode() + healthProfessional.hashCode() + time.hashCode();
    }
}
