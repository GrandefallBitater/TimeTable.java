$(document).ready(function () {
    $(document).on('click', '#refreshSessionButton', function () {
        let lesson = $(this).parent().parent();
        $('#refreshSessionSubmitButton').attr("data-id", $('#refreshSessionButton').attr("data-id"));
        $('#refreshSubjectName').val(lesson.children('#nameTimeTable').children().text());
        $('#refreshAudienceNumber').val(lesson.children('#ANumTimeTable').children().text());
        $('#refreshTeacher').val(lesson.children('#TeacherTimeTable').children().text());
        $('#refreshSessionModal').modal("show");
    });

    $(document).on("click", '#refreshSessionCloseButton', function () {
        $('#refreshSessionSubmitButton').attr("data-id", "null");
        $('#refreshSessionModal').modal("hide");
    });

    $(document).on("click", '#refreshSessionSubmitButton', function () {
        let teacher = $('#refreshTeacher').val();
        let audienceNumber = $('#refreshAudienceNumber').val();
        let subjectName = $('#refreshSubjectName').val();

        if (audienceNumber === "" || subjectName === "" || teacher === "") {
            $('#errorMessage').html("все поля должны быть заполнены");
            $('#errorModalCenter').modal("show");
            return;
        }

        let Session = {
            "id":  $('#refreshSessionSubmitButton').attr("data-id"),
            "teacherName": teacher,
            "time": $('#refreshSubjectType').value,
            "groupNumber": "",
            "SubjectName": subjectName,
            "audienceNumber": audienceNumber,
            "SubjectType": $('#refreshSubjectTime').value,
            "day": ""
        }

        $.ajax({
            url: '/refreshSession',
            type: 'POST',
            data: JSON.stringify(Session),
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