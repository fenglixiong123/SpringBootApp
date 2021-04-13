
/**
 * 用户登录
 */
login = () => {

    let username = $("input[name=username]").val().trim();
    let password = $("input[name=password]").val().trim();

    if(!username){
        alert("用户名为空请重新填写！");
        return;
    }
    if(!password){
        alert("密码为空请重新填写！");
        return;
    }

    //此处进行登录验证，请求后台服务器获取用户信息

    //登录成功，跳转聊天页面
    window.location.href = "chat.html?nick=" + encodeURI(encodeURI(message));
    return false;
};

/**
 * 清除登录框
 */
clearLogin = ()=>{
    $("input[name=username]").val("");
    $("input[name=password]").val("");
};

/**
 * 用户注册
 */
register = ()=>{

};



