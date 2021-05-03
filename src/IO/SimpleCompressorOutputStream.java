package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

/**
 * this class is a decorator for output stream which adds a functionality of compressing the output.
 */
public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * @param b an array that holds maze's details.
     * we will compress the details and send them forward via the out stream.
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        Stack<Integer> stackI = new Stack<>();
        Stack<Byte> stackB = new Stack<>();
        int count=0;
        int last;
        last = 0; //last one seen
        for (int i = 24; i < b.length; i++) {
            //we continue the count because the but hasn't changed.
            if((int)b[i]==last)
                count+=1;
            else{
                //push the amount of consecutive 1's or 0's seen and initiate the count.
                stackI.push(count);
                last=1-last;
                count=1;
            }
        }
        stackI.push(count);
        while(!stackI.isEmpty()){
            int curr = stackI.pop();
            while(curr>255){
                //make larger than 255 int's become byte-0-byte-0..
                stackB.push((byte)255);
                stackB.push((byte)0);
                curr-=255;
            }
            stackB.push((byte)curr);
        }
        //size will be known because of the stack.
        byte[] to_ret = new byte[24+stackB.size()];
        for (int i = 0; i < to_ret.length; i++) {
            if(i<24) //first 24 are rows,cols,start,goal..
                to_ret[i] = b[i];
            else
                to_ret[i]=stackB.pop();
        }
        for (int i = 0; i < to_ret.length; i++) {
            this.write(to_ret[i]);
        }
    }
}
