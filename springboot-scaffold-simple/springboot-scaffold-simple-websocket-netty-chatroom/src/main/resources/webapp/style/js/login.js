
import {request} from "./utils/request";

const baseUrl = "http://127.0.0.1:8888/web";

function testPost(){
    const data = {};
    data._kkk = "111";
    console.log("login:");
    console.log(data);

    //此处进行登录验证，请求后台服务器获取用户信息
    let url = baseUrl+"/login";
    request.post(url,data,function (res) {
        console.log(res);
    },function (error) {
        console.log(error);
    });
}

/**
 * 用户登录
 */
const login = function(data){
    data._kkk = "111";
    console.log("login:");
    console.log(data);

    //此处进行登录验证，请求后台服务器获取用户信息
    let url = baseUrl+"/login";
    request.post(url,data,function (res) {
        console.log(res);
    },function (error) {
        console.log(error);
    });
    //登录成功，跳转聊天页面
    // window.location.href = "chat.html?nick=" + encodeURI(encodeURI(message));
};

/**
 * 用户注册
 */
const register = function(data){

};

export {testPost,login,register}

