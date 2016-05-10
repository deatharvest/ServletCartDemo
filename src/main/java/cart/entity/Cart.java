package cart.entity;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//购物车类
public class Cart {

    //购买商品的集合
    private HashMap<cart.entity.Items,Integer> goods;

    //购物车的总金额
    private double totalPrice;

    //构造方法
    public Cart()
    {
        goods = new HashMap<cart.entity.Items,Integer>();
        totalPrice = 0.0;
    }


    public HashMap<cart.entity.Items, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<cart.entity.Items, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //添加商品进购物车的方法
    public boolean addGoodsInCart(cart.entity.Items item , int number)
    {
        if(goods.containsKey(item))    //goods 为HsahMap<Items,Integer>  key,value
        {
            goods.put(item, goods.get(item)+number);//覆盖操作
        }
        else
        {
            goods.put(item, number);
        }
        calTotalPrice(); //重新计算购物车的总金额
        return true;
    }

        //删除商品的方法
    public boolean removeGoodsFromCart(cart.entity.Items item)
    {
        goods.remove(item);
        calTotalPrice(); //重新计算购物车的总金额
        return true;
    }

    //统计购物车的总金额
    public double calTotalPrice()
    {
        double sum = 0.0;
        Set<cart.entity.Items> keys = goods.keySet(); //获得键的集合
        Iterator<cart.entity.Items> it = keys.iterator(); //获得迭代器对象
        while(it.hasNext())
        {
            cart.entity.Items i = it.next();
            sum += i.getPrice()* goods.get(i);
        }
        this.setTotalPrice(sum); //设置购物车的总金额
        return this.getTotalPrice();
    }

    public static void main(String[] args) {

        //先创建两个商品对象
        cart.entity.Items i1 = new cart.entity.Items(1,"沃特篮球鞋","温州",200,500,"001.jpg");
        cart.entity.Items i2 = new cart.entity.Items(2,"李宁运动鞋","广州",300,500,"002.jpg");
        cart.entity.Items i3 = new cart.entity.Items(1,"沃特篮球鞋","温州",200,500,"001.jpg");

        Cart c = new Cart();
        c.addGoodsInCart(i1, 1);
        c.addGoodsInCart(i2, 2);
        //再次购买沃特篮球鞋，购买3双
        c.addGoodsInCart(i3, 3);


        //变量购物商品的集合
        Set<Map.Entry<cart.entity.Items, Integer>> items= c.getGoods().entrySet();
        for(Map.Entry<cart.entity.Items, Integer> obj:items)
        {
            System.out.println(obj);
        }


        System.out.println("购物车的总金额："+c.getTotalPrice());

    }

}
