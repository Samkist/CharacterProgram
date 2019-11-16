/* Created By: Sam Pizette
 * On Date: 11/12/2019
 * Project Name: Characters
 */
package me.Samkist.Character;

import BreezySwing.GBFrame;

import javax.swing.*;

public class CharacterGUI extends GBFrame {
    private static JFrame frame = new CharacterGUI();
    private JTextField inputField = addTextField("", 1,1 ,2 ,1);
    private JButton inputButton = addButton("Input", 2, 1, 1, 1);
    private JButton resetButton = addButton("Reset", 2, 2, 1,1);
    private JButton exitButton = addButton("Exit", 3, 1, 2, 1);
    private JTextArea outputField = addTextArea("Words: \n", 6,0,2,2);
    private JTextArea outputField2 = addTextArea("Characters: \n", 8, 0, 2, 2);

    public static void main(String[] args) {
        frame.setSize(400, 400);
        frame.setTitle("Parser Program");
        frame.setVisible(true);
    }

    public CharacterGUI() {
        outputField.setEditable(false);
        outputField2.setEditable(false);
    }

    public JTextArea getOutputField() {
        return outputField;
    }

    public void clearFields() {
        outputField.setText("Words: \n");
        outputField2.setText("Characters: \n");
    }

    public void addCharOccurrence(String s) {
        String curText = outputField2.getText();
        outputField2.setText(curText + s + "\n");
    }

    public void addWordOccurrence(String s) {
        String curText = outputField.getText();
        outputField.setText(curText + s + "\n");
    }

    public void buttonClicked(JButton jButton) {
        if(jButton.equals(inputButton)) {
            new Parser(inputField.getText(), this);
            inputField.grabFocus();
        }
        if(jButton.equals(resetButton)) {
            inputField.setText("");
            clearFields();;
            inputField.grabFocus();
        }
        if(jButton.equals(exitButton)) {
            System.exit(0);
        }
    }
}
