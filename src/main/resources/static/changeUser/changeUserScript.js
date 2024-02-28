$(document).ready(function () {

    $(document).on('click', '#changeUserButton', function () {
        let button = $(this);
        let user = button.parent().parent();

        $('#submitChangeUser').attr("data-oldUserName", button.attr("data-oldUserName"));
        $('#username').val(user.find('#viewUserName').html());
        $('#password').val(user.find('#viewPassword').html());

        //не работает корректно
        if (user.find('#viewEnabled').html() === "true") {
            $('#enabled').value = 'true';
        }else {
            $('#enabled').value = 'false';
        }

        if (user.find('#viewRole').html() === "ADMIN") {
            $('#enabled').value = 'admin';
        }else if (user.find('#viewRole').html() === "TEACHER"){
            $('#enabled').value = 'teacher';
        }else {
            $('#enabled').value = 'user';
        }
        $('#changeModal').modal("show");
    });

    $(document).on('click', '#errorModalCenterCloseButton', function () {
        $('#errorModalCenter').modal("hide");
    });

    $(document).on('click', '#changeModalCloseButton', function () {
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