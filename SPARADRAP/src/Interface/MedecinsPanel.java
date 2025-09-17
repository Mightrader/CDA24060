 

package sparadrap.ui;

import sparadrap.Medecins;
import sparadrap.Pharmacie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MedecinsPanel extends JPanel {
    private Pharmacie service;
    private JTable table;
    public MedecinsPanel(Pharmacie s){ this.service=s;
        setLayout(new BorderLayout());
        add(new JLabel("Liste des médecins"), BorderLayout.NORTH);
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        JButton refresh = new JButton("Rafraîchir");
        // Recharge les données depuis la couche métier
        refresh.addActionListener(e->load());
        add(refresh, BorderLayout.SOUTH);
        load();
    }

    private void load(){ List<Medecins> l = service.getMedecins();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Id","Nom","Prénom","Ville","Téléphone"},0){
            @Override public boolean isCellEditable(int row, int column){ return false; }
        };
        for (Medecins d: l) m.addRow(new Object[]{d.getId(), d.getNom(), d.getPrenom(), d.getVille(), d.getTelephone()});
        table.setModel(m);
    }
}
