
# html中运行es6语法

要运行es6需要使用webpack+babel来进行转换

## Windows测试可用

现在的浏览器很多都不支持es6的语法，或者仅仅是部分支持  
babel就承担了“翻译”的角色，把es6的写法转换成es5的写法。  
利用webpack打包工具进行打包

前面先安装最新版node

### 1.创建项目

    新建项目：netty-web-chat
    进入项目：cd netty-web-chat  

### 2.初始化项目

    npm init
    
 会生成 package.json文件

#### 3.安装各种依赖

 打开终端程序执行下面命令
 
* 安装cross-env 能跨平台地设置及使用环境变量

        npm install cross-env --save-dev

* 安装webpack 打包工具 

        npm install webpack --save-dev //这里指定安装3.0版本，否则会提示版本不匹配
        npm install webpack-cli@3 --save-dev

* 安装babel  实现 ES6 到 ES5

        npm install @babel/preset-env --save-dev //安装转码规则

* 安装babel-loader

        npm install @babel/core babel-loader --save-dev

注意：
        
       如果webpack版本过高，则需要更换为低版本：
            npm uninstall webpack-cli
            npm install webpack-cli@3 --save-dev
            
       如果安装了babel-preset-es2015需要修改为babel-preset-env
            npm uninstall --save-dev babel-preset-es2015
            npm install --save-dev babel-preset-env@next

注释：  

--save-dev:

当你为你的模块安装一个依赖模块时，正常情况下你得先安装他们（在模块根目录下npm install module-name），然后连同版本号手动将他们添加到模块配置文件package.json中的依赖里（dependencies）。

-save和save-dev可以省掉你手动修改package.json文件的步骤。  

* npm install module-name -save 自动把模块和版本号添加到dependencies部分
* npm install module-name -save-dve 自动把模块和版本号添加到devDependencies部分

#### 4.打开项目

    使用webstorm等工具打开netty-web-chat项目
        |---node_modules
        |---package.json
        |---package-lock.json

### 5.创建babelrc文件

设置一个.babelrc的文件放在根目录下  
内容：
~~~  
{
  "presets": [ "env" ]
} 
~~~

### 6.配置webpack

文件：webpack.config.js  

~~~
module.exports = {
    entry: "./js/main.js",
    output:{
        filename: 'bundle.js'

    },
    module: {
        loaders: [{
            test: /\.js$/,
            loader: "babel-loader"
        }]

    }
}
~~~

### 7.配置package.json

    "scripts": {
        "dev": "webpack-dev-server --port 8080 --hot --open --open-page './webapp/html/index.html'",
        "build": "cross-env NODE_ENV=development webpack --config webpack.config.js",
        "prod": "cross-env NODE_ENV=production webpack --config webpack.production.js",
        "test": "echo \"Error: no test specified\" && exit 1"
    }
    
    说明：
    (1)dev采用了devServer热部署的形式，可以运行在node的小型服务器上面
    (2)build指定了打包环境以及打包采用的配置，可以将js文件从es6转换成es5

### 8.打包文件

    npm run build

### 启用热部署

devServer为你提供了一个简单的 web server，并且具有 live reloading(实时重新加载) 功能。

* 安装插件

        npm install webpack-dev-server --save-dev //热更新服务器

* 修改引用

    我们要注意的是：webpack output is served from /。打包输出的文件在服务器的内存里。文件名：bundle.js  
    我们要修改index.html的文件引入路径，改为：<script src="/bundle.js" ></script>
    

* 添加脚本
        
        方式一：
        
        "scripts": {
            "dev": "webpack-dev-server --port 8080 --hot --open --open-page "/dist/index.html" "
        }
        
        方式二：
        
        "scripts": {
             "dev": "webpack-dev-server"
         }
        
        webpackage.config.js中配置
        
        devServer:{
            port:8080,//修改端口为8080
            hot:true,//开启热加载
            open:true,//执行npm run以后会自动打开浏览器
            openPage:"/dist/index.html"//指定打开的页面
            
        }
        
* 运行脚本

        npm run dev















