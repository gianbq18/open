package pe.open.client.escale.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcelUtil {

	private List<Object[]> listaExportar;
	private List<String> listaExportarCabecera;
	static String MINEDU = "OPEN CLIENT";
	static String REPORTE = "REPORTE :";
	static String FECHA_REPORTE = "Fecha de creación :";
	private String nombreReporte;
	HttpServletResponse response;

	public ExportExcelUtil() {

	}

	public ExportExcelUtil(List<Object[]> listaExportar, List<String> listaExportarCabecera, String nombreReporte) {
		super();
		this.listaExportar = listaExportar;
		this.listaExportarCabecera = listaExportarCabecera;
		this.nombreReporte = nombreReporte;
	}

	public ExportExcelUtil(List<Object[]> listaExportar, List<String> listaExportarCabecera, String nombreReporte,
			HttpServletResponse response) {
		super();
		this.listaExportar = listaExportar;
		this.listaExportarCabecera = listaExportarCabecera;
		this.nombreReporte = nombreReporte;
		this.response = response;
	}

	public List<Object[]> getListaExportar() {
		return listaExportar;
	}

	public void setListaExportar(List<Object[]> listaExportar) {
		this.listaExportar = listaExportar;
	}

	public List<String> getListaExportarCabecera() {
		return listaExportarCabecera;
	}

	public void setListaExportarCabecera(List<String> listaExportarCabecera) {
		this.listaExportarCabecera = listaExportarCabecera;
	}

	public static String getMINEDU() {
		return MINEDU;
	}

	public static void setMINEDU(String mINEDU) {
		MINEDU = mINEDU;
	}

	public static String getREPORTE() {
		return REPORTE;
	}

	public static void setREPORTE(String rEPORTE) {
		REPORTE = rEPORTE;
	}

	public static String getFECHA_REPORTE() {
		return FECHA_REPORTE;
	}

	public static void setFECHA_REPORTE(String fECHA_REPORTE) {
		FECHA_REPORTE = fECHA_REPORTE;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@SuppressWarnings({ "resource", "deprecation" })
	public void doExport(List<Object[]> dataList) {
		if (dataList != null && !dataList.isEmpty()) {
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();
			// Estilo
			HSSFCellStyle style = workBook.createCellStyle();
			HSSFFont font = workBook.createFont();
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setFontHeightInPoints((short) 10);
			font.setBold(true);
			style.setFont(font);
			// style.setFillPattern(CellStyle.ALIGN_FILL);
			style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			HSSFRow mineduRow = sheet.createRow(0);
			mineduRow.createCell((short) 0).setCellValue(MINEDU);
			mineduRow.getCell(0).setCellStyle(style);
			HSSFRow tituloRow = sheet.createRow(1);
			tituloRow.createCell((short) 0).setCellValue(nombreReporte);
			tituloRow.getCell(0).setCellStyle(style);
			HSSFRow fechaRow = sheet.createRow(2);
			fechaRow.createCell((short) 0).setCellValue(FECHA_REPORTE + FechaUtil
					.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFechaActual(), "dd/MM/yyyy HH:mm:ss"));
			fechaRow.getCell(0).setCellStyle(style);

			HSSFRow headingRow = sheet.createRow(8);

			int i = 0;
			for (String cabecera : listaExportarCabecera) {
				headingRow.createCell((short) i).setCellValue(cabecera.toString());
				headingRow.getCell(i).setCellStyle(style);
				i++;
			}

			int z = 8; // La fila siguiente a la lista de cabecera
			HSSFRow row;
			HSSFCell cell;
			for (Object[] obj : dataList) {
				row = sheet.createRow(z + 1);
				for (int y = 0; y < obj.length; y++) {
					cell = row.createCell((short) y);
					if (obj[y] instanceof Date)
						cell.setCellValue(
								FechaUtil.obtenerFechaFormatoPersonalizado((Date) obj[y], "dd/MM/yyyy HH:mm:ss"));
					else if (obj[y] instanceof Boolean)
						cell.setCellValue((Boolean) obj[y]);
					else if (obj[y] instanceof Double)
						cell.setCellValue((Double) obj[y]);
					else if (obj[y] instanceof Integer)
						cell.setCellValue((Integer) obj[y]);
					else if (obj[y] == null)
						cell.setCellValue("");
					else
						cell.setCellValue("" + obj[y]);
				}
				z++;
			}

			if (dataList.size()<5000) {
				for (int r = 0; r < 50; r++) {
					sheet.autoSizeColumn(r);
				}
			}
			

			// String file = "D:/Files/exportarTabla.xls";

			try {
				// FileOutputStream fos = new FileOutputStream(file);
				ServletOutputStream stream = response.getOutputStream();
				workBook.write(stream);
				// fos.flush();
				// workBook.write(response.getOutputStream());
				
				response.getOutputStream().flush();
				// fos.close();
//				response.getOutputStream().close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("directorio invalido");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error al crear el archivo");
			}

		}
	}
}