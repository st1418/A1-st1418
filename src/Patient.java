/**
 * Patient - represents a patient booking an appointment.
 * Stores basic patient details needed by the appointment system.
 *
 * Design note: Patient is its own class (not just a String in Appointment)
 * because:
 *   - Encapsulation: keeps patient data together
 *   - Reusability: Patient can be used in multiple places
 *   - Extensibility: easy to add more patient fields later
 */
public class Patient {

    private final String name;          // full name
    private final String mobileNumber;  // contact phone

    /**
     * Constructor - creates a Patient with required details.
     *
     * @param name         patient's full name (cannot be empty)
     * @param mobileNumber patient's mobile phone number (cannot be empty)
     * @throws IllegalArgumentException if name or mobileNumber is empty
     */
    public Patient(String name, String mobileNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new IllegalArgumentException("Mobile number cannot be empty.");
        }
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " (Mobile: " + mobileNumber + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient)) return false;
        Patient other = (Patient) obj;
        return this.name.equals(other.name) && this.mobileNumber.equals(other.mobileNumber);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + mobileNumber.hashCode();
    }
}
