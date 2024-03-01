$(document).ready(function () {
    $(document).on('click', '#createSessionButton', function () {
        $('#createSessionSubmitButton').attr("data-day", $('#createSessionButton').attr("data-day"));
        $('#createSessionModal').modal("show");
    });

    $(document).on("click", '#createSessionCloseButton', function () {
        $('#createSessionSubmitButton').attr("data-day", "null");
        $('#createSessionModal').modal("hide");
    });

    $(document).on("click", '#createSessionSubmitButton', function () {
        let teacher = $('#createTeacher').val();
        let audienceNumber = $('#createAudienceNumber').val();
        let subjectName = $('#createSubjectName').val();

        if (audienceNumber === "" || subjectName === "" || teacher === "") {
            $('#errorMessage').html("все поля должны быть заполнены");
            $('#errorModalCenter').modal("show");
            return;
        }

        let Session = {
            "id": 0,
            "teacherName": teacher,
            "time": $('#createSubjectType').value,
            "groupNumber": $('#foundedTimeTable').attr("data-group"),
            "SubjectName": subjectName,
            "audienceNumber": audienceNumber,
            "SubjectType": $('#createSubjectTime').value,
            "day": $('#createSessionSubmitButton').attr("data-id")
        }

        $.ajax({
            url: '/createSession',
            type: 'POST',
            data: JSON.stringify(Session),
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