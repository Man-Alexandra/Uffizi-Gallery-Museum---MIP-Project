package interfaces;

import java.util.Date;

public interface VisitorActions {
    public void displayVisitors();
    public void addVisitor(int idVisitor, int idPerson, int ticketId, Date visitDate);
    public void deleteVisitor(int idVisitor);
    public void printVisitorById(int idVisitor);
    public void listVisitors();
}
