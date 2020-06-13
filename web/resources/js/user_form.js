$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='login_form']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      pUsername: "required",
      pPassword: {
        required: true,
        minlength: 5
      }
    },
    // Specify validation error messages
    messages: {
      pUsername: "Please enter your firstname",
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      }
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});