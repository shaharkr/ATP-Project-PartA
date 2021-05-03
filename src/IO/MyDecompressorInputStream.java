package IO;

import algorithms.mazeGenerators.Maze;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import static IO.byteIntOperations.byteToInt;

/**
 * this is a decorator for the input stream which adds the functionallity of decompressing mazes that were compressed
 * via MyCompressorOutputStream.
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(Object o) throws IOException {
        return (this.read(((Maze)o).toByteArray()));
    }

    /**
     * @param b the array to which we write what we read
     * @return the amount of bytes read.
     * @throws IOException
     */
    public int read(byte b[]) throws IOException{
        int index=0,row,col;
        int cat = in.read();
        while (index<24){
            b[index] = (byte)cat;
            index++;
            cat = in.read();
        }
        row = byteToInt(b[0],b[1],b[2],b[3]);
        col = byteToInt(b[4],b[5],b[6],b[7]);

        int mod = (col*row)%8;

        while(cat!=-1){
            if(index<24){
                b[index] = (byte)cat;
                index++;
            }
            else{
                Stack<Byte> tempStack;
                tempStack = numToBin(cat);
                while (!tempStack.isEmpty() && index<b.length){
                    //extract the binary bytes that represent the single byte cat, from the stack.
                    b[index]=tempStack.pop();
                    index++;
                }
            }
            cat=in.read();
        }
        return index;
    }


    /**
     * @param num an integer
     * @return a stack which contains binary digits representing the number according to the popping order.
     */
    private Stack<Byte> numToBin(int num){
        int bit=0, count =0;
        Stack<Byte> to_ret = new Stack<>();
        while(num>=1){
            bit = num%2;
            to_ret.push((byte) bit);
            num =(int)num/2;
            count++;
        }
        while(count<8){
            to_ret.push((byte) 0);
            count++;
        }
        return to_ret;
    }

}
