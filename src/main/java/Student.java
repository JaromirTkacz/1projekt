public class Student {
  private String name;
  private String lastname;
  private int age;
  private String birthDate;

  public Student(String name, String lastname, int age, String birthDate) {
    this.name = name;
    this.lastname = lastname;
    this.age = age;
    this.birthDate = birthDate;
  }

  @Override
  public String toString() {
    return name + " " + lastname + ", wiek: " + age + ", data urodzenia: " + birthDate;
  }

  public String toDataString() {
    return name + "," + lastname + "," + age + "," + birthDate;
  }

  public static Student parse(String line) {
    String[] parts = line.split(",");
    if (parts.length != 4) return null;
    String name = parts[0];
    String lastname = parts[1];
    int age = Integer.parseInt(parts[2]);
    String birthDate = parts[3];
    return new Student(name, lastname, age, birthDate);
  }
}
