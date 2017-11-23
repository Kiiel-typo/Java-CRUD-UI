package ui;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Theme {
	

	public static void setLookAndFeel() {
		try {
            UIManager.put("TableHeader.foreground", Color.white);
            
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if ("Windows Classic".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			
					} catch (Exception e) {}
		
	}
}
