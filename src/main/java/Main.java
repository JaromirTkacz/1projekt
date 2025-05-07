import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      boolean tak = true;
      while(tak){
        System.out.println("\nWybierz opcję:");
        System.out.println("1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Zakończ");
        System.out.print("Twój wybór: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
          case 1:
            System.out.print("Podaj imię studenta: ");
            String name = scanner.nextLine();

            System.out.print("Podaj nazwisko studenta: ");
            String lastname = scanner.nextLine();

            int age = 0;
            boolean validAge = false;
            while (!validAge) {
              System.out.print("Podaj wiek studenta: ");
              try {
                age = Integer.parseInt(scanner.nextLine());
                validAge = true;
              } catch (NumberFormatException e) {
                System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
              }
            }

            String birthDate = "";
            boolean validDate = false;
            while (!validDate) {
              System.out.print("Podaj datę urodzenia studenta (RRRR-MM-DD): ");
              birthDate = scanner.nextLine();
              try {
                LocalDate.parse(birthDate); // sprawdza poprawność formatu
                validDate = true;
              } catch (DateTimeParseException e) {
                System.out.println("❌ Niepoprawny format daty. Spróbuj ponownie.");
              }
            }

            s.addStudent(new Student(name, lastname, age, birthDate));
            System.out.println("✅ Dodano studenta.");
            break;

          case 2:
            var students = s.getStudents();
            System.out.println("📋 Lista studentów:");
            for (Student current : students) {
              System.out.println(current.toString());
            }
            break;

          case 3:
            tak = false;
            s.saveToFile();
            System.out.println("👋 Zakończono program.");
            break;

          default:
            System.out.println("Nieznana opcja. Wybierz ponownie.");
        }
      }

      scanner.close();
    } catch (IOException e) {
      System.out.println("Wystąpił błąd: " + e.getMessage());
    }
  }
}
