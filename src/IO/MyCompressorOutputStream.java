package IO;

import algorithms.mazeGenerators.Maze;

import java.io.IOException;
import java.io.OutputStream;


import static IO.byteIntOperations.byteToInt;

/**
 * this class is a decorator to output stream which adds the compression functionally to the stream, given its a byte array.
 * the compression takes the bytes from 24 and forth, knowing they are binary, and treats them like bits, compressing every 8 bits
 * to one byte.
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(Object o) throws IOException {
        this.write(((Maze)o).toByteArray());
    }

    /**
     * this method writes a compressed byte array version of the byte array in param b
     * @param b byte array from which we read the maze details, and write them, compressed into out
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int index=24,count=0,to_add=0,flag=0;
        int row=byteToInt(b[0],b[1],b[2],b[3]),col=byteToInt(b[4],b[5],b[6],b[7]);
        int temp = (int)(row*col/8);
        double temp2 = (row*col/8.0);
        temp2=temp2-(double)temp;
        if(temp2>0){temp++;}
        byte[] to_ret = new byte[temp + 24];
        for (int i = 0; i < b.length; i++) {
            if(i<24){
                //first 24 are the dimensions of the maze, remain the same.
                to_ret[i] = b[i];
                continue;
            }
            if(count<8){
                //count every 8 bits to 1 byte.
                to_add+=Math.pow(2,7-count)*(int)b[i];
                //flag=0;i
            }
            else{
                to_ret[index]=(byte)to_add;
                index++;
                //flag=1;
                count=0;
                to_add =(int)Math.pow(2,7-count)*(int)b[i];
            }
            count++;
        }
        //add last byte.
        to_ret[index/*-flag*/]=(byte)to_add;
        for (int j = 0; j < to_ret.length; j++) {
            this.write(to_ret[j]);
        }
    }

}
