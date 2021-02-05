package org.ikinsure.editor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private final TextEditor view;
    private File currentFile;
    private final List<Result> indices = new ArrayList<>();
    private final CycleIterator<Result> iterator = new CycleIterator<>(indices);

    public Controller(TextEditor view) {
        this.view = view;
    }

    public void setListeners() {
        var search = view.getMenu().getSearchMenu();
        var file = view.getMenu().getFileMenu();
        var pane = view.getOptionPanel();
        pane.getOpen().addActionListener(e -> open());
        file.getOpen().addActionListener(e -> open());
        pane.getSave().addActionListener(e -> save());
        file.getSave().addActionListener(e -> save());
        pane.getSearch().addActionListener(e -> search());
        search.getStart().addActionListener(e -> search());
        pane.getNext().addActionListener(e -> next());
        search.getNext().addActionListener(e -> next());
        pane.getPrevious().addActionListener(e -> prev());
        search.getPrevious().addActionListener(e -> prev());
        search.getRegex().addActionListener(e -> regex());
        file.getExit().addActionListener(e -> System.exit(0));
    }

    private void regex() {
        boolean regex = view.getOptionPanel().getRegex().isSelected();
        view.getOptionPanel().getRegex().setSelected(!regex);
    }

    private void search() {
        String regex = view.getOptionPanel().getConsole().getText();
        if (!regex.isEmpty()) {
            String data = view.getTextPanel().getArea().getText();
            indices.clear();
            iterator.reset();
            if (view.getOptionPanel().getRegex().isSelected()) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(data);
                while (matcher.find()) {
                    indices.add(new Result(matcher.start(), matcher.end()));
                }
            } else {
                int index = 0;
                while ((index = data.indexOf(regex, index)) != -1) {
                    indices.add(new Result(index, index + regex.length()));
                    index++;
                }
            }
            next();
        }
    }

    private void open() {
        int result = view.getChooser().showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = view.getChooser().getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                StringBuilder builder = new StringBuilder();
                int sign;
                while ((sign = reader.read()) != -1) {
                    builder.append((char) sign);
                }
                view.getTextPanel().getArea().setText(builder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                view.getTextPanel().getArea().setText("");
            }
        }
    }

    private void save() {
        String data = view.getTextPanel().getArea().getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void next() {
        Result i = iterator.next();
        if (i != null) {
            showResult(i);
        }
    }

    private void prev() {
        Result i = iterator.prev();
        if (i != null) {
            showResult(i);
        }
    }

    private void showResult(Result result) {
        JTextArea area = view.getTextPanel().getArea();
        area.setCaretPosition(result.getStart());
        area.select(result.getStart(), result.getEnd());
        area.grabFocus();
    }
}
