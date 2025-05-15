function changeLocal(e) {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/love/area/AreaLoveSelectProc"
        ,data: { "ifcaParents" : $(e).val() }
        ,success: function(response) {
          $("#changeLocal").html(response);
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
      });
}