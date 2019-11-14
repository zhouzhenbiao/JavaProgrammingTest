package DynamicProxy.test;

import com.zzb.DynamicProxy.DynamicProxyImpl.SmallShrimp;
import com.zzb.DynamicProxy.Service;
import com.zzb.DynamicProxy.Service2;
import com.zzb.DynamicProxy.util.LittleFish;
import com.zzb.DynamicProxy.util.ProxyFactory;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        // 获取代理对象
        Service proxy = ProxyFactory.getInstance(SmallShrimp.class);
        proxy.eat();
        proxy.play();
    }

    public static void test2() {
        //代理对象实现类
        SmallShrimp shrimp = new SmallShrimp();
        LittleFish fish = new LittleFish(shrimp);

        Class[] clzArrays = {Service.class, Service2.class};
        Service2 proxy = (Service2) Proxy.newProxyInstance(SmallShrimp.class.getClassLoader(), clzArrays, fish);
//        Service2 proxy = (Service2) Proxy.newProxyInstance(SmallShrimp.class.getClassLoader(), SmallShrimp.class.getInterfaces(), fish);

        proxy.work();
    }
}
