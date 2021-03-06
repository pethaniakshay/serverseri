/**
 * Under the developement.
 */

// Document Ready
$(document).ready(function() {
  $('#newServerForm').submit(function(e) {
    var form = this;
    e.preventDefault();
    var formData = {}

    $.each(this, function(i, v) {
      var input = $(v);
      formData[input.attr("name")] = input.val();
    });

    $.ajax({
      type : $(this).attr('method'),
      url : $(this).attr('action'),
      dataType : 'json',
      data : formData,
      success : function(response) {
        console.log("Response of form submit: ", response);
      },
      error : function(e) {
        console.log("Error: ", e);
      }
    });
  });

  $(function(){
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });
  });

});// End Document Ready
