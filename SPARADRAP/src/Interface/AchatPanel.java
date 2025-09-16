 

import javax.swing.*;
import java.awt.*;

public class AchatPanel extends JPanel {
    private final Pharmacie service;
    private JComboBox<Clients> cbClient;
    private JComboBox<Medecins> cbMedecin;
    private JCheckBox cbAvecMutuelle;
    private JComboBox<Mutuelle> cbMutuelle;
    private JComboBox<Medicaments> cbMedicament;
    private JSpinner spQty;

    public AchatPanel(Pharmacie s){
        this.service = s;
        setLayout(new BorderLayout());
        add(new JLabel("Saisir un achat"), BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        cbClient = new JComboBox<>(service.getClients().toArray(new Clients[0]));
        cbMedecin = new JComboBox<>(service.getMedecins().toArray(new Medecins[0]));
        cbAvecMutuelle = new JCheckBox("Avec mutuelle");
        cbMutuelle = new JComboBox<>(service.getMutuelles().toArray(new Mutuelle[0]));
        cbMutuelle.setEnabled(false);
        cbAvecMutuelle.addActionListener(e -> cbMutuelle.setEnabled(cbAvecMutuelle.isSelected()));
        cbClient.addActionListener(e -> onClientChange());
        cbMedicament = new JComboBox<>(service.getMedicaments().toArray(new Medicaments[0]));
        spQty = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));

        int y=0;
        c.gridx=0;
        c.gridy=y;
        form.add(new JLabel("Client"), c);
        c.gridx=1;
        form.add(cbClient, c); y++;
        c.gridx=0;
        c.gridy=y;
        form.add(new JLabel("Médecin"), c);
        c.gridx=1;
        form.add(cbMedecin, c); y++;
        c.gridx=0;
        c.gridy=y;
        form.add(cbAvecMutuelle, c);
        c.gridx=1;
        form.add(cbMutuelle, c); y++;
        c.gridx=0;
        c.gridy=y;
        form.add(new JLabel("Médicament"), c);
        c.gridx=1;
        form.add(cbMedicament, c); y++;
        c.gridx=0;
        c.gridy=y;
        form.add(new JLabel("Quantité"), c);
        c.gridx=1;
        form.add(spQty, c); y++;

        add(form, BorderLayout.CENTER);

        JButton valider = new JButton("Valider achat");
        valider.addActionListener(e -> onValider());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(valider);
        add(south, BorderLayout.SOUTH);
    }

    private void onValider(){
        Clients client = (Clients) cbClient.getSelectedItem();
        Medecins medecins = (Medecins) cbMedecin.getSelectedItem();
        Mutuelle mutuelle = cbAvecMutuelle.isSelected() ? (Mutuelle) cbMutuelle.getSelectedItem() : null;
        Medicaments medicaments = (Medicaments) cbMedicament.getSelectedItem();
        int qty = (Integer) spQty.getValue();

        Achat achat = service.acheter(client, medecins, mutuelle, medicaments, qty);
        if (achat == null){
            JOptionPane.showMessageDialog(this, "Achat impossible (stock insuffisant ?)", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Achat enregistré. Total: " + achat.getTotal() + "€\nStock restant: " + medicaments.getQuantite());
    }

    private void onClientChange(){
        Clients client = (Clients) cbClient.getSelectedItem();
        Mutuelle m = client != null ? client.getMutuelle() : null;
        if (m != null){
            cbAvecMutuelle.setSelected(true);
            cbMutuelle.setEnabled(false);
            cbMutuelle.setSelectedItem(m);
        } else {
            cbAvecMutuelle.setSelected(false);
            cbMutuelle.setEnabled(false);
        }
    }
}
