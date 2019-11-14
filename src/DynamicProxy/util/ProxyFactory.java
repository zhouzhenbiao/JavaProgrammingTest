package DynamicProxy.util;

import com.zzb.DynamicProxy.DynamicProxyImpl.SmallShrimp;
import com.zzb.DynamicProxy.Service;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    // clz 是被监听的对象，也就是 SmallShrimp
    public static Service getInstance(Class<?> clz) {

        //需要一个真实角色
        SmallShrimp shrimp = new SmallShrimp();

        //创建代理对象实现类
        LittleFish fish = new LittleFish(shrimp);

        //创建代理对象
        Class[] clzArrays = {Service.class};
        //三个参数，第一个是被监听类，第二个是被监听的方法（被监听类能触发这些被监听的方法，一旦用了被监听的方法，则生成辅助业务），第三个是代理实现类（辅助业务）
        Service proxy = (Service) Proxy.newProxyInstance(clz.getClassLoader(), clzArrays, fish);
        return proxy;
    }
}
