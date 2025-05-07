import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.toString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line;
    while ((line = reader.readLine()) != null) {
      ret.add(Student.parse(line));
    }
    reader.close();
    return ret;
  }

  // Metoda wyszukująca wszystkich studentów o podanym imieniu
  public Collection<Student> findStudentsByName(String name) throws IOException {
    Collection<Student> students = getStudents();
    Collection<Student> result = new ArrayList<>();
    for (Student st : students) {
      if (st.getName().equalsIgnoreCase(name)) {
        result.add(st);
      }
    }
    return result;
  }
}