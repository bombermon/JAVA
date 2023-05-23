package Inteface;

import Shop.FurnitureBase;
import Shop.Sofas;
import Shop.Beds;
import Shop.Management;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameApp extends JFrame {
    private Management shopManagement;
    private JPanel panelAction;
    private JPanel panelInformation;
    private JButton buttonAdd;
    private JButton buttonDel;
    private JButton buttonFind;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTextField textFieldName;
    private JTextField textFieldColor;
    private JTextField textFieldWeight;
    private JTextField textFieldWidth;
    private JTextField textFieldLength;
    private JTextField textFieldAssembled_length;
    private JTextField textFieldMattress;
    private JTextField textFieldCost;
    private JTextField textType;
    private JButton buttonCopyAll;
    private JButton buttonCopySelect;
    private class ButtonSaveJson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(FrameApp.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                shopManagement.saveToJson(filePath);
            }
        }
    }

    private class ButtonSaveXml implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(FrameApp.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                shopManagement.saveToXml(filePath);
            }
        }
    }

    private class ButtonSaveCsv implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(FrameApp.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                shopManagement.saveToCsv(filePath);
            }
        }
    }
    public FrameApp(Management shopManagement){
        super("Система учета товаров для магазина мебели");
        this.shopManagement = shopManagement;
        panelAction = new JPanel();
        panelAction.setLayout(new FlowLayout());
        JPanel panelNew = new JPanel();
        panelNew = new JPanel();
        panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.Y_AXIS));
        buttonAdd = new JButton("Добавить запись");
        buttonAdd.addActionListener(new ButtonAdd());
        buttonDel = new JButton("Удалить запись");
        buttonDel.addActionListener(new ButtonDel());
        textField = new JTextField();
        buttonFind = new JButton("Найти");
        buttonFind.addActionListener(new ButtonFind());
        buttonCopyAll = new JButton("В буф.обмена все");
        buttonCopyAll.addActionListener(new ButtonCopyAll());
        buttonCopySelect = new JButton("В буф.обмена помеч.");
        buttonCopySelect.addActionListener(new ButtonCopySelect());
        panelNew.add(buttonAdd);
        panelNew.add(buttonDel);
        panelNew.add(textField);
        panelNew.add(buttonFind);
        panelNew.add(buttonCopyAll);
        panelNew.add(buttonCopySelect);
        panelAction.add(panelNew);
        getContentPane().add(BorderLayout.EAST, panelAction);
        model = new DefaultTableModel();
        table = new JTable(model){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        // Таблица состоит из четырех столбцов
        model.addColumn("ID");
        model.addColumn("Название");
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new MyListSelect());
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        getContentPane().add(BorderLayout.CENTER, scrollPane);
        JPanel panelInformation = new JPanel();
        JPanel panelElements = new JPanel();
        textFieldName = new JTextField(20);
        textFieldColor = new JTextField();
        textFieldWeight = new JTextField();
        textFieldWidth = new JTextField();
        textFieldLength = new JTextField();
        textFieldAssembled_length = new JTextField();
        textFieldMattress = new JTextField();
        textFieldCost = new JTextField();
        textType = new JTextField();
        JButton buttonSaveJson = new JButton("Сохранить в JSON");
        buttonSaveJson.addActionListener(new ButtonSaveJson());
        panelAction.add(buttonSaveJson);

        JButton buttonSaveXml = new JButton("Сохранить в XML");
        buttonSaveXml.addActionListener(new ButtonSaveXml());
        panelAction.add(buttonSaveXml);

        JButton buttonSaveCsv = new JButton("Сохранить в CSV");
        buttonSaveCsv.addActionListener(new ButtonSaveCsv());
        panelAction.add(buttonSaveCsv);
        GridLayout experimentLayout = new GridLayout(10,2, 5, 5);
        panelElements.setLayout(experimentLayout);
        panelElements.add(new JLabel("Название"));
        panelElements.add(textFieldName);
        panelElements.add(new JLabel("Цвет"));
        panelElements.add(textFieldColor);
        panelElements.add(new JLabel("Вес (кг)"));
        panelElements.add(textFieldWeight);
        panelElements.add(new JLabel("Ширина (см)"));
        panelElements.add(textFieldWidth);
        panelElements.add(new JLabel("Длина (см)"));
        panelElements.add(textFieldLength);
        panelElements.add(new JLabel("Длина в собр. виде (см)"));
        panelElements.add(textFieldAssembled_length);
        panelElements.add(new JLabel("Наличие матраса (да/нет)"));
        panelElements.add(textFieldMattress);
        panelElements.add(new JLabel("Тип"));
        panelElements.add(textType);
        panelElements.add(new JLabel("Цена (руб) "));
        panelElements.add(textFieldCost);

        panelInformation.setLayout(new FlowLayout());
        panelInformation.add(panelElements);
        getContentPane().add(BorderLayout.SOUTH, panelInformation);
    }


    // Отработчик события от таблицы (списка). Событие о выборе элемента таблицы
    private class MyListSelect implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1){
                int id = (Integer)model.getValueAt(ss[0], 0);
                FurnitureBase cardBase = shopManagement.getOf(id);
                if (cardBase == null)
                    return;
                textFieldName.setText(cardBase.getName());
                textFieldColor.setText(cardBase.getColor());
                textFieldWeight.setText(Float.toString(cardBase.getWeight()));
                textFieldWidth.setText(Integer.toString( cardBase.getWidth()));
                textFieldLength.setText(Integer.toString( cardBase.getLength()));
                textFieldCost.setText(Integer.toString(cardBase.getCost()));
                if (cardBase.getClass().getName() == Sofas.class.getName()){
                    textType.setText("Диван");
                    Sofas CardV = (Sofas)cardBase;
                    textFieldAssembled_length.setText(Integer.toString(CardV.getAssembled_length()));
                    textFieldMattress.setText("-");
                }
                if (cardBase.getClass().getName() == Beds.class.getName()){
                    textType.setText("Кровать");
                    Beds CardN = (Beds)cardBase;
                    textFieldMattress.setText(CardN.getMattress());
                    textFieldAssembled_length.setText("-");
                }
            }else {
                textFieldName.setText("");
                textFieldColor.setText("");
                textFieldWeight.setText("");
                textFieldWidth.setText("");
                textFieldLength.setText("");
                textType.setText("");
                textFieldAssembled_length.setText("");
                textFieldMattress.setText("");
                textFieldCost.setText("");
            }
        }
    }
    private class ButtonAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Отображаем диалоговое окно "Добавить запись"
            DialogAdd dialogAdd = new DialogAdd();
            dialogAdd.setModal(true);
            dialogAdd.setSize(600, 400);
            dialogAdd.setLocation(240,190);
            dialogAdd.setVisible(true);
            if (dialogAdd.isOk()){
                if (dialogAdd.isV()){
                    // Добавляем запись о врезных замках базу записей
                    shopManagement.add(dialogAdd.getName(), dialogAdd.getWeight(),
                            dialogAdd.getColor(), dialogAdd.getGuarantee(),
                            dialogAdd.getNumOfKeysInc(),dialogAdd.getCost(),dialogAdd.getDepth());

                    Sofas cardAdd =  (Sofas)shopManagement.getAll(shopManagement.getCount() - 1); // getCard
                    Object[] addRow = new Object[]{cardAdd.getId(), cardAdd.getName(),
                            Integer.toString(cardAdd.getWeight()), cardAdd.getColor(),
                            Integer.toString(cardAdd.getWidth()),Integer.toString(cardAdd.getLength()),
                            Integer.toString(cardAdd.getCost()), Integer.toString(cardAdd.getAssembled_length())};
                    model.addRow(addRow);
                }
                if (dialogAdd.isN()){
                    // Добавляем запись о навесных замках в базу записей
                    shopManagement.add(dialogAdd.getName(), dialogAdd.getWeight(),
                            dialogAdd.getColor(), dialogAdd.getGuarantee(), dialogAdd.getNumOfKeysInc(),
                            dialogAdd.getCost(), dialogAdd.getLoopD());
                    // Добавляем запись о навесных замках в список
                    Beds cardAdd = (Beds)shopManagement.getAll(shopManagement.getCount() - 1);
                    Object[] addRow = new Object[]{cardAdd.getId(), cardAdd.getName(),
                            Integer.toString(cardAdd.getWeight()), cardAdd.getColor(),
                            Integer.toString(cardAdd.getWidth()), Integer.toString(cardAdd.getLength()),
                            Integer.toString(cardAdd.getCost()),cardAdd.getMattress()};
                    model.addRow(addRow);
                }
            }
        }
    }
    private class ButtonDel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1){
                int idSelect = (Integer)model.getValueAt(ss[0], 0);
                model.removeRow(ss[0]);
                shopManagement.removeCard(idSelect);
            }
        }
    }
    private class ButtonFind implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textField.getText().isEmpty())
                return;
            ArrayList<FurnitureBase> cards = shopManagement.findCard(textField.getText());
            table.clearSelection();
            for (int i = 0; i < cards.size(); i++) {
                for (int j = 0; j < table.getRowCount(); j++){
                    if ((Integer)table.getValueAt(j, 0) == cards.get(i).getId()){
                        table.addRowSelectionInterval(j, j);
                    }
                }
            }
        }
    }
    // Копирует в буфер обмена список всех товаров
    private class ButtonCopyAll implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = "База магазина мебели:\n";
            for (int i = 0; i < shopManagement.getCount(); i++){
                FurnitureBase lockBase = shopManagement.getAll(i);
                str = str + lockBase.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
    // Копирует в буфер обмена список всех выделенных товаров
    private class ButtonCopySelect implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            String str = " База магазина мебели:\n";
            for (int i = 0; i < ss.length; i++){
                int id = (Integer)model.getValueAt(ss[i], 0);
                FurnitureBase cardBase = shopManagement.getOf(id);
                str = str + cardBase.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}