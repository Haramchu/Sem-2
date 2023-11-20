import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {
    // inisiasi panel frame dan label yang diperlukan
    private JFrame frame;
    private JPanel panel;
    private JLabel balanceLabel;
    private JLabel currencyLabel;

    private JButton addMoneyButton;
    private JButton purchaseButton;

    private double balance = 0.0;
    private double quantity = 0.0;
    private double price = 0.0;
    private double total = 0.0;
    private String dynamicMessage = "";

    public GUI() {
        createUI();
    }
    
    private void createUI() {
        // set title dan exit
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set panel dan layout
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        // set label dan tempatnya (grid)
        currencyLabel = new JLabel("Please select an option");
        currencyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(currencyLabel, gbc);

        balanceLabel = new JLabel("");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(balanceLabel, gbc);

        addMoneyButton = new JButton("Add Money");
        addMoneyButton.addActionListener(new AddMoneyListener());
        addMoneyButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(addMoneyButton, gbc);

        purchaseButton = new JButton("Purchase Product");
        purchaseButton.addActionListener(new PurchaseProductListener());
        purchaseButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(purchaseButton, gbc);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class AddMoneyListener implements ActionListener {
        // set button input uang
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(frame, "Enter the amount of money:", "Money Input", JOptionPane.PLAIN_MESSAGE);
            // validasi input
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount <= 0){
                        throw new NumberFormatException("");
                    }
                    balance += amount;
                    balanceLabel.setText("Rp " + balance);
                    currencyLabel.setText("Total money :");
                } catch (NumberFormatException ex) {
                    // window error input
                    JOptionPane.showMessageDialog(frame, "Maaf, jumlah uang yang anda masukkan tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class PurchaseProductListener implements ActionListener {
        // set dynamic text
        @Override
        public void actionPerformed(ActionEvent e) {
            // set dropbox dan textfield
            String[] products = {"Akua", "Fruti Apel", "Palpi Jeruk", "Neskafe Latte", "Koka kola"};
            JComboBox<String> productBox = new JComboBox<>(products);
            JTextField quantityField = new JTextField();
            JTextField priceField = new JTextField();
            JTextField totalField = new JTextField();
            totalField.setEditable(false);
            priceField.setEditable(false);
            // placeholder
            priceField.setText("Rp 5000");
            // add set price setiap dropbox diganti
            productBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selection = (String) productBox.getSelectedItem();
                    if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                        quantity = Double.valueOf(quantityField.getText());
                    }
                    else {
                        quantity = 0.0;
                    }
                    if (selection.equals("Akua")) {
                        priceField.setText("Rp 5000");
                        // set harga total apabila dropbox diganti setelah quantity ditambahkan
                        total = quantity * 5000;
                        dynamicMessage  = "Rp " + total;
                        totalField.setText(String.format(dynamicMessage));
                    } else if (selection.equals("Fruti Apel")) {
                        priceField.setText("Rp 8000");
                        total = quantity * 8000;
                        dynamicMessage  = "Rp " + total;
                        totalField.setText(String.format(dynamicMessage));
                    } else if (selection.equals("Palpi Jeruk")) {
                        priceField.setText("Rp 7500");
                        total = quantity * 7500;
                        dynamicMessage  = "Rp " + total;
                        totalField.setText(String.format(dynamicMessage));
                    } else if (selection.equals("Neskafe Latte")) {
                        priceField.setText("Rp 11000");
                        total = quantity * 11000;
                        dynamicMessage  = "Rp " + total;
                        totalField.setText(String.format(dynamicMessage));
                    } else if (selection.equals("Koka kola")) {
                        priceField.setText("Rp 9500");
                        total = quantity * 9500;
                        dynamicMessage  = "Rp " + total;
                        totalField.setText(String.format(dynamicMessage));
                    }
                } 
            });
            // add pengubahan total harga setiap quantity diketik
            quantityField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // validasi quantity
                double price = Double.parseDouble(priceField.getText().substring(3));
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    // ubah total harga
                    total = quantity * price;
                    dynamicMessage  = "Rp " + total;
                    totalField.setText(String.format(dynamicMessage));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // validasi quantity
                double price = Double.parseDouble(priceField.getText().substring(3));
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    // ubah total harga
                    total = quantity * price;
                    dynamicMessage  = "Rp " + total;
                    totalField.setText(String.format(dynamicMessage));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // validasi quantity
                double price = Double.parseDouble(priceField.getText().substring(3));
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    // ubah total harga
                    total = quantity * price;
                    dynamicMessage  = "Rp " + total;
                    totalField.setText(String.format(dynamicMessage));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }   
            });
            // add panel
            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Product:"));
            panel.add(productBox);
            panel.add(new JLabel("Quantity:"));
            panel.add(quantityField);
            panel.add(new JLabel("Price per item:"));
            panel.add(priceField);
            panel.add(new JLabel("Total price:"));
            panel.add(totalField);
            // validasi purchase
            int result = JOptionPane.showConfirmDialog(frame, panel, "Purchase Product", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    int price = Integer.parseInt(priceField.getText().substring(3));
                    int total = quantity * price;
                    String message = "Berhasil! Kembalian anda sebesar Rp " + (balance - total);
                    // set validasi error dan infobox
                    if (quantity <= 0){
                        JOptionPane.showMessageDialog(frame, "Maaf, jumlah barang yang anda masukkan tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (balance >= total) {
                        balance -= total;
                        balanceLabel.setText("Rp " + balance);
                        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog(frame, "Maaf, Uang anda tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Maaf, jumlah barang yang anda masukkan tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public static void main(String[] args) {
        new GUI();
    }
}