package FLRAF;

import java.io.File;
import java.io.RandomAccessFile;
/**
 * creates a fixed length random access file for storage
 * @author Josh
 *
 */
public class FLRAF extends RandomAccessFile {
	int bytes;
	int blocks;
	static String filename = "./src/FLRAF/words.raf";
	File file = new File(filename);

	public FLRAF(int b) throws Exception {
		super(filename, "rw");
		bytes = b;
		blocks = 0;
	}

	public void writeBlock(byte[] block) throws Exception {
		seek(bytes * blocks);
		write(block);
		blocks++;
	}

	public byte[] getBlock(int x) throws Exception {
		byte[] b = new byte[bytes];
		if (x <= blocks) {
			seek(bytes * x);
			readFully(b);
			return b;
		}
		return null;
	}
}