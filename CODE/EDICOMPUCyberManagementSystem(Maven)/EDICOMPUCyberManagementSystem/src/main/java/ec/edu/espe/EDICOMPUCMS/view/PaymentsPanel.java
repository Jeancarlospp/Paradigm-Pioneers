package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CyberManager;
import ec.edu.espe.EDICOMPUCMS.model.History;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentsPanel extends JPanel {
    private CyberManager cyberManager;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> computerFilter;
    private JSpinner startDateSpinner, endDateSpinner;
    private JButton generateReportButton, exportCSVButton;

    public PaymentsPanel(CyberManager cyberManager) {
        this.cyberManager = cyberManager;
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new FlowLayout());
        computerFilter = new JComboBox<>(getComputerList());

        // Inicializa JSpinner con SpinnerDateModel
        Calendar calendar = Calendar.getInstance();
        Date initialDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();

        SpinnerDateModel startModel = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.YEAR);
        SpinnerDateModel endModel = new SpinnerDateModel(initialDate, earliestDate, latestDate, Calendar.YEAR);

        startDateSpinner = new JSpinner(startModel);
        endDateSpinner = new JSpinner(endModel);

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startEditor);
        endDateSpinner.setEditor(endEditor);

        generateReportButton = new JButton("Generate Report");
        exportCSVButton = new JButton("Export to CSV");

        controlPanel.add(new JLabel("Computer:"));
        controlPanel.add(computerFilter);
        controlPanel.add(new JLabel("Start Date:"));
        controlPanel.add(startDateSpinner);
        controlPanel.add(new JLabel("End Date:"));
        controlPanel.add(endDateSpinner);
        controlPanel.add(generateReportButton);
        controlPanel.add(exportCSVButton);

        add(controlPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Computer ID", "Start Time", "End Time", "Duration", "Cost"}, 0);
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        generateReportButton.addActionListener(e -> generateReport());
        exportCSVButton.addActionListener(e -> exportToCSV());

        // Registrar un listener para actualizar el informe en tiempo real
        cyberManager.addHistoryUpdateListener(this::generateReport);
    }

    private String[] getComputerList() {
        String[] computers = new String[11];
        computers[0] = "All Computers";
        for (int i = 1; i <= 10; i++) {
            computers[i] = "Computer " + i;
        }
        return computers;
    }

    private void generateReport() {
        tableModel.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.##");
        double totalRevenue = 0;

        List<History> filteredHistory = cyberManager.getHistory().stream()
                .filter(this::matchesFilters)
                .collect(Collectors.toList());

        System.out.println("Filtered history size: " + filteredHistory.size()); // Debugging line

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

        for (History entry : filteredHistory) {
            System.out.println("Entry: " + entry); // Debugging line
            tableModel.addRow(new Object[]{
                    entry.getComputerId(),
                    formatter.format(entry.getStartTime()),
                    formatter.format(entry.getEndTime()),
                    formatDuration(entry.getStartTime(), entry.getEndTime()),
                    "$" + df.format(entry.getCost())
            });
            totalRevenue += entry.getCost();
        }

        tableModel.addRow(new Object[]{"Total", "", "", "", "$" + df.format(totalRevenue)});
    }

    private boolean matchesFilters(History entry) {
        int selectedComputer = computerFilter.getSelectedIndex();
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant().plusSeconds(86399); // Incluir todo el día

        boolean matchesComputer = selectedComputer == 0 || entry.getComputerId() == selectedComputer;
        boolean matchesStartDate = startDate == null || !entry.getStartTime().isBefore(startInstant);
        boolean matchesEndDate = endDate == null || !entry.getEndTime().isAfter(endInstant);

        System.out.println("matchesComputer: " + matchesComputer); // Debugging line
        System.out.println("matchesStartDate: " + matchesStartDate); // Debugging line
        System.out.println("matchesEndDate: " + matchesEndDate); // Debugging line

        return matchesComputer && matchesStartDate && matchesEndDate;
    }

    private String formatDuration(Instant start, Instant end) {
        Duration duration = Duration.between(start, end);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void exportToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.append(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        writer.append(tableModel.getValueAt(i, j).toString());
                        if (j < tableModel.getColumnCount() - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }

                writer.flush();
                JOptionPane.showMessageDialog(this, "Report exported successfully to " + filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting report: " + e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
