import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Service {
  private List<Student> students;
  private final String FILE_NAME = "students.txt";

  public Service() throws IOException {
    students = new ArrayList<>();
    loadFromFile();
  }

  public void addStudent(Student s) {
    students.add(s);
  }

  public List<Student> getStudents() {
    return students;
  }

  public void saveToFile() throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
    for (Student s : students) {
      writer.write(s.toDataString());
      writer.newLine();
    }
    writer.close();
  }

  private void loadFromFile() throws IOException {
    File file = new File(FILE_NAME);
    if (!file.exists()) {
      return;
    }

    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
      Student s = Student.parse(line);
      if (s != null) {
        students.add(s);
      }
    }
    reader.close();
  }

  public List<Student> findStudentByName(String name) {
    List<Student> results = new ArrayList<>();
    for (Student s : students) {
      if (s != null && s.getName().equalsIgnoreCase(name)) {
        results.add(s);
      }
    }
    return results;
  }
}
