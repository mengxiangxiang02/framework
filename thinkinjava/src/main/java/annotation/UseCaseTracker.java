package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/7 14:00
 * @Description:注解处理器处理UseCase注解
 */
public class UseCaseTracker {
    public static void  trackUseCases(List<Integer> useCases,Class<?> c1)
    {
        for(Method m:c1.getDeclaredMethods())
        {
            UseCase useCase=m.getAnnotation(UseCase.class);
            if(useCase!=null)
            {
                System.out.println("Found UseCase:"+useCase.id()+";description: "+useCase.description());
                useCases.remove(new Integer(useCase.id()));
            }
        }
        for(int i:useCases)
        {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String args[])
    {
        List<Integer> useCases=new ArrayList<Integer>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCases(useCases,PasswordUtil.class);
    }
}
