$(document).ready(function () {
    var in3 = $('#refreshTimeTable');
    in3.hide();

    $("#flexSwitchCheckDefault").change(function () {

        if ($(this).prop('checked') == true) {
            var in2 = $('#createTimeTable');
            in2.hide();
            var in1 = $('#refreshTimeTable');
            in1.show();
        } else {
            var in2 = $('#createTimeTable');
            in2.show();
            var in1 = $('#refreshTimeTable');
            in1.hide();
        }
    });

    var create = $('#createTimeTableDiv');
    create.hide();
    var files = $('.UploadContainer');

    $("#submit").on('click', function () {
        files.hide();
        create.show();
    });
});