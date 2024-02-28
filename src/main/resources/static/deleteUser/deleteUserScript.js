$(document).ready(function () {
    $(document).on('click', '#deleteUserButton', function () {
        $('#submitDeleteUser').attr("data-username", $(this).attr("data-username"));
        $('#deleteModal').modal("show");
    });

    $(document).on('click', '#deleteModalCloseButton', function () {
        $('#submitDeleteUser').attr("data-username", "null");
        $('#deleteModal').modal("hide");
    });

    $(document).on('click', '#submitDeleteUser', function () {
        let deleteUser = {
            "username": $('#submitDeleteUser').attr("data-username"),
            "password": "null",
            "enabled": "null",
            "role": "null",
            "oldUserName": "null"
        }

        $.ajax({
            url: '/deleteUser',
            type: 'POST',
            data: JSON.stringify(deleteUser),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (message) => {
                if (message === "удаление прошло успешно"){
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