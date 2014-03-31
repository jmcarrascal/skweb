package jmc.skweb.core.service.impl.test;

import java.io.File;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

public class TestBdTif {

public static void main(String[] args) throws Exception {
File inFile = new File("C:\\cheques\\test1.tif");
System.out.println(inFile.exists());
ImageInputStream is = ImageIO.createImageInputStream(inFile);
Iterator readers = ImageIO.getImageReadersByFormatName("");
ImageReader reader = null;
if ( readers.hasNext() ) {
reader = (ImageReader) readers.next();
} else {
return;
}
reader.setInput(is);
IIOImage iimg = reader.readAll(0,null);
ImageWriter iWriter = null;
Iterator it = ImageIO.getImageWritersByFormatName("jpg");
if ( it.hasNext() ) {
iWriter = (ImageWriter)it.next();
} else {
return;
}
File out = File.createTempFile("diagnose","jpg");
System.out.println("File " + out.getAbsolutePath());
FileImageOutputStream ios = new FileImageOutputStream(out);
iWriter.setOutput(ios);
iWriter.write(null,iimg,null);
}
}