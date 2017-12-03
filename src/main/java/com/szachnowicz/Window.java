package com.szachnowicz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class Window extends JFrame {
    private JEditorPane orginalHtml;
    private JEditorPane formatedHtml;
    private JComboBox<String> jComboBox;
    private JButton buttonRun;
    private ScriptEngine engineScript;


    public static final String HTML = "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h2>JavaScript can Change HTML</h2>\n" +
            "\n" +
            "<p>Hello World!</p>\n" +
            "\n" +
            "<p>\n" +
            "sample example test " +
            "</p>\n" +
            "\n" +
            "<p>The paragraph above was changed by a script.</p>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    private JEditorPane editorPane1;


    public Window() {
        this.setup();
    }

    private void initJavaScriptManager() {
        ScriptEngineManager engineFactory = new ScriptEngineManager();
        engineScript = engineFactory.getEngineByName("JavaScript");
    }

    private void setup() {


        initJavaScriptManager();
        this.setSize(600, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("JavaScript");

        this.setLayout(new GridLayout(3, 2, 20, 20));

        orginalHtml = new JEditorPane();
        formatedHtml = new JEditorPane();


        orginalHtml.setContentType("text/html");
        orginalHtml.setText(HTML);
        orginalHtml.setOpaque(false);

        formatedHtml.setContentType("text/html");
        formatedHtml.setOpaque(false);


        FileManager manager = new FileManager();
        final List<String> allJsRecources = manager.getAllJsRecources();

        jComboBox = new JComboBox<String>(
                allJsRecources.toArray(new String[allJsRecources.size()])
        );


        buttonRun = new JButton("Run");
        buttonRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = jComboBox.getSelectedItem().toString();
                callScript(selected);
            }
        });

        this.add(new JLabel("Input:"));
        this.add(orginalHtml);
        this.add(new JLabel("Output:"));
        this.add(formatedHtml);
        this.add(jComboBox);
        this.add(buttonRun);
        this.setVisible(true);
    }

    private void callScript(String name) {
        try {
            this.engineScript.eval(Files.newBufferedReader(Paths.get(name), StandardCharsets.UTF_8));
            Invocable inv = (Invocable) this.engineScript;

            String str = orginalHtml.getText().toString();
            final String functionName = name.substring(name.lastIndexOf("/") + 1, name.indexOf(".js"));
            formatedHtml.setText(str);
            inv.invokeFunction(functionName, formatedHtml);

        } catch (NoSuchMethodException | ScriptException | IOException var3) {
            var3.printStackTrace();
        }
    }

}
