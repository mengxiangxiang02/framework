package annotation.DataBaseAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/7 16:10
 * @Description:表创建
 */
public class TableCreator {
    public static void main(String args[]) throws Exception {
        args=new String[]{"annotation.DataBaseAnnotation.Member"};
        for(String className:args)
        {
            Class<?> c1=Class.forName(className);
            DBTable dbTable=c1.getAnnotation(DBTable.class);
            if(dbTable==null)
            {
                System.out.println("No table annotations in class"+className);
                continue;
            }
            String tableName=dbTable.name();
            if(tableName.length()<1)
            {
                tableName=c1.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<String>();

            for(Field field:c1.getDeclaredFields())
            {
                String columnName=null;
                Annotation[] annotations = field.getDeclaredAnnotations();
                if(annotations.length<1)
                {
                    continue;
                }
                if(annotations[0] instanceof SQLString)
                {
                    SQLString sqlString=(SQLString)annotations[0];
                    if(sqlString.name().length()<1)
                    {
                        columnName=field.getName().toUpperCase();
                    }else
                    {
                        columnName=sqlString.name().toUpperCase();
                    }
                    columnDefs.add(columnName + " VARCHAR2(" +
                            sqlString.value() + ")" +
                            getConstraints(sqlString.constraints()));
                }

                if(annotations[0] instanceof SQLInteger)
                {
                    SQLInteger sqlString=(SQLInteger)annotations[0];
                    if(sqlString.name().length()<1)
                    {
                        columnName=field.getName().toUpperCase();
                    }else
                    {
                        columnName=sqlString.name().toUpperCase();
                    }
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sqlString.constraints()));
                }

                StringBuilder createCommand = new StringBuilder(
                        "CREATE TABLE " + tableName + "(");
                for(String columnDef : columnDefs)
                    createCommand.append("\n " + columnDef + ",");
                // Remove trailing comma
                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1) + "\n);";
                System.out.println("Table Creation SQL for " +
                        className + " is :\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con)
    {
        String constraints="";
        if(!con.allowNull()) constraints+=" NOT NULL";
        if(con.primaryKey()) constraints+=" PRIMARY KEY";
        if(con.unique()) constraints+=" UNIQUE";
        return constraints;
    }
}
