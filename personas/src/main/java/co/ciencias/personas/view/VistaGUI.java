package co.ciencias.personas.view;

import co.ciencias.personas.controller.Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class VistaGUI extends JFrame {

    private Controller controller;

    // Tabla
    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    // Campos del formulario
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtHeight;

    // Filtro
    private JTextField txtFilter;

    // Botones
    private JButton btnAdd;
    private JButton btnSortByAge;
    private JButton btnFilter;
    private JButton btnClearFilter;

    public VistaGUI(Controller controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Directorio de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 1),
                "Agregar Persona",
                0, 0,
                new Font("SansSerif", Font.BOLD, 12),
                new Color(60, 60, 60)));
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(12);
        panelForm.add(txtName, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        panelForm.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 3;
        txtAge = new JTextField(5);
        panelForm.add(txtAge, gbc);

        gbc.gridx = 4; gbc.gridy = 0;
        panelForm.add(new JLabel("Altura (m):"), gbc);
        gbc.gridx = 5;
        txtHeight = new JTextField(5);
        panelForm.add(txtHeight, gbc);

        gbc.gridx = 6; gbc.gridy = 0;
        btnAdd = new JButton("Agregar");
        styleButton(btnAdd, new Color(70, 130, 180));
        panelForm.add(btnAdd, gbc);

        add(panelForm, BorderLayout.NORTH);

        String[] columns = {"Nombre", "Edad", "Altura (m)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setGridColor(new Color(220, 220, 220));

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelBottom.setBackground(new Color(235, 240, 248));
        panelBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));

        panelBottom.add(new JLabel("Filtrar por nombre:"));
        txtFilter = new JTextField(14);
        panelBottom.add(txtFilter);

        btnFilter = new JButton("Buscar");
        styleButton(btnFilter, new Color(90, 160, 100));
        panelBottom.add(btnFilter);

        btnClearFilter = new JButton("Limpiar");
        styleButton(btnClearFilter, new Color(160, 100, 90));
        panelBottom.add(btnClearFilter);

        panelBottom.add(Box.createHorizontalStrut(20));
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(1, 28));
        panelBottom.add(sep);
        panelBottom.add(Box.createHorizontalStrut(10));

        btnSortByAge = new JButton("Ordenar por edad ↑");
        styleButton(btnSortByAge, new Color(120, 80, 180));
        panelBottom.add(btnSortByAge);

        add(panelBottom, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> onAgregar());
        btnFilter.addActionListener(e -> onFiltrar());
        btnClearFilter.addActionListener(e -> onLimpiarFiltro());
        btnSortByAge.addActionListener(e -> onOrdenarPorEdad());

        txtFilter.addActionListener(e -> onFiltrar());
    }


    private void onAgregar() {
        String nombre = txtName.getText().trim();
        String edadStr = txtAge.getText().trim();
        String alturaStr = txtHeight.getText().trim();

        if (nombre.isEmpty() || edadStr.isEmpty() || alturaStr.isEmpty()) {
            mostrarError("Por favor completa todos los campos.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            double altura = Double.parseDouble(alturaStr);
            controller.ingresarPersona(nombre, edad, altura);
        } catch (NumberFormatException ex) {
            mostrarError("Edad debe ser un número entero y Altura un número decimal.");
        }
    }

    private void onFiltrar() {
        String texto = txtFilter.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0));
        }
    }

    private void onLimpiarFiltro() {
        txtFilter.setText("");
        sorter.setRowFilter(null);
    }

    private void onOrdenarPorEdad() {
        controller.ordenarPorEdad();
    }


    public void agregarFilaTabla(String nombre, int edad, double altura) {
        tableModel.addRow(new Object[]{nombre, edad, altura});
    }

    public void limpiarTabla() {
        tableModel.setRowCount(0);
    }

    public void mostrarMensaje(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarFormulario() {
        txtName.setText("");
        txtAge.setText("");
        txtHeight.setText("");
        txtName.requestFocus();
    }


    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }
}