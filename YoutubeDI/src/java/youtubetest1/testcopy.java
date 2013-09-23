/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetest1;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.awt.Desktop;
import java.net.URI;

/**
 *
 * @author amungen
 */
public class testcopy {

    public static void main(String[] args) {
testcopy t = new testcopy();
t.tarayicipencereac();

    }

    public void tarayicipencereac() {
        try {


            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://www.example.com"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void copyclipboard() {
        try {


            String myString = "This text will be copied into clipboard when running this code!";
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
