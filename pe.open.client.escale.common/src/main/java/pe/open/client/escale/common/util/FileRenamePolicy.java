package pe.open.client.escale.common.util;

import java.io.File;
import java.io.InputStream;


public interface FileRenamePolicy {
	
	 public File rename(File f, InputStream content);
	 public File rename(File f, String nombreBase);
	 public String rename(String[] nombreBase);
}
