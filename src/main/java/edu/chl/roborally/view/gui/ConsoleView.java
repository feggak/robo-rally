package edu.chl.roborally.view.gui;

/**
 * Created by Axel Aringskog on 2015-05-12.
 *
 * This class, representing a console, will show all outputs and error made by the application.
 * It does this by redirecting System.out and System.Err to the console textArea.
 */

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsoleView extends JPanel implements ActionListener, Runnable {

    private JTextArea textArea;
    private Thread reader;
    private Thread reader2;

    private final PipedInputStream pipedInputStream=new PipedInputStream();
    private final PipedInputStream pipedInputStream2=new PipedInputStream();

    public ConsoleView() {

        /* create the swing components and add them to the panel */
        this.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.GREEN);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);

        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(315, 492);

        /* Redirection of system.out */
        try {
            PipedOutputStream pipedOutputStream = new PipedOutputStream(this.pipedInputStream);
            System.setOut(new PrintStream(pipedOutputStream, true));
        } catch (java.io.IOException io) {
            textArea.append("Couldn't redirect System.out" + io.getMessage());
            textArea.append("Error message: \n" + io.getMessage());
        } catch (SecurityException se) {
            textArea.append("Couldn't redirect System.out");
            textArea.append("Error message: \n" + se.getMessage());
        }

        /* Redirection of system.err */
        try {
            PipedOutputStream pipedOutputStream2 = new PipedOutputStream(this.pipedInputStream2);
            System.setErr(new PrintStream(pipedOutputStream2, true));
        } catch (java.io.IOException io) {
            textArea.append("Couldn't redirect System.err");
            textArea.append("Error message: \n" + io.getMessage());
        } catch (SecurityException se) {
            textArea.append("Couldn't redirect System.err");
            textArea.append("Error message: \n" + se.getMessage());
        }


      	/*
      	Starting the two threads,
      	one for System.out and one for System.err
      	*/
        reader = new Thread(this);
        reader.setDaemon(true);
        reader.start();

        reader2 = new Thread(this);
        reader2.setDaemon(true);
        reader2.start();
    }

    public synchronized void actionPerformed(ActionEvent evt)
    {
        textArea.setText("");
    }

    /* This is where the threads read from the inputStream */

    public synchronized void run() {
        try
        {
            while (Thread.currentThread() == reader)
            {
                try { 
                    this.wait(100);
                }catch(InterruptedException ie) {}
                
                if (pipedInputStream.available()!=0)
                {
                    String input = this.readLine(pipedInputStream);
                    textArea.append(input);
                }
            }

            while (Thread.currentThread()==reader2)
            {
                try { 
                    this.wait(100);
                }catch(InterruptedException ie) {}
                
                if (pipedInputStream2.available()!=0)
                {
                    String input = this.readLine(pipedInputStream2);
                    textArea.append(input);
                }
            }
        } catch (Exception e) {
            textArea.append("\n The console reports an Internal error." + " " + e.getMessage());
        }
    }

    /* Method for reading from the inputStreams */

    public synchronized String readLine(PipedInputStream pipeIn) throws IOException {
        String input = "";
        int available = pipeIn.available();
        byte b[] = new byte[available];

        input = input + new String(b,0,b.length);
        while (!input.endsWith("\n") && !input.endsWith("\r\n")) {
            available = pipeIn.available();
            
            if (available == 0){
                break;
            }
            
            b = new byte[available];
            pipeIn.read(b);
            input = input + new String(b, 0, b.length);
        }
        return input;
    }
}