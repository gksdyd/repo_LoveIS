function indexSearch() {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexSearch"
        ,data: {  }
        ,success: function(response) {
            $(".card-body").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function docSearch() {
    if ($("#shIndex").val() === "") {
        return;
    }

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmDocSearch"
        ,data: { "index" : $("#shIndex").val() }
        ,success: function(response) {
            $(".card-body").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function elasticRegister() {
    window.location.href = "/elastic/xdm/ElasticXdmForm"
}

function changeIndex() {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexChange"
        ,data: { "index" : $("#index").val() }
        ,success: function(response) {
            if (response === "Fail") {
                return;
            }
            $("blockquote").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

const ENGLISH = /[a-zA-Z]/;
const KOREAN = /[가-힣]/;
const regex = /^\/[a-zA-Z]+.*$/;

function validation() {
    let text = "<div class='fs-10 error' style='color:red;'>text</div>";
    if ($("#index").val() === "") {
        text = text.replace("text", "index 선택해주세요!");
        $("#index").parent().append(text);
        return false;
    }

    if (!KOREAN.test($("#name").val())) {
        text = text.replace("text", "한글 이름을 입력해주세요!");
        $("#name").parent().append(text);
        return false;
    }

    if (!ENGLISH.test($("#engName").val())) {
        text = text.replace("text", "영문 이름을 입력해주세요!");
        $("#engName").parent().append(text);
        return false;
    }

    if (!regex.test($("#url").val())) {
        text = text.replace("text", "올바른 주소를 입력해주세요! (/example...)");
        $("#url").parent().append(text);
        return false;
    }

    return true;
}

function elasticDoc() {
    $(".error").remove();

    if (!validation()) {
        return;
    }
}