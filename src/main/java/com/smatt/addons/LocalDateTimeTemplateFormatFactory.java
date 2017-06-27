package com.smatt.addons;

import freemarker.core.*;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModelException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by smatt on 23/05/2017.
 */
public class LocalDateTimeTemplateFormatFactory extends TemplateDateFormatFactory {

    public static final LocalDateTimeTemplateFormatFactory INSTANCE = new LocalDateTimeTemplateFormatFactory();

     private LocalDateTimeTemplateFormatFactory() {}

    @Override
    public TemplateDateFormat get(String s, int i, Locale locale, TimeZone timeZone, boolean b, Environment environment) throws TemplateValueFormatException {
        TemplateFormatUtil.checkHasNoParameters(s);
        return LocalDateTimeTemplateFormat.INSTANCE;
    }


    private static class LocalDateTimeTemplateFormat extends TemplateDateFormat {

        public static final LocalDateTimeTemplateFormat INSTANCE = new LocalDateTimeTemplateFormat();

        private LocalDateTimeTemplateFormat() {}

        @Override
        public String formatToPlainText(TemplateDateModel templateDateModel) throws TemplateValueFormatException, TemplateModelException {
            return String.valueOf(TemplateFormatUtil.getNonNullDate(templateDateModel).getTime());
        }

        @Override
        public Object parse(String s, int i) throws TemplateValueFormatException {
            return java.sql.Timestamp.valueOf(LocalDateTime.parse(s));
        }

        @Override
        public boolean isLocaleBound() {
            return false;
        }

        @Override
        public boolean isTimeZoneBound() {
            return false;
        }

        @Override
        public String getDescription() {
            return "will parse java.time.LocalDateTime object to java.sql.Timestamp";
        }
    }
}
