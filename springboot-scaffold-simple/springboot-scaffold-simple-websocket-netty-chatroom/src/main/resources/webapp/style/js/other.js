
//login
function login(){
    var username = $("input[name=username]");
    var password = $("input[name=password]");

    var ustr = $.trim(username.val());
    var pstr = $.trim(password.val());

    if(!ustr){
        alert("用户名为空请重新填写！");
        return;
    }
    if(!pstr){
        alert("密码为空请重新填写！");
        return;
    }

    $("#LoginForm").submit();

    clearLoginForm();
}

function clearLoginForm(){
    $("input[name=username]").val("");
    $("input[name=password]").val("");
}

//page
function turnPage(page){
    var totalPage = $("#totalPage").val();
    if(page>totalPage){
        page = totalPage;
    }
    if(page<1){
        page = 1;
    }
    $("#page").val(page);
    loadDate();
}

function prePage() {
    var page = $("#page").val();
    turnPage(parseInt(page)-1);
}

function nextPage(){
    var page = $("#page").val();
    turnPage(parseInt(page)+1);
}