import java.util.HashSet;
import java.util.Set;

/**
 * HealthProfessional - an abstract base class representing a generic health professional.
 *
 * This class captures information and behaviour common to all health professionals
 * regardless of their specialty. It is declared abstract because we should never create
 * a "plain" health professional without a specific type.
 *
 * Shared information includes:
 *  - id: a unique numeric identifier
 *  - name: the professional's full name
 *  - workingDays: the days of the week they work
 *
 * @author A1-username
 * @version 1.0
 */
public abstract class HealthProfessional {

    // Fields - private for encapsulation
    private final String id;               // numeric ID
    private final String name;             // full name
    private final Set<Day> workingDays;    // days of week they work

    /**
     * Constructor - creates a HealthProfessional with all required details.
     *
     * @param id          unique numeric identifier (must contain only digits)
     * @param name        full name of the professional
     * @param workingDays set of days the professional works each week
     * @throws IllegalArgumentException if id is invalid, or name/workingDays is null
     */
    public HealthProfessional(String id, String name, Set<Day> workingDays) {
        // Validate ID is numeric only
        if (id == null || !id.matches("\\d+")) {
            throw new IllegalArgumentException("ID must contain only numbers.");
        }
        // Validate name is not null/empty
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        // Defensive copy of workingDays to prevent external modification
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("Working days must be specified.");
        }
        this.id = id;
        this.name = name;
        this.workingDays = new HashSet<>(workingDays);
    }

    /**
     * Protected constructor for subclasses that want to provide a sensible default
     * for workingDays (e.g., Monday to Friday).
     *
     * @param id   unique numeric identifier
     * @param name full name of the professional
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, defaultWorkingDays());
    }

    /**
     * Returns a default set of working days: Monday to Friday.
     * Used as a sensible default by the protected constructor.
     *
     * @return Set of Day values from MONDAY to FRIDAY
     */
    private static Set<Day> defaultWorkingDays() {
        Set<Day> defaultDays = new HashSet<>();
        defaultDays.add(Day.MONDAY);
        defaultDays.add(Day.TUESDAY);
        defaultDays.add(Day.WEDNESDAY);
        defaultDays.add(Day.THURSDAY);
        defaultDays.add(Day.FRIDAY);
        return defaultDays;
    }

    // ---------- Getters (read-only access, encapsulation) ----------

    /**
     * @return the professional's unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return the professional's full name
     */
    public String getName() {
        return name;
    }

    /**
     * @return a copy of the professional's working days (defensive copy)
     */
    public Set<Day> getWorkingDays() {
        return new HashSet<>(workingDays);
    }

    // ---------- Object overrides ----------

    /**
     * Returns a human-readable string showing ALL details of this health professional,
     * including type-specific information from subclasses.
     * Uses polymorphism: subclasses override this to add their own details.
     *
     * @return formatted string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Name: ").append(name).append("\n");
        sb.append("  Working Days: ").append(workingDays).append("\n");
        return sb.toString();
    }

    /**
     * Two health professionals are considered equal if they have the same ID.
     * The ID is the unique identifier.
     *
     * @param obj the object to compare with
     * @return true if both are equal HealthProfessionals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HealthProfessional)) return false;
        HealthProfessional other = (HealthProfessional) obj;
        return this.id.equals(other.id);
    }

    /**
     * HashCode consistent with equals() - uses the ID as the basis.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
