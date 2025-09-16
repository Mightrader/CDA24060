import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> {
            Pharmacie service = new Pharmacie();
            JFrame frame = new JFrame("SPARADRAP");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(980, 600);
            frame.setLocationRelativeTo(null);

            JPanel top = new JPanel();
            JButton bAchat = new JButton("Achats");
            JButton bMed = new JButton("Médecins");
            JButton bCli = new JButton("Clients");
            JButton bMedics = new JButton("Médicaments");
            JButton bMut = new JButton("Mutuelles");
            top.add(bAchat);
            top.add(bMed);
            top.add(bCli);
            top.add(bMedics);
            top.add(bMut);
            frame.add(top, BorderLayout.NORTH);

            JPanel center = new JPanel(new BorderLayout());
            frame.add(center, BorderLayout.CENTER);

            AchatPanel achatsPanel = new AchatPanel(service);
            MedecinsPanel medPanel = new MedecinsPanel(service);
            ClientsPanel cliPanel = new ClientsPanel(service);
            MedicamentsPanel medsPanel = new MedicamentsPanel(service);
            MutuellesPanel mutPanel = new MutuellesPanel(service);
            center.add(achatsPanel, BorderLayout.CENTER);

            bAchat.addActionListener(e -> switchCenter(center, achatsPanel));
            bMed.addActionListener(e -> switchCenter(center, medPanel));
            bCli.addActionListener(e -> switchCenter(center, cliPanel));
            bMedics.addActionListener(e -> switchCenter(center, medsPanel));
            bMut.addActionListener(e -> switchCenter(center, mutPanel));

            frame.setVisible(true);
        });
    }

    private static void switchCenter(JPanel center, JPanel panel) {
        center.removeAll();
        center.add(panel, BorderLayout.CENTER);
        center.revalidate();
        center.repaint();
    }
}