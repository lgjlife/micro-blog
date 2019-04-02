package com.mybatis.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class OverwriteXmlPlugin extends PluginAdapter {

   /* @Override
    public boolean validate(List<String> warnings) {
        return true;
    }*/

    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
       // sqlMap.setMergeable(false);
       // return super.sqlMapGenerated(sqlMap, introspectedTable);

        return true;
    }


}
