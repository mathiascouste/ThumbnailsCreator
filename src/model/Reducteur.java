package model;

import java.io.File;
import view.Window;


public class Reducteur implements Runnable {
	private Window window;
	String from = "image";
	String to = "thumbnails";
	int maxWidth,maxHeight;
	
	
	public Reducteur(Window wdw) {
		this.window = wdw;
	}
	public void doIt() {
		/*if(args.length < 2) {
			System.out.println("This program needs at least two parameters.\n > cmd input_repository output_repository");
			return;
		} else {
			from = args[0];
			to = args[1];
		}*/
		ImageResizer ir;
		int finalSize = 290;
		
		DirectoryReader dr = new DirectoryReader();
		File[] fi = dr.listFiles(from);
		dr.addToList(fi);
		
		System.out.println(dr.getAr().size() + " fichiers trouves dans "+from);
		
		for(File f : dr.ar) {
			String[] tabF = f.getName().split("\\.");
			if(tabF.length > 1) {
				String extension = tabF[1].toLowerCase();
			if(extension.equals("jpg") || extension.equals("png") || extension.equals("bmp") || extension.equals("gif")) {
				//System.out.println("## Creating "+f.getName()+"'s thumbnail ##\nprocessing ...");
				window.writeState("## "+f.getName()+" ##");
				ir = new ImageResizer(f);
				if(ir.loadImage()) {
					ir.resizeImage(maxWidth,maxHeight);
					String name = f.getName();
					String [] tab = name.split("\\.");
					name = tab[0]+"."+tab[1].toLowerCase();
					String path = to+"/thumbnails_"+maxWidth+"x"+maxHeight+"_"+name;
					ir.saveImage(path);
				} else {
					System.out.println(f.getName()+" can't be loaded");
				}
			}
			}
		}
		window.writeState(">> End of the process <<");
	}
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	@Override
	public void run() {
		doIt();
	}
}
