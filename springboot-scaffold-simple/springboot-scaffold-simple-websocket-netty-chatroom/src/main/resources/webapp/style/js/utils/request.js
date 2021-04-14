

/**
 * Get 请求获取参数
 * @return {string}
 * @param name 参数名
 */
function getQueryString(name) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    const r = window.location.search.substr(1).match(reg);
    if (r !== null) {
        return unescape(decodeURI(decodeURI(r[2])));
    }
    return "";
}

const request = {
    /**
     * 带参数的get请求
     * @param url 地址
     * @param data 请求数据
     * @param success 成功回调
     * @param error 失败回调
     */
    get(url,data,success,error){
        $.ajax({
            url:url,
            type:'get',
            data:data,
            dataType:"json",
            success:success,
            error:error
        })
    },
    /**
     * 带参数的post请求
     * @param url 地址
     * @param data 请求数据
     * @param success 成功回调
     * @param error 失败回调
     * dataType:"json"//服务端返回的数据类型
     * contentType:'application/json;charset=UTF-8',//发送的数据类型
     */
    post(url,data,success,error){
        $.ajax({
            url:url,
            type:'post',
            data:JSON.stringify(data),
            dataType:"json",
            contentType:'application/json;charset=UTF-8',
            success:success,
            error:error
        })
    },
    /**
     * 带参数的put请求
     * @param url 地址
     * @param data 请求数据
     * @param success 成功回调
     * @param error 失败回调
     */
    put(url,data,success,error){
        // data._method = 'put';
        $.ajax({
            url:url,
            type:'put',
            data:JSON.stringify(data),
            dataType:"json",
            contentType:'application/json;charset=UTF-8',
            success:success,
            error:error
        })
    },
    /**
     * 带参数的delete请求
     * @param url 地址
     * @param data 请求数据
     * @param success 成功回调
     * @param error 失败回调
     */
    delete(url,data,success,error){
        // data._method = 'delete';
        $.ajax({
            url:url,
            type:'delete',
            data:JSON.stringify(data),
            dataType:"json",
            contentType:'application/json;charset=UTF-8',
            success:success,
            error:error
        })
    }

};

export {request};