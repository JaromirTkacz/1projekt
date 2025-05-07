
public class Student {
  private String name;
  private int age;
  private String lastName;

  public Student(String name, String lastName, int age) {
    this.name = name;
    this.age = age;
    this.lastName = lastName;
  }

  public String getName() { return name; }
  public String getLastName() { return lastName; }
  public int getAge() { return age; }

  @Override
  public String toString() {
    return name + " " + lastName + " " + age;
  }

  public static Student parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 3) {
      System.out.println("Błąd parsowania danych: " + str);
      return new Student("Parse Error", "Parse Error", -1);
    }
    try {
      int age = Integer.parseInt(data[2]);
      return new Student(data[0], data[1], age);
    } catch (NumberFormatException e) {
      System.out.println("Błąd konwersji wieku dla: " + str);
      return new Student("Parse Error", "Parse Error", -1);
    }
  }
}
