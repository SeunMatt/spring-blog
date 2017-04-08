package com.smatt.addons;

import com.smatt.utils.MyApplicationContext;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by smatt on 23/03/2017.
 * This class will accepts a relative url to static files (css/js/images) and will print the complete file path to the
 * body
 * e.g. usage
 *
 * <@asset url='relativeUrl' />
 */

public class AssetDirective implements TemplateDirectiveModel {

    ApplicationContext context;

    public AssetDirective() {
        context = MyApplicationContext.getApplicationContext();
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        if (map.isEmpty() || map.size() > 1 || map.get("url") == null) {
            throw new TemplateModelException(
                    "This directive <@asset url='relativeUrl'> require one parameter only.");
        }

        if (templateModels.length != 0) {
            throw new TemplateModelException(
                    "This directive doesn't allow loop variables.");
        }

        if(templateDirectiveBody != null) {
            throw new TemplateModelException("This directive <@asset url='relativeUrl'> doesn't allow a body");
        }

        Writer out = environment.getOut();
        out.write(context.getEnvironment().getProperty("server.servlet-path") + map.get("url"));

    }

}

