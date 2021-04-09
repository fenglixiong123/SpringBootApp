
/**
 * 用户登录
 */
login = () => {
    let message = $("#username").value();
    if (message === "" || message === null) {
        alert("昵称不能为空~");
        return;
    }
    if (message.length > 15) {
        alert("昵称字数不能大于15~");
        return;
    }
    //此处进行登录验证，请求后台服务器获取用户信息

    //登录成功，跳转聊天页面
    window.location.href = "chat.html?nick=" + encodeURI(encodeURI(message));
    return false;
};

/**
 * 用户注册
 */
register = ()=>{

};