package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;

public class ModeloTabelaParaPintarCedula extends DefaultTableCellRenderer {

    private static final long   serialVersionUID    = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color corCedula = Color.WHITE;
        Object text = table.getValueAt(row, 0);
        LocalDateTime localDateTime = LocalDateTime.now();
        String horarioEmString = table.getValueAt(row, 0).toString();
        String[] horasSeparadas= horarioEmString.split(":");
        int horas = Integer.parseInt(horasSeparadas[0]);
        int minutos = Integer.parseInt(horasSeparadas[1]);

        if(localDateTime.getHour()-horas > 1 || localDateTime.getMinute() - minutos > 10 )
            corCedula = Color.RED;

        label.setBackground(corCedula);
        return label;
    }
}
