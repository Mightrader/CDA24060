
package sparadrap.ui;

import sparadrap.Clients;
import sparadrap.Pharmacie;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class ClientsPanel extends JPanel {
    private final Pharmacie service; private final JTable table;
    public ClientsPanel(Pharmacie s){ this.service=s;
        setLayout(new BorderLayout());
        add(new JLabel("Liste des clients"), BorderLayout.NORTH);
        table = new JTable(); add(new JScrollPane(table), BorderLayout.CENTER);
        JButton refresh = new JButton("Rafraîchir");
        // Recharge les données a partir de la couche métier
        refresh.addActionListener(e->load());
        add(refresh, BorderLayout.SOUTH);
        load();
    }

    private void load(){ List<Clients> l = service.getClients();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Id","Nom","Prénom","Ville","Téléphone","Mutuelle"},0){
            @Override public boolean isCellEditable(int row, int column){ return false; }
        };
        for (Clients c: l) m.addRow(new Object[]{c.getId(), c.getNom(), c.getPrenom(), c.getVille(), c.getTelephone(), c.getMutuelle()==null?"":c.getMutuelle().getNom()});
        table.setModel(m);
    }
}
