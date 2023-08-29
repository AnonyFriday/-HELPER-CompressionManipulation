/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compressionmanipulation;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class CompressionManipulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// Create a simulated file and directory
	File dir = new File("tmp");
	dir.mkdir();

	File file = new File(dir, "uncompress.txt");
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException ex) {
		Logger.getLogger(CompressionManipulation.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	// Compress File
	CompressionDAO.compressByteFile(dir, file);

	// Decompress File
	File compressedFile = new File(dir, "compressed.zip");
	CompressionDAO.uncompressByteFile(dir, compressedFile);

    }

}
