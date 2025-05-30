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