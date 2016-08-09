package domon.cn.gankio.network;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Domon on 16-8-9.
 */
public abstract class BaseCallback<T> {
    public Type mType;

    static Type getSuperClassTypeParameter(Class<?> subclass) {
        Type superClass = subclass.getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    public BaseCallback() {
        mType = getSuperClassTypeParameter(getClass());
    }

    public abstract void onRequestBefore();

    public abstract void onFailure(Request request, Exception e);

    public abstract void onError(Response response, int errorCode, Exception e);

    public abstract void onSuccess(Response response, T t);

}
