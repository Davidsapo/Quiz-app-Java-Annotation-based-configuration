package entity;

public class Student {

    private String name;
    private String surname;
    public int score;

    public Student(String name, String surname, int score) {
        this(name,surname);
        this.score = score;
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname + ": " + score;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }
}
