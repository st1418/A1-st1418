import java.util.Set;

/**
 * Specialist - a medical specialist (e.g., paediatrician, radiologist, cardiologist).
 * Extends HealthProfessional and adds specialty field and hospital affiliation.
 */
public class Specialist extends HealthProfessional {

    private final String specialtyField;       // e.g., "Paediatrics", "Radiology"
    private final String hospitalAffiliation;  // which hospital they're affiliated with

    /**
     * Full constructor.
     */
    public Specialist(String id, String name, Set<Day> workingDays,
                      String specialtyField, String hospitalAffiliation) {
        super(id, name, workingDays);
        this.specialtyField = specialtyField;
        this.hospitalAffiliation = hospitalAffiliation;
    }

    /**
     * Constructor with default working days (Mon-Fri).
     */
    public Specialist(String id, String name, String specialtyField, String hospitalAffiliation) {
        super(id, name);
        this.specialtyField = specialtyField;
        this.hospitalAffiliation = hospitalAffiliation;
    }

    public String getSpecialtyField() {
        return specialtyField;
    }

    public String getHospitalAffiliation() {
        return hospitalAffiliation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Specialist]\n");
        sb.append(super.toString());
        sb.append("  Specialty: ").append(specialtyField).append("\n");
        sb.append("  Hospital: ").append(hospitalAffiliation).append("\n");
        return sb.toString();
    }
}
