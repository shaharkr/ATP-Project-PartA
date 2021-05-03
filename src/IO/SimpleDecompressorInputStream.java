package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * this is a decorator for input stream which adds the functionality of decompressing of mazes.
 */
public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * @param b an array to which we will write the inputted data, after extracting it.
     * @return amount of data read.
     * @throws IOException
     */
    public int read(byte b[]) throws IOException {
        int index = 0;
        int curr=0;
        int cat = in.read();
        while(cat!=-1){
            if(index<24){
                b[index] = (byte)cat;
                index++;
            }
            else{
                while(cat>0){
                    b[index]=(byte)curr;
                    index++;
                    cat--;
                }
                curr = 1 - curr;
            }
            cat=in.read();
        }
        return index;
    }
}
