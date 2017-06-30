package pe.open.client.escale.common.util;

import java.util.ArrayList;
import java.util.Collections;

import pe.open.client.escale.common.business.exception.FileInvalidaExtension;

public class FileProperties {
	private String extensions;
	private ExtensionSettings extensionSettings;
	private final Integer MB = 1024 * 1024;
	private final Long fileSizeMax;
	private String nombreArchivo;

	enum ExtensionSettings {
		VALID, INVALID
	}

	public FileProperties() {
		setExtensions("");
		setExtensionSettings(ExtensionSettings.VALID);
		fileSizeMax = Long.valueOf(MB * 3);
		//		fileSizeMax = Long.valueOf(MB * 10);
	}

	public FileProperties(String extensions) {
		setExtensions(extensions);
		setExtensionSettings(ExtensionSettings.VALID);
		fileSizeMax = Long.valueOf(MB * 3);
	}
	public FileProperties(String extensions, int cantidadMBMaxima) {
		setExtensions(extensions);
		setExtensionSettings(ExtensionSettings.VALID);
		fileSizeMax = Long.valueOf(MB * cantidadMBMaxima);
	}

	public FileProperties(String extensions, String extensionSettings) {
		setExtensions(extensions);
		this.extensionSettings = getExtensionSettings(extensionSettings);
		fileSizeMax = Long.valueOf(MB * 3);
	}

	public void isValidExtension(String uploadedFileExtension) throws FileInvalidaExtension {
		uploadedFileExtension = uploadedFileExtension.lastIndexOf(".") == -1 ? " "
				: uploadedFileExtension
						.substring(uploadedFileExtension.lastIndexOf(".") + 1, uploadedFileExtension.length()).trim()
						.toUpperCase();
		ArrayList<String> extensionsList = new ArrayList<String>();
		Collections.addAll(extensionsList, extensions.trim().toUpperCase().split(","));
		
		if (extensions.trim().length() == 0) { // if no list is defined
			if (extensionSettings.equals(ExtensionSettings.VALID)) {
				return;
			} else {
				throw new FileInvalidaExtension(uploadedFileExtension);
			}
		}
		if (extensionSettings.equals(ExtensionSettings.VALID)) { 
			if (!extensionsList.contains(uploadedFileExtension)) 
				throw new FileInvalidaExtension(uploadedFileExtension);
		} else { 
			if (extensionsList.contains(uploadedFileExtension)) 
				throw new FileInvalidaExtension(uploadedFileExtension);
		}
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions == null ? "" : extensions.trim();
	}

	public ExtensionSettings getExtensionSettings() {
		return extensionSettings;
	}

	public void setExtensionSettings(ExtensionSettings extensionSettings) {
		this.extensionSettings = extensionSettings;
	}

	public ExtensionSettings getExtensionSettings(String extensionSettings) {
		if (extensionSettings == null || extensionSettings.length() == 0) {
			return ExtensionSettings.VALID;
		} else {
			try {
				return ExtensionSettings.valueOf(extensionSettings.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
				// Do Nothing for now. Is this a good practice ?
			}
			return ExtensionSettings.VALID;
		}
	}

	public Long getFileSizeMax() {
		return fileSizeMax;
	}

	public Long getFileSizeMaxInMb() {
		return fileSizeMax / MB;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
}
