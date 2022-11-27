package good;

import java.util.List;

public class FileProcessorGood {
    public void readMultipleFiles(List<IFileProcessor> files) {
        for(int i=0; i< files.size(); i++) {
            files.get(i).read();
        }
    }
}
