package pe.open.client.escale.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.open.client.escale.common.business.exception.FileErrorSubida;
import pe.open.client.escale.common.business.exception.FileInvalidaExtension;
import pe.open.client.escale.common.business.exception.FileInvalidoTamano;

public class FileUploadHelper {
	
	final static Logger log = Logger.getLogger(FileUploadHelper.class);
	
    FileProperties fileProperties; 
    FileRenamePolicy fileRenamePolicy;
    //Impl para renombrar en esta clase, no es necesario porque se desea que varie segun el negocio
//    FileRenamePolicy fileRenamePolicy = new FileRenamePolicy() {
//		
//		@Override
//		public File rename(File f, InputStream content) {
//			String fecha = FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFechaActual(), "ddMMyyyyHHmmss");
//			String extension = FilenameUtils.getExtension(f.getName());
//			String nombreFile = FilenameUtils.getBaseName(f.getName());
//			File f1 = new File(fecha + nombreFile + extension);
//			f.renameTo(f1);
//			return f;
//		}
//	};
 
    public FileUploadHelper() { 
        fileProperties = new FileProperties(); 
    } 
 
    public FileUploadHelper(FileProperties fileProperties) { 
        super(); 
        this.fileProperties = fileProperties; 
    } 
 
    public List<File> returnFiles(HttpServletRequest request, ServletContext context) throws FileErrorSubida, FileInvalidoTamano, FileInvalidaExtension { 
 
        // Compruebe que tenemos una petición de carga de archivos
        boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
        return isMultipart ? getFiles(request, context, null) : new ArrayList<File>(); 
    } 
 
//    public List<File> returnFiles(HttpServletRequest request, ServletContext context, FileRenamePolicy fileRenamePolicy) { 
// 
//        // Check that we have a file upload request 
//        this.fileRenamePolicy = fileRenamePolicy; 
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
//        return isMultipart ? getFiles(request, context, null) : new ArrayList<File>(); 
//    } 
 
//    public List<File> returnFiles(HttpServletRequest request, ServletContext context, String dirToSaveUploadedFileIn, FileRenamePolicy fileRenamePolicy) { 
// 
//        // Check that we have a file upload request 
//        this.fileRenamePolicy = fileRenamePolicy; 
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
//        return isMultipart ? getFiles(request, context, createDirectoryIfDoesntExist(dirToSaveUploadedFileIn)) : new ArrayList<File>(); 
//    } 
 
    public List<File> returnFiles(HttpServletRequest request, ServletContext context, String dirToSaveUploadedFileIn) throws FileErrorSubida, FileInvalidoTamano, FileInvalidaExtension { 
 
        // Compruebe que tenemos una petición de carga de archivos 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
        return isMultipart ? getFiles(request, context, createDirectoryIfDoesntExist(dirToSaveUploadedFileIn)) : new ArrayList<File>(); 
    } 
 
    public List<File> returnFiles(HttpServletRequest request, ServletContext context, String dirToSaveUploadedFileIn, FileRenamePolicy fileRenamePolicy) throws FileErrorSubida, FileInvalidoTamano, FileInvalidaExtension { 
    	 
        // Compruebe que tenemos una petición de carga de archivos
    	this.fileRenamePolicy = fileRenamePolicy;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
        return isMultipart ? getFiles(request, context, createDirectoryIfDoesntExist(dirToSaveUploadedFileIn)) : new ArrayList<File>(); 
    } 
    
    private List<File> getFiles(HttpServletRequest request, ServletContext context, String dirToSaveUploadedFileIn) throws FileErrorSubida, FileInvalidoTamano, FileInvalidaExtension { 
        List<File> files = new ArrayList<File>(); 

//        try {
//			Part filePartCroquis = request.getPart("fileCroquis");
//		} catch (IOException | ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        // Crear una fábrica de basado en disk-based file items 
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
 
        // Crear un nuevo controlador de carga de archivos
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setFileSizeMax(getFileProperties().getFileSizeMax()); 
        try { 
            // Parse the request 
            List<FileItem> items = upload.parseRequest(request); 
            // Procesar los elementos subidos 
 
            Iterator<FileItem> iter = items.iterator(); 
            while (iter.hasNext()) { 
                FileItem item = iter.next(); 
                if (!(StringUtils.equals(item.getContentType(), MediaType.APPLICATION_OCTET_STREAM))) {
                	getFileProperties().setNombreArchivo(item.getFieldName());
                    if (item.isFormField()) { 
                        request.setAttribute(item.getFieldName(), item.getString()); 
                        // Aqui va el codigo si el objeto no es un archivo
                        // Sino un campo del formulario
                    } else { 
                        getFileProperties().isValidExtension(item.getName());
                        files.add(processUploadedFile(item, dirToSaveUploadedFileIn)); 
                       
                    } 
				}
                
            } 
            return files; 
        }catch (FileSizeLimitExceededException slee) { 
        	throw new FileInvalidoTamano(String.valueOf(getFileProperties().getFileSizeMaxInMb()));
        }catch (FileUploadException fue) { 
            throw new FileErrorSubida(String.valueOf(getFileProperties().getNombreArchivo())); 
        } 
    } 
 
    private File processUploadedFile(FileItem item, String dirToSaveUploadedFileIn) throws FileErrorSubida { 
        dirToSaveUploadedFileIn = dirToSaveUploadedFileIn == null ? System.getProperty("java.io.tmpdir") : dirToSaveUploadedFileIn; 
        String fileName = item.getName();
//        String fecha = FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFechaActual(), "ddMMyyyyHHmmss");
        // Algunos navegadores IE 6,7 getName devuelve la ruta completa
        int startIndex = fileName.lastIndexOf('\\'); 
        if (startIndex != -1) { 
            fileName = fileName.substring(startIndex + 1, fileName.length());
//            fileName = fileName ;//concatemanos la fecha hasta segundos con el nomrbe el archivo original
        }         
        
        File uploadedFile = new File(dirToSaveUploadedFileIn + File.separator + fileName); 
        if (fileRenamePolicy != null) { 
		        try { 
		         uploadedFile = fileRenamePolicy.rename(uploadedFile,item.getFieldName()); 
		        } catch (Exception e) { //modificar IOException e
		        	throw new FileErrorSubida(String.valueOf(getFileProperties().getNombreArchivo())); 
	        } 
        }
        try { 
		   item.write(uploadedFile); 
		  } catch (Exception e) { 
		   throw new FileErrorSubida(String.valueOf(getFileProperties().getNombreArchivo())); 
		  } 
        return uploadedFile; 
 
    } 
 
    private String createDirectoryIfDoesntExist(String theDir) { 
        if (!new File(theDir).isDirectory()) { 
            new File(theDir).mkdirs(); 
        } 
        return new File(theDir).toString(); 
    } 
 
    public FileProperties getFileProperties() { 
        return fileProperties; 
    } 
 
    public void setFileProperties(FileProperties fileProperties) { 
        this.fileProperties = fileProperties; 
    } 
}
