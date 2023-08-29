/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compressionmanipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

/**
 *
 * @author duyvu
 */
public abstract class CompressionDAO {

    /**
     * Compressing Byte File
     *
     * Read a single byte from raw file and compress it into a .txt file
     *
     * Alternate Using DeflateInputStream for InputStream or DeflatOutputStream
     * for OutputStream
     *
     * @param file
     * @param locatedFolder
     */
    protected static void compressByteFile(File locatedFolder, File file) {

//	File fileZip = new File(locatedFolder, file.getName().replaceFirst("[.]\\w*", ".zip"));
	File fileZip = new File(locatedFolder, "compressed.zip");

	try (FileInputStream fis = new FileInputStream(file); DeflaterInputStream dis = new DeflaterInputStream(fis); FileOutputStream fos = new FileOutputStream(fileZip)) {

	    // Read and create a connection to a file
	    // Why does writing a byte take integer, instead of byte when dealing with byte stream
	    // - it only cares about the lowest 8 bits of data
	    // - the 24 bits remaining are ignored
	    // - If you actually care the multi-byte Unicode, the Reader classes were introduced back with Java 1.1
	    // https://stackoverflow.com/questions/1407893/why-java-outputstream-write-takes-integer-but-writes-bytes
	    int code;
	    while ((code = dis.read()) != -1) {
		fos.write(code);
	    }

	    // Finish the compression and close all of the connection (done with try-with-resource)
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(CompressionDAO.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(CompressionDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Decompressing Byte File
     *
     * Read a single block of byte (8-bits) from the zip and decompress it into
     * the raw file
     *
     * Alternative to use InflateInputStream for InputStream or InflateOutput
     * for OutputStream
     *
     * @param locatedFolder
     * @param file
     */
    protected static void uncompressByteFile(File locatedFolder, File file) {

//	File uncompressedFile = new File(locatedFolder, file.getName().replaceFirst("[.]\\w*", "2.txt"));
	File decompressedFile = new File(locatedFolder, "decompressedFile.txt");

	try (FileInputStream fis = new FileInputStream(file); InflaterOutputStream ios = new InflaterOutputStream(new FileOutputStream(decompressedFile))) {

	    // Reading a single byte and decompress a single byte
	    int code;
	    while ((code = fis.read()) != -1) {
		ios.write(code);
	    }

	} catch (FileNotFoundException ex) {
	    Logger.getLogger(CompressionDAO.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(CompressionDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
