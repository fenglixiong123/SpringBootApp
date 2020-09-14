
# Redis知识点精讲

## String数据类型

常用操作：
* set key value                 //存入字符串键值对
* mset key value[key value...]  //批量存入字符串键值对
* setnx key value               //存入一个不存在的字符串键值对
* get key                       //获取一个字符串键值
* mget key[key...]              //批量获取字符串键值
* del key[key...]               //删除一个健
* expire key seconds            //设置一个健的过期时间（秒）

* incr key                      //将key中存储的数字加1
* decr key                      //将key中存储的数字减1
* incrby key increment          //将key所存储的值加上increment
* decrby key increment          //将key所存储的值减去decrement

### 单值缓存

set key value  
get key  

### 对象缓存

* 存储对象的json结构  
set user:1 value(json数据)  
* 存储对象的字段到set中去   
mset user:1:name jack user:1:balance 2000  
mget user:1:name user:1:balance  

### 分布式锁

setnx product:1001 true //返回1代表获取锁成功
setnx product:1001 true //返回0代表获取锁失败
执行业务操作
del product:1001    //执行完释放锁

set product:1001 true ex 10 nx  //防止程序意外终止导致死锁

### 计数器

incr article:readcount:{文章id}  
get article:readcount:{文章id}

### 分布式系统全局序列号

incrby orderId 1000    //redis批量生成序列号提升性能

### Web集群session共享
spring session + redis实现session共享

## Hash数据类型

常用操作：
* hset key field value                      //存储一个哈希表key的键值
* hsetnx key field value                    //存储一个不存在的哈希表key的键值
* hmset key field value[field value...]     //在一个哈希表key中存储多个键值对
* hget key field                            //获取哈希表key对应的field键值
* hmget key field[field...]                 //批量获取哈希表key中多个field键值
* hdel key field[field...]                  //删除哈希表key中的field键值
* hlen key                                  //返回哈希表key中field的数量
* hgetall key                               //返回哈希表key中所有的键值

* hincrby key field increment               //为哈希表key中field键的值加上增量increment

### 对象缓存

hmset user {userId}:name value {userId}:age value

hmset user 1:name jack 1:age 22  
hmset user 2:name mary 2:age 18

hmget user 1:name 1:age  
hmget user 2:name 2:age

* 优点

1)同类数据归类整合存储，方便数据管理  
2)相比string操作消耗内存与cpu更小  
3)相比string更加节省空间  

* 缺点

1)过期功能不能用在field上，只能用在key上面  
2)Redis集群下不适合大规模使用

### 电商购物车

1)用户id为key      1001  
2)商品id为field    9999  
3)商品数量为value

购物车操作：

1)添加商品------------->hset shopcar:1001 9999 1  
2)增加数量------------->hincrby shopcar:1001 9999 1  
3)商品总数------------->hlen shopcar:1001  
4)删除商品------------->hdel shopcar:1001 9999  
5)获取购物车所有商品----->hgetall shopcar:1001  

## List数据结构

常用操作：  
* lpush key value[value...]         //将一个或者多个值value插入到key列表的表头(最左边)
* rpush key value[value...]         //将一个或者多个值value插入到key列表的表尾(最右边)
* lpop key                          //移除并返回key列表的头元素
* rpop key                          //移除并返回key列表的尾元素
* lrange key stat stop              //返回列表key中指定区间内的元素，区间以偏移量start和stop指定

* blpop key[key...] timeout         //从key列表表头弹出一个元素，若列表中没有元素，阻塞等待timeout秒，如果timeout=0则一直等待
* brpop key[key...] timeout         //从key列表表尾弹出一个元素，若列表中没有元素，阻塞等待timeout秒，如果timeout=0则一直等待




























































































































