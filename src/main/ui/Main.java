/*
 * Citation: code obtained from JsonSerializationDemo
 *           URL: https:// github.students.cs.ubc.ca/CPSC210/JsonSerializationsDemo.git
 */


package ui;

import java.io.FileNotFoundException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            new TaskListApp();
        } catch (FileNotFoundException e) {
            // pass
        }
    }
}
