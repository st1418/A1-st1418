/**
 * Appointment - represents a single appointment linking a patient to a
 * health professional at a specific time.
 *
 * An Appointment:
 *   - works with ANY type of HealthProfessional (polymorphism via the base class)
 *   - stores time as an int (minutes since midnight), which is naturally comparable
 *   - implements Comparable so Collections.sort() can order by time
 *   - ensures it can never exist in an incomplete state (all-or-nothing constructor)
 */
public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional healthProfessional;
    private final int timeInMinutes;   // e.g. 09:00 = 540, 16:00 = 960

    /**
     * Constructor - creates a valid, complete appointment.
     *
     * @param patient             the patient booking the appointment
     * @param healthProfessional  any type of health professional (GP, Specialist, etc.)
     * @param timeInMinutes       the appointment time in minutes since midnight (0-1439)
     * @throws IllegalArgumentException if any parameter is null/invalid
     */
    public Appointment(Patient patient, HealthProfessional healthProfessional, int timeInMinutes) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (healthProfessional == null) {
            throw new IllegalArgumentException("Health professional cannot be null.");
        }
        if (timeInMinutes < 0 || timeInMinutes >= 1440) {
            throw new IllegalArgumentException("Time must be between 0 and 1439 minutes.");
        }
        this.patient = patient;
        this.healthProfessional = healthProfessional;
        this.timeInMinutes = timeInMinutes;
    }

    // ---------- Getters ----------

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHealthProfessional() {
        return healthProfessional;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    /**
     * Returns the appointment time formatted as "HH:MM".
     *
     * @return formatted time string, e.g. "09:00"
     */
    public String getTimeFormatted() {
        int hours = timeInMinutes / 60;
        int minutes = timeInMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
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
        return this.timeInMinutes - other.timeInMinutes;
    }

    // ---------- Object overrides ----------

    /**
     * Full details including patient info, professional info, and time.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("Appointment Time: ").append(getTimeFormatted()).append("\n");
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
            && this.timeInMinutes == other.timeInMinutes;
    }

    @Override
    public int hashCode() {
        return patient.hashCode() + healthProfessional.hashCode() + Integer.hashCode(timeInMinutes);
    }
}
