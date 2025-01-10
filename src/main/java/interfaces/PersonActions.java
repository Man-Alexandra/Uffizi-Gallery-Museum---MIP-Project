package interfaces;

public interface PersonActions {
    public void printPersonByFirstName(String firstName, String secondName);
    public void addPerson(String firstName, String secondName, int age, String gender, String email, String phoneNumber);
    public void removePerson(String firstName, String secondName);
    public void changeFirstName(String oldFirstName, String newFirstName);
    public void changeLastName(String oldLastName, String newLastName);
    public void changeAge(String firstName, String lastName, int newAge);
    public void changeEmail(String firstName, String lastName, String newEmail);
    public void changeGender(String firstName, String lastName, String newGender);
    public void changePhoneNumber(String firstName, String lastName, String phoneNumber);
    public void printAllPersons();
}
