$(document).ready(function () {
    $(document).on('click', '#deleteSessionButton', function () {
       $('#submitDeleteSession').attr("data-id", $('#deleteSessionButton').attr("data-id"));
       $('#deleteModal').modal("show");
    });

    $(document).on("click", '#deleteModalCloseButton', function () {
        $('#submitDeleteSession').attr("data-id", "null");
        $('#deleteModal').modal("hide");
    });

    $(document).on("click", '#submitDeleteSession', function () {
        let Session = {
            "id": $('#submitDeleteSession').attr("data-id"),
            "teacherName": "",
            "time": "",
            "groupNumber": $('#foundedTimeTable').attr("data-group"),
            "SubjectName": "",
            "audienceNumber": 0,
            "SubjectType": "",
        }

        $.ajax({
            url: '/deleteSession',
            type: 'POST',
            data: JSON.stringify(Session),
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