 
package sparadrap.ui;

import sparadrap.Mutuelle;
import sparadrap.Pharmacie;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MutuellesPanel extends JPanel {
    private final Pharmacie service; private final JTable table;
    public MutuellesPanel(Pharmacie s){ this.service=s; setLayout(new BorderLayout());
        add(new JLabel("Liste des mutuelles"), BorderLayout.NORTH);
        table = new JTable(); add(new JScrollPane(table), BorderLayout.CENTER);
        JButton refresh = new JButton("Rafraîchir");
        // Recharge les données a partr de la couche métier
        refresh.addActionListener(e->load());
        add(refresh, BorderLayout.SOUTH);
        load();
    }
    private void load(){ List<Mutuelle> l = service.getMutuelles();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Nom","Taux","Ville","Téléphone"},0){
            @Override public boolean isCellEditable(int row, int column){ return false; }
        };
        for (Mutuelle d: l) m.addRow(new Object[]{d.getNom(), d.getTauxPriseEnCharge(), d.getVille(), d.getTelephone()});
        table.setModel(m);
    }
}
