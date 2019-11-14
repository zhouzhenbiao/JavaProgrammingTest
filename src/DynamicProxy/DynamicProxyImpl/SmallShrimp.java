package DynamicProxy.DynamicProxyImpl;

import com.zzb.DynamicProxy.Service;
import com.zzb.DynamicProxy.Service2;

/**
 * 虾米类 实现了 他的 核心业务
 */
public class SmallShrimp implements Service, Service2 { //虾米类
    @Override
    public void eat() {
        System.out.println("虾米吃海藻。。。。。");
    }

    @Override
    public void play() {
        System.out.println("虾米喜欢玩");
    }

    @Override
    public void work() {
        System.out.println("虾米正在找工作。。。。");
    }
}
