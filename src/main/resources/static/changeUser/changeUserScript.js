$(document).ready(function () {

    $('#changeUserButton').click( function () {
        let button = $(this);

        $('#submitChangeUser').attr("data-oldUserName", button.attr("data-oldUserName"));

        $('#changeModal').modal("show");
    });

    $('#errorModalCenterCloseButton').click( function () {
        $('#errorModalCenter').modal("hide");
    });

    $('#changeModalCloseButton').click( function () {
        $('#submitChangeUser').attr("data-oldUserName", "null");
        $('#changeModal').modal("hide");
    });

    $('#submitChangeUser').click(function () {
        let username = $('#username').val();
        let password = $('#password').val()

        if (username === "" || password === "") {
            $('#errorMessage').html("не все поля заполнены");
            $('#errorModalCenter').modal("show");
            return;
        }


        let button = $(this);
        let oldUserName = button.attr("data-oldUserName");

        let changeUser = {
            "username": username,
            "password": password,
            "enabled": $('#enabled').val(),
            "role": $('#role').val(),
            "oldUserName": oldUserName
        }

        $.ajax({
            url: '/changeUser',
            type: 'POST',
            data: JSON.stringify(changeUser),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                if (message === "изменение прошло успешно"){
                    location.reload();
                }
                else {
                    $('#errorMessage').html(message);
                    $('#errorModalCenter').modal("show");
                }
                },
            error: (err) => {
                console.log(err);
                $('#errorMessage').html("что-то пошло не так");
                $('#errorModalCenter').modal("show");
            }
        });
    });
});