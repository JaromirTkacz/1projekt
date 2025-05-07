import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      boolean running = true;
      while (running) {
        System.out.println("\nWybierz opcję:");
        System.out.println("1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Znajdź studentów po imieniu");
        System.out.println("4 - Usuń studenta");
        System.out.println("5 - Zaktualizuj dane studenta");
        System.out.println("6 - Zakończ program");
        System.out.print("Twój wybór: ");
        int choice;
        try {
          choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Nieprawidłowy wybór!");
          continue;
        }

        switch (choice) {
          case 1:
            System.out.print("Podaj imię studenta: ");
            String name = scanner.nextLine();

            System.out.print("Podaj nazwisko studenta: ");
            String lastName = scanner.nextLine();

            int age = 0;
            boolean validAge = false;
            while (!validAge) {
              System.out.print("Podaj wiek studenta: ");
              try {
                age = Integer.parseInt(scanner.nextLine());
                validAge = true;
              } catch (NumberFormatException e) {
                System.out.println("Wiek musi być liczbą. Spróbuj jeszcze raz.");
              }
            }

            String birthDate = "";
            boolean validDate = false;
            while (!validDate) {
              System.out.print("Podaj datę urodzenia (DD-MM-YYYY): ");
              birthDate = scanner.nextLine();
              String[] parts = birthDate.split("-");
              if (parts.length == 3) {
                try {
                  int year = Integer.parseInt(parts[2]);
                  if (year >= 1900 && year <= 2025) {
                    validDate = true;
                  } else {
                    System.out.println("Rok musi być w przedziale 1900-2025. Spróbuj ponownie.");
                  }
                } catch (NumberFormatException e) {
                  System.out.println("Błędny format daty. Spróbuj ponownie.");
                }
              } else {
                System.out.println("Błędny format daty. Użyj formatu DD-MM-YYYY.");
              }
            }

            s.addStudent(new Student(name, lastName, age, birthDate));
            System.out.println("Dodano studenta.");
            break;

          case 2:
            Collection<Student> students = s.getStudents();
            System.out.println("Lista studentów:");
            for (Student st : students) {
              System.out.println(st);
            }
            break;

          case 3:
            System.out.print("Podaj imię studenta do wyszukania: ");
            String searchName = scanner.nextLine();
            Collection<Student> foundStudents = s.findStudentsByName(searchName);
            if (!foundStudents.isEmpty()) {
              System.out.println("Znaleziono studentów:");
              for (Student st : foundStudents) {
                System.out.println(st);
              }
            } else {
              System.out.println("Nie znaleziono studenta o imieniu: " + searchName);
            }
            break;

          case 4:
            System.out.print("Podaj imię studenta do usunięcia: ");
            String deleteName = scanner.nextLine();
            System.out.print("Podaj nazwisko studenta do usunięcia: ");
            String deleteLastName = scanner.nextLine();

            if (s.deleteStudent(deleteName, deleteLastName)) {
              System.out.println("Student został usunięty.");
            } else {
              System.out.println("Nie znaleziono studenta o podanym imieniu i nazwisku.");
            }
            break;

          case 5:
            System.out.print("Podaj imię studenta do aktualizacji: ");
            String updateName = scanner.nextLine();
            System.out.print("Podaj nazwisko studenta do aktualizacji: ");
            String updateLastName = scanner.nextLine();

            System.out.print("Podaj nowy wiek studenta: ");
            int newAge = 0;
            try {
              newAge = Integer.parseInt(scanner.nextLine());
              if (s.updateStudentAge(updateName, updateLastName, newAge)) {
                System.out.println("Dane studenta zostały zaktualizowane.");
              } else {
                System.out.println("Nie znaleziono studenta o podanym imieniu i nazwisku.");
              }
            } catch (NumberFormatException e) {
              System.out.println("Nieprawidłowy wiek!");
            }
            break;

          case 6:
            running = false;
            System.out.println("Zamykam program.");
            break;

          default:
            System.out.println("Nieznana opcja, spróbuj ponownie.");
            break;
        }
      }
      scanner.close();
    } catch (IOException e) {
      System.out.println("Wystąpił błąd podczas obsługi pliku: " + e.getMessage());
    }
  }
}