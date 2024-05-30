import javax.swing.JFrame; // Hauptfenster
import javax.swing.JPanel; // Für das Panel, das die Buttons enthält
import javax.swing.JButton; // Für die Memory-Karten
import javax.swing.JMenuBar; // Für die Menüleiste
import javax.swing.JMenu; // Menü
import javax.swing.JMenuItem; // Menüelement
import javax.swing.ImageIcon; // Für die Icons der Memory-Karten
import java.awt.GridLayout; // Für das Layout der Karten
import java.awt.BorderLayout; // Für das Layout des Hauptfensters
import java.awt.event.ActionEvent; // Für das ActionEvent
import java.awt.event.ActionListener; // Für das ActionListener-Interface
import java.util.ArrayList; // Für die Liste der Bilddateien
import java.util.Arrays; // Für die einfache Initialisierung der Liste
import java.util.Collections; // Für das Mischen der Bilddateien

public class MemoryGame extends JFrame
{
    // Panel zur Anzeige der Memory-Karten
    private JPanel panel;
    // Liste der Bilddateien für die Memory-Karten
    private ArrayList<String> filenames = new ArrayList<>(Arrays.asList(
            "memory/img/circle.png", "memory/img/circle.png", "memory/img/cloud.png", "memory/img/cloud.png",
            "memory/img/flower.png", "memory/img/flower.png", "memory/img/heart.png", "memory/img/heart.png",
            "memory/img/hexagon.png", "memory/img/hexagon.png", "memory/img/smiley.png", "memory/img/smiley.png",
            "memory/img/star6.png", "memory/img/star6.png", "memory/img/triangle.png", "memory/img/triangle.png"));

    // Konstruktor für das MemoryGame-Fenster
    public MemoryGame()
    {
        super("Memory Game"); // Setzt den Titel des Fensters

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm beim Schließen des Fensters

        // Erstellt ein neues Panel mit einem 4x4 GridLayout
        panel = new JPanel(new GridLayout(4, 4));
        add(panel, BorderLayout.CENTER); // Fügt das Panel in die Mitte des Fensters ein

        // Menüleiste und Menü für die Neuspiel-Option
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Datei");
        JMenuItem newMenuItem = new JMenuItem("Neu...");
        newMenuItem.addActionListener(e -> resetGame()); // Fügt einen ActionListener hinzu, der das Spiel zurücksetzt
        fileMenu.add(newMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar); // Setzt die Menüleiste des Fensters

        resetGame(); // Startet das Spiel beim Öffnen des Fensters

        pack(); // Passt die Größe des Fensters an die Inhalte an
        setLocationRelativeTo(null); // Positioniert das Fenster in der Mitte des Bildschirms
        setVisible(true); // Macht das Fenster sichtbar
    }

    // Setzt das Spiel zurück und mischt die Karten neu
    private void resetGame()
    {
        panel.removeAll(); // Entfernt alle bisherigen Karten vom Panel
        ArrayList<String> shuffledFilenames = new ArrayList<>(filenames); // Kopiert die Liste der Bilddateien
        Collections.shuffle(shuffledFilenames); // Mischt die Bilddateien zufällig

        for (String filename : shuffledFilenames)
        {
            panel.add(new MemoryButton(filename)); // Fügt für jede Bilddatei eine MemoryButton-Karte hinzu
        }
        panel.revalidate(); // Validiert das Panel neu
        panel.repaint(); // Malt das Panel neu
    }

    // Innere Klasse für die Memory-Karten
    private static class MemoryButton extends JButton implements ActionListener
    {
        private final ImageIcon backIcon = new ImageIcon("memory/img/back.png"); // Rückseite der Karte
        private final ImageIcon frontIcon; // Vorderseite der Karte
        private boolean showingBack = true; // Gibt an, ob die Rückseite der Karte gezeigt wird

        // Konstruktor für eine MemoryButton-Karte
        public MemoryButton(String filename)
        {
            super();
            frontIcon = new ImageIcon(filename); // Setzt die Vorderseite der Karte
            setIcon(backIcon); // Zeigt anfangs die Rückseite der Karte
            addActionListener(this); // Fügt einen ActionListener zur Karte hinzu
        }

        // Wechselt die angezeigte Seite der Karte beim Klicken
        public void actionPerformed(ActionEvent event)
        {
            if (showingBack)
            {
                setIcon(frontIcon); // Zeigt die Vorderseite der Karte
            }
            else
            {
                setIcon(backIcon); // Zeigt die Rückseite der Karte
            }
            showingBack = !showingBack; // Ändert den Status der Karte
        }
    }

    // Hauptmethode zum Starten des Spiels
    public static void main(String[] args)
    {
        new MemoryGame(); // Erstellt eine neue Instanz des MemoryGame-Fensters
    }
}
