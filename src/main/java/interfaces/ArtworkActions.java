package interfaces;
import java.sql.Connection;

public interface ArtworkActions {
    void addArtworkToDatabase(Connection connection);
    void exhibit(String title);
    void removeFromExhibit(String title);
    void removeArtwork(String title);
    void changeTitle(String oldTitle, String newTitle);
    void changeArtist(String title, String newArtist);
    void changeYear(String title, int newYear);
    void changeDescription(String title, String newDescription);
    void printNonExhibitedArtworks();
    void printArtworkDetailsByTitle(String titleToSearch);
    void displayArtworks();
    void ListArtworks();
}

