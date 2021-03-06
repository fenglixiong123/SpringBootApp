package com.flx.springboot.scaffold.common.system;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author Fenglixiong
 * @Create 2021/4/12 23:49
 * @Description
 *
 * 以下[]为了防止转义，实际需要去掉
 *
 * classpath:[**]/*.class 可以获取到当前项目类路径下所有的类
 * classpath*:[**]/*.class 可以获取到所有classpath下所有的类
 * classpath*:com/flx/**[/entity/**]/*.class 可以获取指定目录下所有的类
 *
 **/
public class ClassUtils {

    public static void main(String[] args) throws Exception {
        Set<Class<?>> classes = getClasses("com.flx.springboot.scaffold.common.utils", true);
        for (Class<?> c:classes){
            System.out.println(c.getName());
        }
    }

    /**
     * 返回类的指定泛型的具体类型
     *
     * getSuperclass  返回直接继承的父类（由于编译擦除，没有显示泛型参数）  class cn.test.Person
     * getGenericSuperclass  返回直接继承的父类（包含泛型参数）  cn.test.Person<cn.test.Test>
     *
     * @param clazz 你要获取父类泛型的类，一般填this.getClass
     * @param index 你要获取哪个泛型，按照顺序索引
     * @return 返回泛型的具体Class类型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperGenericClass(Class clazz,int index)throws Exception{
        //获取该类的直接超类[带泛型]
        Type genType = clazz.getGenericSuperclass();
        //判断是否支持泛型
        if(!(genType instanceof ParameterizedType)){
            throw new Exception("can't support generic !");
        }
        //ParameterizedType 是Type的子类，用于接收含有泛型的类型
        //获取实际类型参数的Type对象数组，数组放的是Class，比如 Jack extends Person<A,B>
        //比如当前类为BaseManager<T extends BaseDO,V extends BaseDao>
        //实际用的时候可能会写StudentManager extends BaseManager{...}
        //此时对于StudentManager来说
        //this.getClass是当前类
        //this.getClass.getGenericSuperclass()是BaseManager
        //arguments为：[T.class,V.class]
        Type[] arguments = ((ParameterizedType) genType).getActualTypeArguments();
        if(index>=arguments.length||index<0){
            throw new Exception("index error !");
        }
        return (Class<T>)arguments[index];
    }

    /**
     * 获取父类泛型对应的对象
     */
    public static <T> T getSuperGenericInstance(Class<T> clazz,int index) throws Exception {
        Class<T> genericClass = getSuperGenericClass(clazz,index);
        return genericClass.newInstance();
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param packageName 包名
     * @param recursive 是否循环迭代
     * @return
     */
    public static Set<Class<?>> getClasses(String packageName,boolean recursive) throws Exception {

        // 第一个class类的集合
        //List<Class<?>> classes = new ArrayList<Class<?>>();
        Set<Class<?>> classes = new HashSet<>();
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    addClass(classes, filePath, packageName);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static void addClass(Set<Class<?>> classes, String filePath, String packageName) throws Exception {
        File[] files = new File(filePath).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        assert files != null;
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (!packageName.isEmpty()) {
                    className = packageName + "." + className;
                }
                doAddClass(classes, className);
            }

        }
    }

    public static void doAddClass(Set<Class<?>> classes, final String classsName) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
        classes.add(classLoader.loadClass(classsName));
    }


    public static <A extends Annotation> Set<Class<?>> getAnnotationClasses(String packageName, Class<A> annotationClass) throws Exception {

        //找用了annotationClass注解的类
        Set<Class<?>> controllers = new HashSet<>();
        Set<Class<?>> clsList = getClasses(packageName,true);
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                if (cls.getAnnotation(annotationClass) != null) {
                    controllers.add(cls);
                }
            }
        }
        return controllers;
    }

}
