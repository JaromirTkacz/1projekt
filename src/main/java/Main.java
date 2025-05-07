import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try {
      Service s = new Service();

      while (true) {

        menu();

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
          case 1: {
            String name = imie();
            String lastname = nazwisko();
            int age = wiek();
            String urodzenie = dataUrodzenia();
            s.addStudent(new Student(name, lastname, age, urodzenie));
            System.out.println("Dodano studenta.");
            break;
          }

          case 2: {
            var students = s.getStudents();
            System.out.println("Lista studentów:");
            for (Student current : students) {
              System.out.println(current);
            }
            break;
          }

          case 3: {
            String searchName = imie();
            Student foundStudent = s.findStudentByName(searchName);
            if (foundStudent != null) {
              System.out.println("Znaleziony student:");
              System.out.println("- " + foundStudent);
            } else {
              System.out.println("Nie znaleziono studenta o imieniu: " + searchName);
            }
            break;
          }

          case 4: {
            String searchName = imie();
            String searchLastName = nazwisko();
            boolean success = s.removeStudent(searchName, searchLastName);
            if (success) {
              System.out.println("Student " + searchName + " " + searchLastName + " został usunięty.");
            } else {
              System.out.println("Nie znaleziono studenta o imieniu: " + searchName + " i nazwisku: " + searchLastName);
            }
            break;
          }
          case 5: {
            String searchName = imie();
            String searchLastName = nazwisko();
            int newAge = wiek();
            boolean success = s.updateStudentAge(searchName, searchLastName, newAge);
            if (success) {
              System.out.println("Zaktualizowano wiek studenta " + searchName + " " + searchLastName);
            } else {
              System.out.println("Nie znaleziono studenta o imieniu: " + searchName + " i nazwisku: " + searchLastName);
            }
            break;
          }
          case 6: {
            System.out.println("Program zakończony.");
            System.exit(0);
            break;
          }

          default: {
            System.out.println("Zły wybór. Wpisz ponownie.");
            break;
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Wystąpił błąd wejścia/wyjścia: " + e.getMessage());
    }
  }

  public static void menu()
  {
    System.out.println("\nWybierz opcję:");
    System.out.println("1 - Dodaj studenta");
    System.out.println("2 - Wyświetl wszystkich studentów");
    System.out.println("3 - Wyszukaj studenta po imieniu");
    System.out.println("4 - Usuń studenta po imieniu i nazwisku");
    System.out.println("5 - Zaktualizuj dane studenta");
    System.out.println("6 - Zakończ program");
    System.out.print("Co chcesz zrobic?: ");
  }

  public static String imie() {
    System.out.print("Podaj imię studenta: ");
    return scanner.nextLine();
  }

  public static String nazwisko() {
    System.out.print("Podaj nazwisko studenta: ");
    return scanner.nextLine();
  }

  public static int wiek() {
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
    return age;
  }

  public static String dataUrodzenia() {
    while (true) {
      System.out.print("Podaj datę urodzenia w formacie (dd-mm-rrrr): ");
      String date = scanner.nextLine();

      try {
        String[] parts = date.split("-");
        if (parts.length != 3) throw new Exception("Nieprawidłowy format daty.");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (day < 1 || day > 31) throw new Exception("Dzień musi być między 1 a 31.");
        if (month < 1 || month > 12) throw new Exception("Miesiąc musi być między 1 a 12.");
        if (year < 1900) throw new Exception("Rok nie może być mniejszy niż 1900.");

        return date;

      } catch (Exception e) {
        System.out.println("Błąd: " + e.getMessage());
        System.out.println("Spróbuj ponownie.");
      }
    }
  }
}