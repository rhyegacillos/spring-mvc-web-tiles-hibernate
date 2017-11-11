function onLoad() {
    $("#pass").keyup(checkPasswordMatch);
    $("#confirmPass").keyup(checkPasswordMatch);
    
    $('#addNewAccount').submit(
        function enableSubmit() {
            var password = $("#pass").val();
            var confirmPassword = $("#confirmPass").val();

            if(password != confirmPassword) {
                alert("Password do not match")
                return false;
            } else {
                return true;
            }
        });
}

function checkPasswordMatch() {

    var password = $("#pass").val();
    var confirmPassword = $("#confirmPass").val();

    if(password.length > 3 || confirmPassword.length > 3) {
        if(password == confirmPassword) {
            // $("#passwordMatch").text('<fmt:message key=\'MatchedPassword.user.password\' />');
            $("#passwordMatch").text('Password match');
            $("#passwordMatch").addClass("valid");
            $("#passwordMatch").removeClass("error");
        } else {
            // $("#passwordMatch").text('<fmt:message key=\'UnmatchedPassword.user.password\' />');
            $("#passwordMatch").text('Password do not match');
            $("#passwordMatch").addClass("error");
            $("#passwordMatch").removeClass("valid")
            $("#passwordMatch").addClass()
        }
    }
}


$(document).ready(onLoad);

function onDeleteClick(event) {

    var doDelete = confirm('Are you sure you want to delete this offer?');

    if(doDelete == false){
        event.preventDefault();
    }

}

$('#delete').click(onDeleteClick);


