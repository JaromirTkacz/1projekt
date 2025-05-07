import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

  public void addStudent(Student student) throws IOException {
    try (BufferedWriter b = new BufferedWriter(new FileWriter("db.txt", true))) {
      b.write(student.toString());
      b.newLine();
    }
  }

  public ArrayList<Student> getStudents() throws IOException {
    var students = new ArrayList<Student>();
    try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        students.add(Student.parse(line));
      }
    }
    return students;
  }

  public Student findStudentByName(String name) throws IOException {
    for (Student s : getStudents()) {
      if (s.getName().equalsIgnoreCase(name)) {
        return s;
      }
    }
    return null;
  }

  public boolean updateStudentAge(String name, String lastName, int newAge) throws IOException {
    ArrayList<Student> students = getStudents();
    boolean found = false;
    
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      if (student.getName().equalsIgnoreCase(name) && student.getLastName().equalsIgnoreCase(lastName)) {
        students.set(i, new Student(name, lastName, newAge));
        found = true;
        break;
      }
    }

    if (found) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt"))) {
        for (Student student : students) {
          writer.write(student.toString());
          writer.newLine();
        }
      }
    }
    return found;
  }

  public boolean removeStudent(String name, String lastName) throws IOException {
    ArrayList<Student> students = getStudents();
    boolean found = false;
    for (Student student : students) {
      if (student.getName().equalsIgnoreCase(name) && student.getLastName().equalsIgnoreCase(lastName)) {
        students.remove(student);
        found = true;
        break;
      }
    }

    if (found) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt"))) {
        for (Student student : students) {
          writer.write(student.toString());
          writer.newLine();
        }
      }
    }
    return found;
  }
}
