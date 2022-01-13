import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Comparator_VS_Comparable
{

    public static void main(String[] args)
    {
        // Task 1
        /**
         * Comparable interface 6.1.1:
         *  Any class that implements the comparable interface is required
         *  to have a compareTo() method, return type must be integer and
         *  parameter must be one object. If you do not specify a type in <T>
         *  after Comparable, when we call compareTo(Type, name), we will manually
         *  cast the type to desired type.
         *
         *  Interfaces are automatically public, methods in interface are not
         *  required public when we declare interface is public.
         *  Although interface cannot have instance fields, but we can declare constants and
         *  assign value to the constants, the constants in interface are public static final
         *
         *  If a subclass is going to compare with a superclass by overwriting superclass
         *  compareTo() method, we can't cast superclass to subclass and do comparison.
         *
         * Comparator interface 6.2.2:
         *  Comparator interface takes a generic type and any class that implement Comparator
         *  must also implement a compare() method, return type must be int and takes 2 parameters.
         *  It is convenient for us to alter what we want to compare. For example: we can compare
         *  student name by order, or ID, or GPA etc. Unlike Comparable, we only can have one
         *  method is loaded.
         *
         * */
        Student[] students = generateStudent();
        System.out.println("Original list without sort ");
        printWork(students);
        System.out.println("------------------------------------------------------------------ ");
        System.out.println("students in ascending gpa (first-order) and name (second-order),");
        Arrays.sort(students);
        System.out.println("Natural order sorted by GPA first, if same GPA, " +
                "natural order of name then(using comparable): ");
        printWork(students);
        System.out.println("------------------------------------------------------------------ ");
        Arrays.sort(students, new NameCompare());
        System.out.println("the sorted list of students in ascending name order(Comparator):");
        printWork(students);
        System.out.println("------------------------------------------------------------------ ");
        System.out.println("the sorted list of students in ascending GPA order(Comparator):");
        Arrays.sort(students, new GpaCompare());
        printWork(students);

    }
    public static void printWork(Student[] students)
    {
        for (int i = 0; i < students.length; i++)
        {
            System.out.println(students[i]);
        }
    }
    public static Student[] generateStudent()
    {
        Random random = new Random();
        String[] names = {"Kevin", "James", "Alice", "Anna", "Shawn"};
        Student[] students = new Student[5];

        for (int i = 0; i < students.length; i++)
        {
            students[i] = new Student(names[i], random.nextInt(2021000, 2021100),
                    random.nextDouble(0, 4));
        }
        return students;
    }
}
class GpaCompare implements  Comparator<Student>
{
    public int compare(Student o1, Student o2)
    {
        return Double.compare(o1.getGpa(),o2.getGpa());
    }
}
class NameCompare implements Comparator<Student>
{
    public int compare(Student o1, Student o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}
class Student implements Comparable<Student>
{
    int sID;
    String name;
    double gpa;

    public Student (String name, int sID, double gpa)
    {
        this.sID = sID;
        this.name = name;
        this.gpa = gpa;
    }
    public int compareTo(Student o)
    {

        int res = Double.compare(this.gpa, o.gpa);
        if (res == 0)
        {
            int res2 = this.name.compareTo(o.name);
            return res2;
        }
        return res;
    }
    public String toString()
    {
        return String.format("Student { name = %s, sID = %d, gpa = %.2f}",
                this.name, this.sID, this.gpa);
    }

    public int getsID()
    {
        return sID;
    }

    public String getName()
    {
        return name;
    }

    public double getGpa()
    {
        return gpa;
    }
}