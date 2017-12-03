
package com.serverseri.core.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Component
public class FreeMakerUtils {

  private static final Logger logger = LoggerFactory.getLogger(FreeMakerUtils.class);

  @Autowired
  private FreeMarkerConfig freeMakerConfig;

  public String templateToString(String templateName, Map<String,Object> model) {
    StringBuffer content = new StringBuffer();
    try {
      content.append(FreeMarkerTemplateUtils
          .processTemplateIntoString(freeMakerConfig.getConfiguration().getTemplate(templateName),model));
    } catch (Exception e) {
      logger.error("Error: ", e);
    }
    logger.info("Template " + templateName + " Converted Into String");
    return content.toString();
  }
}
