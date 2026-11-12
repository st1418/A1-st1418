import java.util.Set;

/**
 * Surgeon - a medical surgeon who performs operations.
 * Extends HealthProfessional and adds surgical specialty and hospital info.
 */
public class Surgeon extends HealthProfessional {

    private final String surgicalSpecialty;   // e.g., "Cardiothoracic", "Orthopedic"
    private final String operatingHospital;   // where they perform surgeries

    /**
     * Full constructor.
     */
    public Surgeon(String id, String name, Set<Day> workingDays,
                   String surgicalSpecialty, String operatingHospital) {
        super(id, name, workingDays);
        this.surgicalSpecialty = surgicalSpecialty;
        this.operatingHospital = operatingHospital;
    }

    /**
     * Constructor with default working days (Mon-Fri).
     */
    public Surgeon(String id, String name, String surgicalSpecialty, String operatingHospital) {
        super(id, name);
        this.surgicalSpecialty = surgicalSpecialty;
        this.operatingHospital = operatingHospital;
    }

    public String getSurgicalSpecialty() {
        return surgicalSpecialty;
    }

    public String getOperatingHospital() {
        return operatingHospital;
    }

    /**
     * Surgeons can perform surgeries - a behaviour unique to this type.
     *
     * @return a description of the surgery being performed
     */
    public String performSurgery(String patientName) {
        return "Surgeon " + getName() + " (" + surgicalSpecialty + ") is performing surgery on " + patientName +
               " at " + operatingHospital + ".";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Surgeon]\n");
        sb.append(super.toString());
        sb.append("  Surgical Specialty: ").append(surgicalSpecialty).append("\n");
        sb.append("  Operating Hospital: ").append(operatingHospital).append("\n");
        return sb.toString();
    }
}
