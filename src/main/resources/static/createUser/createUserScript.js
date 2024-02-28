$(document).ready(function () {

    $('#createUserButton').click( function () {
        console.log(1);
        $('#createModal').modal("show");
    });

    $(document).on('click', '#createModalCloseButton', function () {
        $('#createModal').modal("hide");
    });

    $('#submitCreateUser').click(function () {
        let username = $('#createUsername').val();
        let password = $('#createPassword').val()

        if (username === "" || password === "") {
            $('#errorMessage').html("не все поля заполнены");
            $('#errorModalCenter').modal("show");
            return;
        }

        let createUser = {
            "username": username,
            "password": password,
            "enabled": $('#createEnabled').val(),
            "role": $('#createRole').val(),
        }

        $.ajax({
            url: '/createUser',
            type: 'POST',
            data: JSON.stringify(createUser),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                if (message === "добавление прошло успешно"){
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