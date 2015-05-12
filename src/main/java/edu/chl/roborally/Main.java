package edu.chl.roborally;

import edu.chl.roborally.controller.AppController;
import edu.chl.roborally.view.gui.ConsoleView;

import javax.swing.*;

public final class Main {

	private static String OS = null;

	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
        //new AppController();

		//new TestGameView();

        new TestConsole();
        for(int i = 0; i < 50; i ++){
            System.out.println(i);
        }
    }

	private static String getOsName() {
		if(OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	public static boolean isWindows()
	{
		return getOsName().startsWith("Windows");
	}
}
