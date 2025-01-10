package interfaces;

public interface TicketActions {
    public void displayTickets();
    public void addTicket(String ticketId, String ticketType, boolean isValid);
    public void removeTicket(String ticketId);
    public void changeTicketType(String ticketId, String newTicketType);
    public void validateTicket(String ticketId);
    public void invalidateTicket(String ticketId);
}
