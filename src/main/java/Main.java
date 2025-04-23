/*
Kod bazowy programu Commit4_0:
• Program dodaje do prostej bazy danych (pliku db.txt) dane odnośnie Studentów.
• Studenci dodawani są w klasie Main.
• Wszyscy studenci są wypisywani na końcu klasy Main.
• Klasa Service obsługuje odczyt i zapis do pliku bazy danych.
• Klasa Student reprezentuje pojedynczego studenta (Imię, Wiek).
*/

import java.io.IOException;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      java.util.Scanner scanner = new java.util.Scanner(System.in);

      System.out.println("Podaj imie studenta:");
      String name = scanner.nextLine();
      System.out.println("Podaj wiek studenta:");
      int age = scanner.nextInt();

      s.addStudent(new Student(name, age));
      var students = s.getStudents();
      for(Student current : students) {
        System.out.println(current.ToString());
      }
    } catch (IOException e) {

    }
  }
}