package Inteface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DialogAdd extends JDialog {
    private JTextField textFieldName;

    private JTextField textFieldColor;

    private JTextField textFieldWeight;

    private JTextField textFieldWidth;

    private JTextField textFieldLength;

    private JTextField textFieldAssembled_length;
    private JTextField textFieldMattress;
    private JTextField textFieldCost;
    private JRadioButton radioButtonV;
    private JRadioButton radioButtonN;
    private JButton buttonOk;
    private JButton buttonCancel;
    private boolean isOk;
    public DialogAdd(){
        isOk = false;
        // Создаем панель, на которую разместим все элементы интерфейса
        JPanel panel = new JPanel();

        textFieldName = new JTextField(20);
        textFieldColor = new JTextField();
        textFieldWeight = new JTextField();
        textFieldWidth = new JTextField();
        textFieldLength = new JTextField();
        textFieldAssembled_length = new JTextField();
        textFieldMattress = new JTextField();
        textFieldCost = new JTextField();
        radioButtonV = new JRadioButton("Диван");
        radioButtonV.addActionListener(new RadioButtonV());
        radioButtonN = new JRadioButton("Кровать");
        radioButtonN.addActionListener(new RadioButtonN());
        buttonOk = new JButton("Добавить");
        buttonOk.addActionListener(new ButtonOk());
        buttonCancel = new JButton("Отмена");
        buttonCancel.addActionListener(new ButtonCancel());
        // Устанавливаем компоновщик GridLayout для панели
        GridLayout experimentLayout = new GridLayout(10,2, 5, 5);
        panel.setLayout(experimentLayout);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panel);
        // Располагаем на панели элементы интерфейса
        panel.add(new JLabel("Название"));
        panel.add(textFieldName);
        panel.add(new JLabel("Цвет"));
        panel.add(textFieldColor);
        panel.add(new JLabel("Вес (кг)"));
        panel.add(textFieldWeight);
        panel.add(new JLabel("Ширина (cм)"));
        panel.add(textFieldWidth);
        panel.add(new JLabel("Длина (cм)"));
        panel.add(textFieldLength);
        panel.add(new JLabel("Длина в собр. (cм)"));
        panel.add(textFieldAssembled_length);
        panel.add(new JLabel("Наличие матраса (да/нет)"));
        panel.add(textFieldMattress);
        panel.add(new JLabel("Цена (руб)"));
        panel.add(textFieldCost);

        panel.add(radioButtonV);
        panel.add(radioButtonN);
        panel.add(buttonOk);
        panel.add(buttonCancel);
        textFieldAssembled_length.setEnabled(false);
        textFieldMattress.setEnabled(false);
    }
    // Обработчик события от кнопки "Добавить"
    private class ButtonOk implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            isOk = true;
            // Проверяем, не содержат ли поля с цифрами символы
            try {
                if (radioButtonN.isSelected()) {
                    Integer.parseInt(textFieldWeight.getText());
                    Integer.parseInt(textFieldWidth.getText());
                    Integer.parseInt(textFieldLength.getText());
                    Integer.parseInt(textFieldCost.getText());
                    dispose();
                }
            }catch (NumberFormatException ex){
                System.out.println("*");
                JOptionPane.showMessageDialog(getRootPane(),
                        "Поля должны содержать число");
            }
            try {
                if (radioButtonV.isSelected()) {
                    Integer.parseInt(textFieldWeight.getText());
                    Integer.parseInt(textFieldWidth.getText());
                    Integer.parseInt(textFieldLength.getText());
                    Integer.parseInt(textFieldAssembled_length.getText());
                    Integer.parseInt(textFieldCost.getText());
                    dispose();
                }
            }catch (NumberFormatException ex){
                System.out.println("*");
                JOptionPane.showMessageDialog(getRootPane(),
                        "Неверный формат ввода!");
            }
        }
    }
    // Обработчик события от кнопки "Отмена"
    private class ButtonCancel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            isOk = false;
            dispose();
        }
    }
    // Обработчик события от кнопки "диван"
    private class RadioButtonV implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonV.setSelected(true);
            radioButtonN.setSelected(false);
            textFieldAssembled_length.setEnabled(true);
            textFieldMattress.setEnabled(false);
        }
    }
    // Обработчик события кнопки "кровать"
    private class RadioButtonN implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonV.setSelected(false);
            radioButtonN.setSelected(true);
            textFieldAssembled_length.setEnabled(false);
            textFieldMattress.setEnabled(true);
        }
    }
    // Методы-геттеры для получения информации о добавленном издании
    public boolean isOk(){
        return isOk;
    }
    public String getName(){
        return textFieldName.getText();
    }
    public String getColor(){
        return textFieldColor.getText();
    }
    public int getWeight(){
        return Integer.parseInt(textFieldWeight.getText());
    }
    public int getGuarantee(){
        return Integer.parseInt(textFieldWidth.getText());
    }
    public int getNumOfKeysInc(){
        return Integer.parseInt(textFieldLength.getText());
    }
    public int getDepth(){
        return Integer.parseInt(textFieldAssembled_length.getText());
    }
    public String getLoopD(){
        return textFieldMattress.getText();
    }
    public int getCost(){
        return Integer.parseInt(textFieldCost.getText());
    }
    public boolean isN(){
        if (radioButtonN.isSelected())
            return true;
        else
            return false;
    }
    public boolean isV(){
        if (radioButtonV.isSelected())
            return true;
        else
            return false;
    }
}
