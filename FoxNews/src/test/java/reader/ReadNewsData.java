package reader;

/**
 * Created by a on 7/16/2016.
 */
import util.DataReader;

import java.io.IOException;

public class ReadNewsData {
    DataReader dataReader=new DataReader();
    public String[] getData() throws IOException{
        String path=System.getProperty("user.dir")+"/data/searchData.xls";
        String [] news=dataReader.fileReader(path);
        return news;

    }

}
