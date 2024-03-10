$(document).ready(function () {
    $(document).on('click', '#refreshSessionButton', function () {
        let lesson = $(this).parent().parent();
        $('#refreshSessionSubmitButton').attr("data-id", $('#refreshSessionButton').attr("data-id"));
        $('#refreshSubjectName').val(lesson.children('#nameTimeTable').children().text());
        $('#refreshAudienceNumber').val(lesson.children('#ANumTimeTable').children().text());
        $('#refreshTeacher').val(lesson.children('#TeacherTimeTable').children().text());
        let SubjectType = lesson.children('#typeTimeTable').children().text();
        if (SubjectType === "Лекция") {
            $('#refreshSubjectType option:contains("Лекция")').prop('selected', true);
        } else if(SubjectType === "Лабораторная работа") {
            $('#refreshSubjectType option:contains("Лабораторная работа")').prop('selected', true);
        }else {
            $('#refreshSubjectType option:contains("Практика")').prop('selected', true);
        }

        let SubjectTime = lesson.children('#TimeTimeTable').children().text();
        if (SubjectTime === "8:00") {
            $('#refreshSubjectTime option:contains("8:00")').prop('selected', true);
        } else if(SubjectTime === "9:40") {
            $('#refreshSubjectTime option:contains("9:40")').prop('selected', true);
        }else if(SubjectTime === "11:20") {
            $('#refreshSubjectTime option:contains("11:20")').prop('selected', true);
        }else if(SubjectTime === "13:30") {
            $('#refreshSubjectTime option:contains("13:30")').prop('selected', true);
        }else if(SubjectTime === "15:10") {
            $('#refreshSubjectTime option:contains("15:10")').prop('selected', true);
        } else if(SubjectTime === "16:40") {
            $('#refreshSubjectTime option:contains("16:40")').prop('selected', true);
        }
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
            "time": $('#refreshSubjectTime').val(),
            "groupNumber": $('#foundedTimeTable').attr("data-group"),
            "SubjectName": subjectName,
            "audienceNumber": audienceNumber,
            "SubjectType": $('#refreshSubjectType').val(),
            "day": ""
        }
        console.log(Session);
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