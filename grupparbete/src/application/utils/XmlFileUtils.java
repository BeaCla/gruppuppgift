package application.utils;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import application.model.Competitor;
import javafx.collections.ObservableList;

/**
 * Utility class for reading and writing a file with XMLEncoder and XMLDecoder.
 * 
 * @author Niclas Lindblom
 *
 */
public class XmlFileUtils {

	/**
	 * Method for writing an Xmlfile with data used in SkiComp.
	 * 
	 * @param competitor {@link ObservableList <{@link Competitor}> list of
	 *                   competitor posts.
	 * @param filename   {@link String} Name of the xml file including path.
	 * @throws IOException when file operation goes wrong.
	 */
	static public void writeXMLEncoder(ObservableList<Competitor> competitor, String filename) throws IOException {
		FileOutputStream fos = null;
		Competitor[] c = new Competitor[competitor.size()];
		c = competitor.toArray(c);
		try {
			fos = new FileOutputStream(filename);
		} catch (FileNotFoundException e1) {

			System.out.println("file " + filename + " not found");
		}
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				System.err.println("Exception! :" + e.toString());
			}
		});
		encoder.writeObject(c);
		encoder.close();
		fos.close();
	}

	/**
	 * Method for reading an xml file for populating data in SkiComp.
	 * @param filename
	 * @return Competitor {@link Competitor} Array with Competitors
	 */
	static public Competitor[] readXMLDecoder(String filename) {
		
		Competitor[] c = new Competitor[0];
		
	    FileInputStream fis = null;
	    
		File f = new File(filename);
		if(f.exists() && !f.isDirectory()) { 
			try {
				fis = new FileInputStream(filename);
				XMLDecoder decoder = new XMLDecoder(fis);
				c = (Competitor[]) decoder.readObject();
				 decoder.close();
				 fis.close();
				//Remove null objects of type Competitor in array, to not create empty table entries.
				c = Arrays.stream(c).filter(Objects::nonNull).toArray(Competitor[]::new);
			} catch (FileNotFoundException e) {
				System.out.println("file " + filename + " not found");
			} catch (ArrayIndexOutOfBoundsException aie) {
				System.out.println(aie);
			}  catch (IOException e) {
					System.err.println("Kunde inte stänga filen :" + filename);
					System.out.println("Kunde inte stänga filen :" + filename);
					e.printStackTrace();
			}
		}
	    return c;
	}

}
