import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Notepad extends Frame implements ActionListener {

    // Declare text area
    TextArea textArea;

    // Declare menu item
    MenuBar menuBar;
    Menu fileMenu;
    MenuItem openMenuItem;
    MenuItem saveMenuItem;
    MenuItem saveAsMenuItem;
    MenuItem exitMenuItem;

    // Declare file chooser
    FileDialog openFileDialog;
    FileDialog saveFileDialog;

    // Declare file
    File file;

    public Notepad()
    {
        // Set the title
        super("Editeur De Text");

        // Set the size
        setSize(800, 500);

        // Initialize text area
        textArea = new TextArea();

        // Add text area to the frame
        add(textArea);

        // Initialize menu bar
        menuBar = new MenuBar();

        // Initialize file menu
        fileMenu = new Menu("File");

        // Initialize menu items
        openMenuItem = new MenuItem("Open");
        saveMenuItem = new MenuItem("Save");
        saveAsMenuItem = new MenuItem("Save As");
        exitMenuItem = new MenuItem("Exit");

        // Add action listeners for menu items
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        saveAsMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        // Add menu items to the file menu
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Add file menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar
        setMenuBar(menuBar);

        // Initialize file dialogs
        openFileDialog = new FileDialog(this, "Open File", FileDialog.LOAD);
        saveFileDialog = new FileDialog(this, "Save File", FileDialog.SAVE);

        // Add window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        // Get the action command
        String command = e.getActionCommand();

        // Check which menu item was selected
        if (command.equals("Open")) {
            // Show the open file dialog
            openFileDialog.setVisible(true);

            // Get the selected file
            String fileName = openFileDialog.getFile();
            String directory = openFileDialog.getDirectory();
            file = new File(directory + fileName);

            // Check if a file was selected
            if (fileName != null) {
                // Read the contents of the file
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    reader.close();
                    textArea.setText(sb.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.setTitle(openFileDialog.getFile());
            }
        } else if (command.equals("Save")) {
            // Check if a file is open
            if (file != null) {
                // Save the contents of the text area to the file
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.setTitle(openFileDialog.getFile());
            } else {
                // Show the save file dialog
                saveFileDialog.setVisible(true);

                // Get the selected file
                String fileName = saveFileDialog.getFile();
                String directory = saveFileDialog.getDirectory();
                file = new File(directory + fileName);

                // Save the contents of the text area to the file
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.setTitle(openFileDialog.getFile());
            }

        } else if (command.equals("Save As")) {
            // Show the save file dialog
            saveFileDialog.setVisible(true);

            // Get the selected file
            String fileName = saveFileDialog.getFile();
            String directory = saveFileDialog.getDirectory();
            file = new File(directory + fileName);

            // Save the contents of the text area to the file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Create a new Notepad instance
        Notepad notepad = new Notepad();

        // Make the frame visible
        notepad.setVisible(true);
    }
}


