
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

        npm cross-env --save-dev 

* 安装webpack 打包工具 

        npm -g install webpack@3 --save-dev //这里指定安装3.0版本，否则会提示版本不匹配
        npm install webpack-cli --save-dev

* 安装babel  实现 ES6 到 ES5

        npm install babel-core babel-preset-es2015 --save-dev

* 安装babel-loader

        npm install babel-loader --save-dev

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
   "presets": ["es2015"]  
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

    {
      "scripts": {
        "build": "cross-env NODE_ENV=production webpack --config webpack.config.js"
      }
    }

### 8.打包文件

    npm run build



















