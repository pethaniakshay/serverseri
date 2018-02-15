/**
 *  Forgot Password
 */

$(document).ready(function(){
  $('#submit').click(function(){
    var data = {};
    data['email'] = $('#email').val();
    $.ajax({
      type : 'post',
      url : '/serverseri/ajax_send_passwd_rst_lnk',
      dataType : 'json',
      data : data,
      success : function(response) {
        console.log("Response of form submit: ", response);
      },
      error : function(e) {
        console.log("Error: ", e);
      }
    });
  });
});