package Reports;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IOMethods {

    public static void openFile(String filename){
        if(Desktop.isDesktopSupported()){
            try{
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            }catch(IOException e){
                //no application registered for PDFs
                e.printStackTrace();
            }
        }
    }
}
