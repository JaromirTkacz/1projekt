public class Student {
  private String name;
  private int age;
  private String lastName;
  private String birthDate;

  public Student(String name, String lastName, int age, String birthDate) {
    this.name = name;
    this.age = age;
    this.lastName = lastName;
    this.birthDate = birthDate;
  }

  public String getName() { return name; }
  public String getLastName() { return lastName; }
  public int getAge() { return age; }
  public String getBirthDate() { return birthDate; }

  @Override
  public String toString() {
    return name + " " + lastName + " " + age + " " + birthDate;
  }

  public static Student parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 4) {
      System.out.println("Błąd parsowania danych: " + str);
      return new Student("Parse Error", "Parse Error", -1, "Parse Error");
    }
    try {
      int age = Integer.parseInt(data[2]);
      return new Student(data[0], data[1], age, data[3]);
    } catch (NumberFormatException e) {
      System.out.println("Błąd konwersji wieku dla: " + str);
      return new Student("Parse Error", "Parse Error", -1, "Parse Error");
    }
  }
}