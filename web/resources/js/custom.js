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

    updatePage();

    startTimer();

}

var timer;

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


function updateMessageLink(data) {
    $("#numberMessages").text(data.number);
}

function showMessages(data) {
    $("#messages").html("");
    for (var i = 0; i < data.messages.length; i++) {
        var message = data.messages[i];

        var messageDiv = document.createElement("div");
        messageDiv.setAttribute("class", "message");

        var subjectSpan = document.createElement("span");
        subjectSpan.setAttribute("class", "subject");
        subjectSpan.appendChild(document.createTextNode(message.subject));

        var contentSpan = document.createElement("span");
        contentSpan.setAttribute("class", "messageContent");
        contentSpan.appendChild(document.createTextNode(message.content));

        var nameSpan = document.createElement("span");
        nameSpan.setAttribute("class", "name");
        nameSpan.appendChild(document.createTextNode(message.name + " ("));

        var link = document.createElement("a");
        link.setAttribute("href", "#");
        link.setAttribute("class", "replyLink");
        link.setAttribute("onClick", "showReply(" + i + ")");
        link.appendChild(document.createTextNode(message.email));
        nameSpan.appendChild(link);
        nameSpan.appendChild(document.createTextNode(")"));

        var replyForm = document.createElement("form");
        replyForm.setAttribute("class", "replyForm");
        replyForm.setAttribute("id", "form" + i);

        var textarea = document.createElement("textarea");
        textarea.setAttribute("class", "replyArea");
        textarea.setAttribute("id", "textarea" + i);

        var replyButton = document.createElement("input");
        replyButton.setAttribute("class", "btn btn-info");
        replyButton.setAttribute("type", "button");
        replyButton.setAttribute("value", "Reply");
        replyButton.onclick = function (j) {
            return function () {
                sendMessage(j);
            }
        }(i);

        replyForm.appendChild(textarea);
        replyForm.appendChild(replyButton);

        messageDiv.appendChild(subjectSpan);
        messageDiv.appendChild(contentSpan);
        messageDiv.appendChild(nameSpan);
        messageDiv.appendChild(replyForm);

        $("#messages").append(messageDiv);
    }
}

function updatePage() {
    $.getJSON("/getMessages", updateMessageLink);
    $.getJSON("/getMessages", showMessages);
}

function showReply(i) {
    stopTimer();
    $("#form" + i).toggle();
}

function stopTimer() {
    window.clearInterval(timer);
}

function startTimer() {
    timer = window.setInterval(updatePage, 5000);
}

function sendMessage(i) {
    alert($("#textarea" + i).val());
}

