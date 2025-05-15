/* ===============================================-->
                      로그아웃 JS
   ===============================================--> */

// 로그아웃 클릭 시 실행되는 Ajax
function logout() {
  $.ajax({
    async: true 
    ,cache: false
    ,type: "post"
    ,url: "/love/member/LogoutLoveProc"
    ,data: {}
    ,success: function(response) {
      if(response.rt == "success") {
        location.href = "/love/member/LoginLoveForm";
      } else {
        // by pass
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
}