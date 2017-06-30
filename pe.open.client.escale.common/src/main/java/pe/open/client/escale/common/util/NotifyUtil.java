package pe.open.client.escale.common.util;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class NotifyUtil {
	
	
    /**
     * Freemarker do.
     *
     * @param datamodel the datamodel
     * @param basePath the base path
     * @param template the template
     * @return the string
     * @throws Exception the exception
     */
    @SuppressWarnings("rawtypes")
	public static String freemarkerDo(Map datamodel, String basePath, String template) throws Exception{

		@SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
                
                @SuppressWarnings("unused")
				ClassLoader classLoader = NotifyUtil.class.getClassLoader();               
//                File file = new File(classLoader.getResource(basePath).getFile());
//                cfg.setDirectoryForTemplateLoading(file);
		cfg.setDirectoryForTemplateLoading(new File(basePath));
		
		Template tpl = cfg.getTemplate(template);
		
		StringWriter sw = new StringWriter();

		tpl.process(datamodel, sw);

		return sw.toString();	

	}

}
