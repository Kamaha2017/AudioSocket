import io.impl.FolderIndexer;

import java.io.File;

/**
 * Created by Elereman on 17.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        //ServerUI.main(new String[1]);
        //FolderIndexer.indexFile(new File("C:/Users/Elereman/Downloads/putty.exe"));
        FolderIndexer.indexFolder(new File("F:/Tor"));
        //Sound.playSound("C:/Users/Elereman/IdeaProjects/AudioSocket/out/production/AudioSocket/tst.wav").join();
    }
}
