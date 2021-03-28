package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.applet.AudioClip;
import java.net.URL;
import java.net.URI;

import model.Task;
import model.TaskList;
import persistence.JsonReader;
import persistence.JsonWriter;

public class GUI extends JFrame {
    private JPanel panel;
    private URI uri;
    private URL url;
    private File file;
    private JTable table;
    private Task task;
    private TaskList taskList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner scanner;
    private JTextField textFieldGoal;
    private JTextField textFieldDate;
    private JTextField textFieldStatus;
    private JTextField textFieldNumOfF;
    private JTextField textFieldNumOfUnF;
    private String[] columns = {"Goal", "Date", "Status"};
    private static final String JSON_STORE = "./data/tasklist.json";
    private JLabel goalLabel = null;
    private JLabel dateLabel = null;
    private JLabel statusLabel = null;
    private JLabel numOfF = null;
    private JLabel numOfUnF = null;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Task List");
        setLocationRelativeTo(null);
        setBounds(500, 500, 850, 600);
        panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);
        taskList = new TaskList(20200320);
        scanner = new Scanner(System.in);
        labels();
        buttons();
        panelScroll();
        listeners();
        getInput();
    }

    private void buttons() {
        bt1();
        bt2();
        bt3();
        bt4();
        bt5();
        bt6();
        bt7();
        bt8();
    }

    private void listeners() {
        listenerThree();
        listenerTwo();
        listenerOne();
    }

    private void labels() {
        label1();
        label2();
        label3();
        label4();
        label5();
    }

    private void panelScroll() {
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(20, 130, 800, 400);
        panel.add(scrollPane1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane1.setViewportView(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    public void addInfo() {
        String content = textFieldGoal.getText();
        String date = textFieldDate.getText();
        String complete = textFieldStatus.getText();
        int inputDate = Integer.parseInt(date);
        Task p = new Task(content, inputDate, complete);
        taskList.addTask(p);
        getInput();
        textFieldGoal.setText("Goal: ");
        textFieldDate.setText("Date: ");
        textFieldStatus.setText("Status: ");

    }


    public void getInput() {
        Object[][] tableValues = new Object[taskList.getTasks().size()][];
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task p1 = taskList.getTasks().get(i);
            Object[] ff = {p1.getGoal(), p1.getDate(), p1.getStatus()};
            tableValues[i] = ff;
        }
        table.setModel(new DefaultTableModel(tableValues, columns));
    }

    private void label1() {
        goalLabel = new JLabel("Goal");
        goalLabel.setBounds(10, 20, 30, 25);
        panel.add(goalLabel);
        textFieldGoal = new JTextField();
        textFieldGoal.setBounds(100, 20, 100, 25);
        panel.add(textFieldGoal);
        textFieldGoal.setColumns(10);
    }

    private void label2() {
        dateLabel = new JLabel("Date");
        dateLabel.setBounds(210, 20, 30, 25);
        panel.add(dateLabel);
        textFieldDate = new JTextField();
        textFieldDate.setBounds(250, 20, 100, 25);
        panel.add(textFieldDate);
        textFieldDate.setColumns(10);
    }

    private void label3() {
        statusLabel = new JLabel("Status");
        statusLabel.setBounds(370, 20, 80, 25);
        panel.add(statusLabel);
        textFieldStatus = new JTextField();
        textFieldStatus.setBounds(460, 20, 80, 25);
        panel.add(textFieldStatus);
        textFieldStatus.setColumns(10);
    }

    private void label4() {
        numOfF = new JLabel("# of F");
        numOfF.setBounds(560, 20, 30, 25);
        panel.add(numOfF);
        textFieldNumOfF = new JTextField();
        textFieldNumOfF.setBounds(600, 20, 70, 25);
        panel.add(textFieldNumOfF);
        textFieldNumOfF.setColumns(10);
    }

    private void label5() {
        numOfUnF = new JLabel("# of UnF");
        numOfUnF.setBounds(680, 20, 30, 25);
        panel.add(numOfUnF);
        textFieldNumOfUnF = new JTextField();
        textFieldNumOfUnF.setBounds(720, 20, 70, 25);
        panel.add(textFieldNumOfUnF);
        textFieldNumOfUnF.setColumns(10);
    }

    private void bt1() {
        JButton addTaskButton = new JButton("Add");
        addTaskButton.setBounds(1, 80, 90, 25);
        addTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addInfo();
            }
        });
        panel.add(addTaskButton);
    }

    private void bt2() {
        JButton deleteTaskButton = new JButton("Delete");
        deleteTaskButton.setBounds(100, 80, 80, 25);
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int rows = table.getSelectedRows().length;
                if (row != -1) {
                    for (int i = 0; i <= rows; i++) {
                        taskList.deleteTask(row);
                    }
                }
                getInput();
            }
        });
        panel.add(deleteTaskButton);
    }

    private void bt3() {
        JButton markTaskButton = new JButton("Mark");
        markTaskButton.setBounds(190, 80, 80, 25);
        markTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int rows = table.getSelectedRows().length;
                if (row != -1) {
                    for (int i = 0; i <= rows; i++) {
                        taskList.markIndexTask(row);
                    }
                }
                getInput();
            }
        });
        panel.add(markTaskButton);
    }

    private void bt4() {
        JButton numberOfFinishedTaskButton = new JButton("# of Finished");
        numberOfFinishedTaskButton.setBounds(280, 80, 120, 25);
        numberOfFinishedTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskList.numberOfFinishedTask();
            }
        });
        panel.add(numberOfFinishedTaskButton);
    }

    private void bt5() {
        JButton numberOfUnFinishedTaskButton = new JButton("# of Unfinished");
        numberOfUnFinishedTaskButton.setBounds(410, 80, 120, 25);
        numberOfUnFinishedTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskList.numberOfUnFinishedTask();
            }
        });
        panel.add(numberOfUnFinishedTaskButton);
    }

    private void bt6() {
        JButton playMusicButton = new JButton("Music");
        playMusicButton.setBounds(540, 80, 80, 25);
        playMusicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playMusic();
            }
        });
        panel.add(playMusicButton);
    }

    private void playMusic() {
        try {
            file = new File("DELACEY-Dream-It-Possible-_Official-Audio_-_320-kbps_.wav");
            uri = file.toURI();
            url = uri.toURL();
            AudioClip music = Applet.newAudioClip(url);
            music.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bt7() {
        JButton saveTaskButton = new JButton("Save");
        saveTaskButton.setBounds(630, 80, 80, 25);
        saveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTask();
            }
        });
        panel.add(saveTaskButton);
    }

    private void saveTask() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(taskList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + JSON_STORE);
        }

    }

    private void bt8() {
        JButton loadTaskButton = new JButton("Load");
        loadTaskButton.setBounds(720, 80, 80, 25);
        loadTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTask();
            }
        });
        panel.add(loadTaskButton);
    }

    private void loadTask() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        try {
            taskList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from " + JSON_STORE);
        }

        getInput();
    }

    private void listenerOne() {
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
            }


            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {


            }
        });
    }

    private void listenerTwo() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                textFieldGoal.setText("");
                textFieldDate.setText("");
                textFieldGoal.setEnabled(true);//Activate the button
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    private void listenerThree() {
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                int row = table.getSelectedRow();
                task = taskList.getTasks().get(row);
                if (row != -1) {
                    textFieldGoal.setText(task.getGoal());
                    textFieldDate.setText(task.getDate() + "");
                    textFieldStatus.setText(task.getStatus() + "");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI frame = new GUI();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}


