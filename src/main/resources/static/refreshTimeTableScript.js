$(document).ready(function () {

    $('.refreshButton').click( function () {
        let lesson = $(this).parent().parent();

        $('#lesson-name').val(lesson.children('#nameTimeTable').children().text());
        $('#time').val(lesson.children('#TimeTimeTable').children().text());
        $('#audienceNumber').val(lesson.children('#ANumTimeTable').children().text());
        $('#teacher').val(lesson.children('#TeacherTimeTable').children().text());
        $('#type').val(lesson.children('#typeTimeTable').children().text());

        $('#exampleModal').modal("show");
    });

    $('.close').click( function () {
        $('#exampleModal').modal("hide");
    });

    $('#close').click( function () {
        $('#exampleModal').modal("hide");
    });

    $('#submit').click( function () {
        $('#refreshForm').submit();
    });
});