// <!-- Add the Facebook SDK for Javascript -->
  
function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    console.log('statusChangeCallback');
    console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook.
      testAPI();  
    }
  }


  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1440371947329125',
      cookie     : true,                     // Enable cookies to allow the server to access the session.
      xfbml      : true,                     // Parse social plugins on this webpage.
      version    : 'v22.0'           // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };
 
  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', {
        fields: 'name,email,gender,birthday'
      },
      function(response) {
      console.log(response);
      console.log('Successful login for: ' + response.name);
      checkInfo(response.name, response.email);
    });
  }

  function facebookLogin() {
    if (typeof FB === 'undefined') {
      console.error('Facebook SDK not loaded yet');
      return;
    }
  
    FB.login(function(response) {
      if (response.status === 'connected') {
        testAPI(); // 로그인 성공 시 사용자 정보 요청 등 처리
      } else {
        console.log('User cancelled login or did not fully authorize.');
      }
    }, { scope: 'public_profile,email' });
  }

  function checkInfo(name, email) {
    $.ajax({
      async: true 
      ,cache: false
      ,type: "post"
      ,url: "/love/google/FacebookLoveLogin"
      ,data : { "userName" : name, "userEmail" : email }
      ,success: function(response) {
        if(response.result == "success") {
          location.href = "/love/index/IndexLoveView";
        } else {
          location.href = "/love/member/SignupLoveForm";
        }
      }
      ,error : function(jqXHR, textStatus, errorThrown){
        alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
      }
    });
  }