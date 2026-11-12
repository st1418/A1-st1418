import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AssignmentOne - main method that demonstrates the complete appointment system.
 *
 * Part 3: Creates and displays various health professional objects.
 * Part 6: Demonstrates the full appointment system end-to-end.
 *
 * @author A1-username
 * @version 1.0
 */
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  HEALTH APPOINTMENT SYSTEM - DEMONSTRATION   ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ==============================================
        // PART 3 — Working with Health Professional objects
        // ==============================================
        demonstrateProfessionals();

        // ==============================================
        // PART 6 — Working with Appointments
        // ==============================================
        demonstrateAppointments();
    }

    // ============================================================
    // PART 3 DEMONSTRATION
    // ============================================================

    private static void demonstrateProfessionals() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PART 3 — HEALTH PROFESSIONALS");
        System.out.println("═══════════════════════════════════════════════════\n");

        // --- Create working day sets ---
        Set<Day> gpDays = new HashSet<>();
        gpDays.add(Day.MONDAY); gpDays.add(Day.WEDNESDAY); gpDays.add(Day.FRIDAY);

        Set<Day> surgeonDays = new HashSet<>();
        surgeonDays.add(Day.TUESDAY); surgeonDays.add(Day.THURSDAY);

        Set<Day> specialistDays = new HashSet<>();
        specialistDays.add(Day.MONDAY); specialistDays.add(Day.TUESDAY);
        specialistDays.add(Day.THURSDAY); specialistDays.add(Day.FRIDAY);

        Set<Day> dietitianDays = new HashSet<>();
        dietitianDays.add(Day.MONDAY); dietitianDays.add(Day.TUESDAY);
        dietitianDays.add(Day.WEDNESDAY); dietitianDays.add(Day.THURSDAY); dietitianDays.add(Day.FRIDAY);

        // --- Create a mix of health professionals ---
        // Using HealthProfessional reference type (polymorphism)
        HealthProfessional drSmith = new GeneralPractitioner("1001", "Dr. Smith", gpDays,
                "123 Main Street Clinic");
        HealthProfessional drJones = new GeneralPractitioner("1002", "Dr. Jones",
                "456 Oak Avenue Clinic");  // uses default Mon-Fri
        HealthProfessional drLee = new GeneralPractitioner("1003", "Dr. Lee", gpDays,
                "789 Pine Road Medical Center");

        HealthProfessional drWang = new Specialist("2001", "Dr. Wang", specialistDays,
                "Paediatrics", "Children's Hospital");
        HealthProfessional drGarcia = new Specialist("2002", "Dr. Garcia",
                "Radiology", "City General Hospital");

        HealthProfessional drChen = new Surgeon("3001", "Dr. Chen", surgeonDays,
                "Cardiothoracic", "Heart Center Hospital");
        HealthProfessional drTanaka = new Surgeon("3002", "Dr. Tanaka", surgeonDays,
                "Orthopedic", "City General Hospital");

        HealthProfessional msBrown = new Dietitian("4001", "Ms. Brown", dietitianDays,
                "Clinical Nutrition");

        // --- Store ALL in one polymorphic list ---
        // This is polymorphism: different types treated through the common type
        HealthProfessional[] allProfessionals = {
                drSmith, drJones, drLee, drWang, drGarcia, drChen, drTanaka, msBrown
        };

        // --- Display all professionals ---
        System.out.println("=== All Health Professionals ===\n");
        for (HealthProfessional hp : allProfessionals) {
            // Each object uses its own toString() - polymorphism in action!
            System.out.println(hp.toString());
            System.out.println();
        }

        System.out.println("Total professionals: " + allProfessionals.length);
    }

    // ============================================================
    // PART 6 DEMONSTRATION
    // ============================================================

    private static void demonstrateAppointments() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  PART 6 — APPOINTMENT MANAGEMENT");
        System.out.println("═══════════════════════════════════════════════════\n");

        // --- Create appointment manager ---
        AppointmentManager manager = new AppointmentManager();

        // --- Re-create some professionals for booking ---
        Set<Day> gpDays = new HashSet<>();
        gpDays.add(Day.MONDAY); gpDays.add(Day.WEDNESDAY); gpDays.add(Day.FRIDAY);

        GeneralPractitioner drSmith = new GeneralPractitioner("1001", "Dr. Smith", gpDays,
                "123 Main Street Clinic");
        Specialist drWang = new Specialist("2001", "Dr. Wang",
                "Paediatrics", "Children's Hospital");
        Surgeon drChen = new Surgeon("3001", "Dr. Chen",
                "Cardiothoracic", "Heart Center Hospital");
        Dietitian msBrown = new Dietitian("4001", "Ms. Brown",
                "Clinical Nutrition");

        // --- Create patients ---
        Patient patient1 = new Patient("Alice Johnson", "0411222333");
        Patient patient2 = new Patient("Bob Smith", "0422333444");
        Patient patient3 = new Patient("Carol Davis", "0433444555");
        Patient patient4 = new Patient("David Wilson", "0444555666");
        Patient patient5 = new Patient("Emma Brown", "0455666777");

        // =========================================
        // SCENARIO 1: Adding appointments (normal cases)
        // =========================================
        System.out.println("--- SCENARIO 1: Adding Appointments ---\n");

        // Book several appointments across different professionals and times
        Appointment appt1 = new Appointment(patient1, drSmith, LocalTime.of(9, 0));
        Appointment appt2 = new Appointment(patient2, drWang, LocalTime.of(9, 30));
        Appointment appt3 = new Appointment(patient3, drChen, LocalTime.of(10, 0));
        Appointment appt4 = new Appointment(patient4, msBrown, LocalTime.of(10, 30));
        Appointment appt5 = new Appointment(patient5, drSmith, LocalTime.of(11, 0));
        Appointment appt6 = new Appointment(patient1, drWang, LocalTime.of(14, 0));

        System.out.println(manager.addAppointment(appt1));
        System.out.println(manager.addAppointment(appt2));
        System.out.println(manager.addAppointment(appt3));
        System.out.println(manager.addAppointment(appt4));
        System.out.println(manager.addAppointment(appt5));
        System.out.println(manager.addAppointment(appt6));

        // =========================================
        // SCENARIO 2: Error cases
        // =========================================
        System.out.println("\n--- SCENARIO 2: Error Cases ---\n");

        // Error 1: Double-booking the same professional at same time
        System.out.println("[Error] Try double-booking Dr. Smith at 09:00:");
        Appointment dupAppt = new Appointment(patient3, drSmith, LocalTime.of(9, 0));
        System.out.println("  " + manager.addAppointment(dupAppt));

        // Error 2: Double-booking Dr. Wang at 09:30
        System.out.println("[Error] Try double-booking Dr. Wang at 09:30:");
        Appointment dupAppt2 = new Appointment(patient4, drWang, LocalTime.of(9, 30));
        System.out.println("  " + manager.addAppointment(dupAppt2));

        // =========================================
        // SCENARIO 3: View ALL appointments (sorted)
        // =========================================
        System.out.println("\n--- SCENARIO 3: View ALL Appointments (Sorted by Time) ---\n");

        List<Appointment> allAppts = manager.getAllAppointments();
        System.out.println("Total: " + allAppts.size() + " appointment(s)\n");
        for (Appointment appt : allAppts) {
            System.out.println(appt);
            System.out.println();
        }

        // =========================================
        // SCENARIO 4: Filter by professional ID
        // =========================================
        System.out.println("--- SCENARIO 4: Filter by Professional ID (1001 = Dr. Smith) ---\n");

        List<Appointment> smithAppts = manager.viewByProfessionalId("1001");
        System.out.println("Found " + smithAppts.size() + " appointment(s) for Dr. Smith:\n");
        for (Appointment appt : smithAppts) {
            System.out.println(appt.getTime() + " - " + appt.getPatient().getName());
        }

        // Filter by non-existent professional
        System.out.println("\n[Error] Filter by non-existent professional ID 9999:");
        List<Appointment> emptyAppts = manager.viewByProfessionalId("9999");
        System.out.println("  Found " + emptyAppts.size() + " appointment(s)");

        // =========================================
        // SCENARIO 5: Filter by patient phone
        // =========================================
        System.out.println("\n--- SCENARIO 5: Filter by Patient Phone (0411222333 = Alice) ---\n");

        List<Appointment> aliceAppts = manager.viewByPatientPhone("0411222333");
        System.out.println("Found " + aliceAppts.size() + " appointment(s) for Alice Johnson:\n");
        for (Appointment appt : aliceAppts) {
            System.out.println("  " + appt.getTime() + " with " +
                    appt.getHealthProfessional().getName() + " [" +
                    appt.getHealthProfessional().getClass().getSimpleName() + "]");
        }

        // =========================================
        // SCENARIO 6: Cancel by professional + time
        // =========================================
        System.out.println("\n--- SCENARIO 6: Cancel Appointment by Professional + Time ---\n");

        System.out.println(manager.cancelByProfessionalAndTime("1001", LocalTime.of(11, 0)));

        // Error: Cancel non-existent
        System.out.println("\n[Error] Cancel non-existent appointment (9999 at 08:00):");
        System.out.println("  " + manager.cancelByProfessionalAndTime("9999", LocalTime.of(8, 0)));

        // =========================================
        // SCENARIO 7: Cancel by patient phone
        // =========================================
        System.out.println("\n--- SCENARIO 7: Cancel All by Patient Phone ---\n");

        // Cancel Alice's remaining appointment
        System.out.println(manager.cancelByPatientPhone("0411222333"));

        // Error: Cancel non-existent phone
        System.out.println("\n[Error] Cancel non-existent patient phone:");
        System.out.println("  " + manager.cancelByPatientPhone("0000000000"));

        // =========================================
        // SCENARIO 8: View final state after cancellations
        // =========================================
        System.out.println("\n--- SCENARIO 8: Final State After Cancellations ---\n");

        List<Appointment> finalAppts = manager.getAllAppointments();
        System.out.println("Total remaining: " + finalAppts.size() + " appointment(s)\n");
        for (Appointment appt : finalAppts) {
            System.out.println("  " + appt.getTime() + " | " +
                    appt.getPatient().getName() + " | " +
                    appt.getHealthProfessional().getName() + " [" +
                    appt.getHealthProfessional().getClass().getSimpleName() + "]");
        }

        // =========================================
        // SCENARIO 9: Show daily schedule
        // =========================================
        System.out.println("\n--- SCENARIO 9: Clinic Daily Schedule ---\n");

        List<LocalTime> schedule = manager.getDailySchedule();
        System.out.println("Clinic Hours: 08:00 - 16:00 (30-min intervals)");
        System.out.println("Total slots: " + schedule.size());
        System.out.println("Slots: " + schedule);

        // =========================================
        // SUMMARY
        // =========================================
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  DEMONSTRATION COMPLETE");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("Health Professionals created: 8 (GP, Specialist, Surgeon, Dietitian)");
        System.out.println("Appointments added: 6");
        System.out.println("Appointments remaining: " + manager.getAppointmentCount());
        System.out.println("All operations (add, filter, sort, cancel, error cases) demonstrated successfully.");
    }
}
