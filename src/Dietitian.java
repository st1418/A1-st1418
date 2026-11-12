import java.util.Set;

/**
 * Dietitian - a nutrition and diet specialist.
 * Extends HealthProfessional and adds their area of nutrition focus.
 */
public class Dietitian extends HealthProfessional {

    private final String areaOfNutrition;  // e.g., "Clinical Nutrition", "Sports Nutrition"

    /**
     * Full constructor.
     */
    public Dietitian(String id, String name, Set<Day> workingDays, String areaOfNutrition) {
        super(id, name, workingDays);
        this.areaOfNutrition = areaOfNutrition;
    }

    /**
     * Constructor with default working days (Mon-Fri).
     */
    public Dietitian(String id, String name, String areaOfNutrition) {
        super(id, name);
        this.areaOfNutrition = areaOfNutrition;
    }

    public String getAreaOfNutrition() {
        return areaOfNutrition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Dietitian]\n");
        sb.append(super.toString());
        sb.append("  Area of Nutrition: ").append(areaOfNutrition).append("\n");
        return sb.toString();
    }
}
