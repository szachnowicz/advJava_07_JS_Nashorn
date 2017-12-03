package com.adbc;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TextParser extends JFrame {

    private ArrayList<File> files;

    private ScriptEngineManager engineFactory;
    private ScriptEngine engineScript;

    private File selectedFile;

    private final String filesPath = "/Users/janstepien/Downloads/java/lab01/textParser/src/";
    private JPanel panel;
    private JTextArea textArea;
    private JComboBox comboBox;
    private JButton button;
    private JEditorPane editorPane1;
    private JPanel panel1;


    public TextParser(){
        files = new ArrayList<>();
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        textArea.setText(
                "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h2>JavaScript can Change HTML</h2>\n" +
                        "\n" +
                        "<p>Hello World!</p>\n" +
                        "\n" +
                        "<p>\n" +
                        "sample tezt here" +
                        "</p>\n" +
                        "\n" +
                        "<p>The paragraph above was changed by a script.</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

        editorPane1 = new JEditorPane();
        editorPane1.setContentType("text/html");
        editorPane1.setText(textArea.getText());
        editorPane1.setOpaque(false);


        editorPane1.add(new JLabel("2"));

        panel.setLayout(new GridLayout(1,2));
        panel.add(panel1);
        panel.add(editorPane1);

        panel1.add(button);
        panel1.add(comboBox);
        panel1.add(textArea);
        loadScripts();
        initJavaScriptManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setVisible(true);




        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = comboBox.getSelectedItem().toString();

                callScript(s);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new TextParser();
        frame.setSize(new Dimension(1000, 600));
    }

    private void initJavaScriptManager() {
        engineFactory = new ScriptEngineManager();
        engineScript = engineFactory.getEngineByName("JavaScript");
    }

    private void loadScripts() {
        File folder = new File(filesPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() &&
                    listOfFiles[i].getName().contains(".js") &&
                    !listOfFiles[i].getName().equals("run.js")) {
                comboBox.addItem(listOfFiles[i].getName());
            }
        }
    }

    private void callScript(String name){
        try {
            this.engineScript.eval(Files.newBufferedReader(Paths.get(filesPath+comboBox.getSelectedItem().toString()), StandardCharsets.UTF_8));
            Invocable inv = (Invocable)this.engineScript;

            String str = textArea.getText().toString();
            inv.invokeFunction(name.substring(0, name.lastIndexOf('.')), textArea);
            editorPane1.setText(textArea.getText());

        } catch (NoSuchMethodException | ScriptException | IOException var3) {
            var3.printStackTrace();
        }
    }


}
