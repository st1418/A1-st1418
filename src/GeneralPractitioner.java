import java.util.Set;

/**
 * GeneralPractitioner (GP) - a family doctor / general practitioner.
 * Extends HealthProfessional and adds clinic location information.
 */
public class GeneralPractitioner extends HealthProfessional {

    private final String clinicLocation;

    /**
     * Full constructor.
     *
     * @param id             numeric ID
     * @param name           full name
     * @param workingDays    days of week worked
     * @param clinicLocation the GP's primary clinic location
     */
    public GeneralPractitioner(String id, String name, Set<Day> workingDays, String clinicLocation) {
        super(id, name, workingDays);
        this.clinicLocation = clinicLocation;
    }

    /**
     * Constructor using default working days (Mon-Fri).
     */
    public GeneralPractitioner(String id, String name, String clinicLocation) {
        super(id, name);  // uses Mon-Fri default
        this.clinicLocation = clinicLocation;
    }

    /**
     * @return the GP's clinic location
     */
    public String getClinicLocation() {
        return clinicLocation;
    }

    /**
     * Overrides toString() to include type-specific details.
     * Shows both the shared info from the parent and the GP-specific info.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[General Practitioner]\n");
        sb.append(super.toString());  // parent: ID, Name, Working Days
        sb.append("  Clinic Location: ").append(clinicLocation).append("\n");
        return sb.toString();
    }
}
