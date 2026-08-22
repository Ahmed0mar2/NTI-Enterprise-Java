import java.util.Scanner;

public class StudentGradeProcessor {
    public static void main(String[] args) {
        String[] names = {"Ahmed Hassan", "Layla Mohamed", "Omar Ali",
                "Fatima Khaled", "Youssef Ibrahim"};
        double[] grades = {85.5, 92.0, 58.5, 76.0, 88.5};
        int studentCount = 5;
        Scanner scanner = new Scanner(System.in);
        while (true){
        System.out.println("Enter name: ");

        System.out.println(formatStudentName(scanner.nextLine()));}
    }
    static boolean isValidGrade(double grade){
        return grade > 0 && grade < 100;
    }
    static boolean isValidName(String name){
        return name.matches("[a-zA=Z]+");
    }
    static boolean isPassingGrade(double grade){
        return grade >= 60;
    }
    static double calculateAverage(double[] grades,int count){
        double sum = 0;
        for(double grade : grades){
            sum += grade;
        }
        return sum / count;
    }
    static char getLetterGrade(double grade){
        if(grade >= 90 && grade <= 100)
            return 'A';
        else if(grade >= 80)
            return 'B';
        else if(grade >= 70)
            return 'C';
        else if (grade >= 60)
            return 'D';
        else
            return 'F';
        }
    static double calculateWeightedAverage(double exam1, double exam2, double finaExam){
        return exam1 * 0.30 + exam2 * 0.30 + finaExam * 0.40;
    }
    static int countPassingStudents(double[] grades){
        int total = 0;
        for(double grade : grades){
            if(isPassingGrade(grade))
                total++;
        }
        return total;
    }
    static String formatStudentName(String name){
        StringBuilder sb = new StringBuilder();
        char[] chars = name.toCharArray();
        for(int i = 0;i < chars.length;i++){
            if(i == 0 || chars[i - 1] == ' ') {
                sb.append(Character.toUpperCase(chars[i]));
            continue;
            }
            sb.append(chars[i]);
        }
        String result = sb.toString();
        return result;
    }
}



