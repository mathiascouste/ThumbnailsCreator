package model;
import java.io.*; 
import java.util.*; 

/** 
* 
* @author HackTrack 
* @version 1.0 
*/ 
public class DirectoryReader { 
	public ArrayList<File> ar;
	public DirectoryReader() {
		ar = new ArrayList<File>();
	}

	public File[] listFiles(String directoryPath){
		File[] files = null;
		File directoryToScan = new File(directoryPath); 
		files = directoryToScan.listFiles(); 
		return files;
	}
	public ArrayList<File> getAr() {return ar;}
	
	public void addToList(File[] fi) {
		if(fi != null) {
			for(int i = 0 ; i < fi.length ; i++) {
				File f = fi[i];
				if(f.isDirectory()) {
					File[] fir = this.listFiles(f.getPath()+"/");
					this.addToList(fir);
				} else {
					this.ar.add(f);
				}
			}
			
		}
	}
} 