package DynamicProxy.util;

import com.zzb.DynamicProxy.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 小鱼类 -> 代理实现类
 */
public class LittleFish implements InvocationHandler { //小鱼类

    //持有一个真实角色引用
    private Service smallShrimp;

    public LittleFish(Service smallShrimp) {
        this.smallShrimp = smallShrimp;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //执行辅助业务
        eat1();

        // 监视 虾米有没有触发核心业务，如果虾米吃了海藻编写辅助业务
        method.invoke(smallShrimp, args);

        //执行辅助业务
        eatSmallShrimp();

        return null;
    }

    public void eat1() {
        System.out.println("虾米吃之前要唱歌");
    }

    public void eatSmallShrimp() {
        System.out.println("小鱼吃虾米");
    }
}
