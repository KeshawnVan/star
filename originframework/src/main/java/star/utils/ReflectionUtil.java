package star.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.bean.TypeWrapper;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author keshawn
 * @date 2017/11/9
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    public static <T> T newInstance(Class<T> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return (T) instance;
    }

    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

    public static TypeWrapper typeParse(Type type) {
        Class<?> cls = type instanceof ParameterizedType ? ((ParameterizedTypeImpl) type).getRawType() : (Class) type;
        return Collection.class.isAssignableFrom(cls)
                ? new TypeWrapper(getRawClass(type), getActualTypeArguments(type), Boolean.TRUE)
                : new TypeWrapper(cls, null, Boolean.FALSE);
    }

    public static Type[] getActualTypeArguments(Type type) {
        return ((ParameterizedType) type).getActualTypeArguments();
    }

    /**
     * ParameterizedType类型的Type获取外层类型
     * 如：传入List<User>，返回List
     * @param type
     * @return
     */
    public static Class<?> getRawClass(Type type){
        return ((ParameterizedTypeImpl) type).getRawType();
    }
}
